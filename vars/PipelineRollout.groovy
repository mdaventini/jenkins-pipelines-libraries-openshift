/**
 * Pipeline for Openshift rollout
 * Rollout a config based on Jira parameters
 *
 * Requires: @Library(['devops-openshift-libraries'])_
 *
 *  @param logLevel String default 'INFO'
 */
/**
 * DevOpsRollout
 */
def call(String logLevel = 'INFO') {
    node {
        //avoid sonarqube duplications
        //param JIRA_ID
        lJIRA_ID = 'JIRA_ID'
        //param Country
        lCountry = 'Country'
    }
    pipeline {
        agent { label 'master' }
        options {
            skipDefaultCheckout()
            disableConcurrentBuilds()
            timeout(activity: true, time: 15, unit: 'MINUTES')
        }
        parameters {
            string name: lJIRA_ID, description: 'Clave que identifica la incidencia de Jira que solicita la promoción',
                defaultValue: 'LABRLS-753', trim: true
            extendedChoice name: lCountry,
                description: 'Country to rollout to (hidden).',
                quoteValue: false, type: 'PT_HIDDEN', visibleItemCount: 0
        }
        environment {
            //init variables
            //Will store required jira data
            jiraData = ' '
            //Will store jiraDataKey only if JIRA_ID exists
            jiraDataKey = ' '
            //Will store openshift data
            osConfigs = ' '
            //Will store country
            pCountry = "${params.Country ?: ' '}"
            //Will store environment
            pEnvironment = "${env.DEPLOY_ENVIRONMENT}"
            //jiraCountries
            jiraCountries = ' '
        }
        stages {
            stage ('Read JIRA') {
                steps {
                    script {
                        //add trace with params
                        log.trace("Parameters are ${params}", logLevel)
                        //display name
                        currentBuild.displayName = "#${BUILD_NUMBER} - JIRA_ID: ${params.JIRA_ID}"
                        //Validate parameters
                        utils.validateParameters([lJIRA_ID], params)
                        //Add message to description as will be part of notification
                        utils.description("Rollout ${params.JIRA_ID}")
                        //Add info to log
                        log.info('Retrieve data')
                        //Read jira and validateParameters
                        //Required issuetypes
                        requiredIssueType = [interfaceHelper.itNameInstanciaDeArtefacto(),
                            interfaceHelper.itNameConfig(), interfaceHelper.itNameSubArtefacto()]
                        //technology
                        validTechnology = interfaceHelper.technologyOpenShift()
                        //read
                        jiraData = interfaceHelper.readJiraIssueOrLinked(params.JIRA_ID, requiredIssueType, [:], validTechnology, logLevel)
                        //jiraDataKey
                        jiraDataKey = jiraData.key
                        //description
                        utils.description("\n${jiraData.key} - ${jiraData.issuetypeName}")
                    }
                }
            }
            stage ('Validate and prepare') {
                steps {
                    script {
                        //deleteDir
                        deleteDir()
                        //jiraCountries vacio
                        jiraCountries = ''
                        //if pCountry (hidden parameter) is null
                        if ( pCountry == ' ' ) {
                            //Keep first
                            pCountry = jiraData.countries[0]
                            //Other countries will be scheduled in next stage
                            jiraCountries = jiraData.countries - pCountry
                        }
                        //Validate pCountry
                        if ( pCountry in cust.validCountries() ) {
                            jiraData.COUNTRY = pCountry
                        } else {
                            //error
                            log.error("${pCountry} is not a valid country")
                        }
                        //displayName
                        currentBuild.displayName = currentBuild.displayName + " - ${pCountry} - ${pEnvironment}"
                        utils.description(" - ${pCountry} - ${pEnvironment}")
                        //build valid osConfigs map, if not valid will throw error
                        osConfigs = openshiftHelper.osConfigsFromParameters(pEnvironment, jiraData, logLevel)
                        //Validate appRepo only when is about to tag in a branch
                        if ( osConfigs.tagBranch ) {
                            //build appRepo
                            appRepo = "${utils.sshScmUrl(cust.defaultBitbucketServer())}/"
                            appRepo += "${osConfigs.git_project}/${jiraData.GIT_REPO}.git"
                            //complete repoConfigMaps
                            bbAppRepo = bitbucketHelper.bbConfigs(appRepo, logLevel)
                            //checkout throws error if branch does not exist
                            bitbucketHelper.gitCheckout(bbAppRepo, osConfigs.tagBranch, logLevel)
                            //info
                            log.info("gitCheckout ${appRepo} Ok")
                            //Validate appRepo write permissions
                            if ( bitbucketHelper.checkRepoPermission(bbAppRepo, 'REPO_WRITE', logLevel) ) {
                                //Check if tag already exists
                                osConfigs.tagExists = bitbucketHelper.gitListTags(bbAppRepo.sshRepoUrl, logLevel).contains(osConfigs.appTag)
                            } else {
                                log.error("El repositorio ${bbAppRepo.sshRepoUrl} no permite escritura")
                            }
                        }
                        //trace jiraCountries
                        log.trace("jiraCountries ${jiraCountries}", logLevel)
                    }
                }
            }
            stage ('Iterate other countries') {
                when {
                    beforeAgent true
                    //Iterate other countries
                    expression { jiraCountries }
                }
                steps {
                    script {
                        //Iterate other countries
                        if ( jiraCountries ) {
                            //trace size
                            log.trace("Countries ${jiraCountries.size}", logLevel)
                            //Iterate other countries
                            jiraCountries.each { nextCountry ->
                                //trace Schedule nextCountry
                                log.trace("Schedule nextCountry ${nextCountry}", logLevel)
                                //Schedule nextCountry
                                build job: env.JOB_BASE_NAME,
                                    parameters: [
                                        string(name: lJIRA_ID, value: params.JIRA_ID),
                                        string(name: lCountry, value: nextCountry)
                                        ],
                                    propagate: false, wait: false
                            }
                        }
                    }
                }
            }
            stage ('Copy ImageStream from desa to testing') {
                when {
                    beforeAgent true
                    //Only when osConfigs.environment is 'testing'
                    expression { osConfigs.isTesting }
                }
                steps {
                    script {
                        //Copy from desa to test
                        //vCurrentTagName
                        vCurrentTagName = "${osConfigs.appName}:${osConfigs.dcTag}"
                        //tagImageStream
                        openshiftHelper.tagImageStream(osConfigs, vCurrentTagName, osConfigs.dcTag, logLevel)
                        //Info
                        log.info("tagImageStream from ${vCurrentTagName} to ${osConfigs.dcTag} Ok")
                    }
                }
            }
            stage ('Skopeo ImageStream from testing to prod') {
                when {
                    beforeAgent true
                    //Only when osConfigs.environment is 'production'
                    expression { osConfigs.isProduction }
                }
                steps {
                    script {
                        //Copy with skopeo
                        openshiftHelper.copyImageStreamSkopeo(osConfigs, logLevel)
                        //Info
                        log.info('copyImageStream Ok')
                    }
                }
            }
            stage ('Tag new ImageStream version in prod') {
                when {
                    beforeAgent true
                    //Only when osConfigs.environment is 'production'
                    expression { osConfigs.isProduction }
                }
                steps {
                    script {
                        //Tag ImageStream from tag -already in os- to dcTag
                        vCurrentTagName = "${osConfigs.appName}:${osConfigs.dcTag}"
                        openshiftHelper.tagImageStream(osConfigs, vCurrentTagName, osConfigs.dcTag, logLevel)
                        //Info
                        log.info("Tagged ${osConfigs.tag} to ${osConfigs.dcTag} Ok")
                    }
                }
            }
            stage ('Apply Infra') {
                steps {
                    script {
                        //always apply-infra
                        openshiftHelper.execApplyInfra(osConfigs, logLevel)
                    }
                }
            }
            stage ('Create ConfigMaps') {
                steps {
                    script {
                        //exec create ConfigMaps
                        openshiftHelper.execCreateConfigMaps(osConfigs, logLevel)
                    }
                }
            }
            stage ('Rollout new app version') {
                steps {
                    script {
                        //Rollout
                        openshiftHelper.rolloutVersion(osConfigs, logLevel)
                        log.info('rolloutVersion Ok')
                    }
                }
            }
            stage ('Tag in scm repo') {
                when {
                    beforeAgent true
                    allOf {
                        //Only when osConfigs.tagBranch is not empty
                        expression { osConfigs.tagBranch }
                        //Only when tag does not exist tagExists
                        expression { ! osConfigs.tagExists }
                        //Only when osConfigs.environment is 'production'
                        expression { osConfigs.isProduction }
                    }
                }
                steps {
                    script {
                        //tag in git_project/git_repo master
                        //commentSmartCommits
                        commentSmartCommits = "#comment: Rollout ${osConfigs.appTag} in ${osConfigs.environment} ${osConfigs.country}"
                        //repo folder
                        dir ( jiraData.GIT_REPO ) {
                            //message
                            actionMessage = "${jiraData.key} ${commentSmartCommits}"
                            bitbucketHelper.gitTagPush(osConfigs.appTag, actionMessage, logLevel)
                            //Add message to description as will be part of notification
                            log.info("Tagged ${osConfigs.appTag} in ${osConfigs.tagBranch} Ok")
                        }
                    }
                }
            }
        }
        post {
            always {
                script {
                    //Add message to description as will be part of notification
                    utils.description(" ${cust.console()} for details.\n")
                    //For debug purpose
                    log.debug('post->always->' + currentBuild.currentResult, logLevel)
                    //set nRecipientProviders to avoid duplications in SonarQube
                    nRecipientProviders = [[$class: 'RequesterRecipientProvider']]
                    //Init other
                    nOtherRecipient = ''
                    //Add comment in jiraData when found
                    if ( jiraDataKey.trim() ) {
                        jiraComment = "${cust.emailSubject()}\n${cust.emailBody()}"
                        jiraHelper.addCommentInJiraIssue(jiraData.key, jiraComment, cust.defaultJiraSiteID(), logLevel)
                    } else {
                        utils.description('\n\n Jira issue not found!!\n')
                    }
                }
            }
            success {
                script {
                    //add debug message
                    log.debug('PipelineRollout: Success! Notify RequesterRecipientProvider and Technical Leader', logLevel)
                }
            }
            aborted {
                script {
                    //add debug message
                    log.debug('PipelineRollout: Aborted! Notify RequesterRecipientProvider', logLevel)
                }
            }
            failure {
                script {
                    //add debug message
                    log.debug('PipelineRollout: Failure! Notify RequesterRecipientProvider, Technical Leader and emailMainteiner', logLevel)
                    //Add emailMainteiner
                    nOtherRecipient = cust.emailMainteiner()
                }
            }
            cleanup { //Run the steps in this post condition after every other post condition has been evaluated, regardless status
                script {
                    //add debug message
                    log.debug("PipelineRollout: Post cleanup: notify ${nRecipientProviders} ${nOtherRecipient}", logLevel)
                    //Notify about result
                    notifierHelper.emailNotifier(cust.emailSubject(), cust.emailBody(), nRecipientProviders, nOtherRecipient, logLevel)
                }
            }
        }
    }
}
