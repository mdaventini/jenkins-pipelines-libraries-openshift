/*
 * openshiftHelper.groovy
 * Library for Openshift integration
 *
 */
/**
 * osConfigs
 *
 * @Map
 *      @environment       String parameter with known environment cust.validEnvironments()
 *      @clusterOS         String known Openshift cluster (from environment variable dependig on environment)
 *      @credentialsID     String known credentials ID for Openshift (from environment variable dependig on environment)
 *      @dcTag             String represents current tag
 *      @country           String extract from nameSpace
 *      @dcTimezone        String calculated from country
 *      @isTesting         Boolean dependig on environment
 *      @isProduction      Boolean dependig on environment
 *      @nameSpace         String nameSpace in Openshift (NAMESPACE)
 *      @nameSpaceFrom     String nameSpace to copy from in Openshift
 *      @appName           String appName in Openshift (APP_NAME)
 *      @git_project       String projectKey in Bitbucket (GIT_PROJECT)
 *      @git_repo          String app repo in Bitbucket (GIT_REPO)
 *      @tag               String tag in git_project/git_repo (GIT_TAG)
 *      @tagBranch         String branch to tag when after rollout ok
 *      @countryTag        String tag-country
 *      @git_config_repo   String repo with configurations - git_project/git_config_repo in Bitbucket - (GIT_CONFIG_REPO)
 *      @config_repo_path  String folder git_config_repo containig data and file (CONFIG_REPO_PATH)
 *      @configs_tag       String tag containing data and file in config_repo_path
 *      @appTag            String tag to create in app git_repo
 *      @repoConfigMaps    String ssh url to git_config_repo with ConfigMaps (data and file)
 *      @repoInfraMaps     String ssh url to git_config_repo with infra template
 */
/**
 * openshiftHelper.osConfigsFromParameters
 * Returns a Map with openshift configurations for a given environment and params
 *
 *  @param environment  String
 *  @param given        Map given parameters [CLUSTER, APP_NAME, NAMESPACE, COUNTRY, GIT_PROJECT,
        GIT_TAG, GIT_CONFIG_REPO, CONFIG_REPO_PATH, INSTANCIA]
 *  @param logLevel     String default 'INFO'
 *  @return osMap       Map osConfigs
 */
def osConfigsFromParameters(String environment, Map given, String logLevel = 'INFO') {
    log.trace("openshiftHelper.osConfigsFromParameters: environment ${environment} given ${given}", logLevel)
    try {
        //add COUNTRY
        vNamespace = given.NAMESPACE + '-' + given.COUNTRY
        vAppTag = ''
        //Define OpenShift parameters based on environment and nameSpace
        osEnvMap = readOpenshiftEnvironment(environment, given.CLUSTER, vNamespace, logLevel)
        //Change dcTag is always version (vGitTag) only for Testing and Production
        if ( environment != cust.development() ) {
            osEnvMap.dcTag = given.GIT_TAG
            //INSTANCIA will be populated from Instancia de Artefacto, Sub-Artefacto or Config
            //GIT_TAG
            vTag = given.GIT_TAG
            if ( given.INSTANCIA ) {
                //GIT_TAG + '-RC.' + INSTANCIA
                vTag += '-RC.' + given.INSTANCIA
            }
            //Change dcTag fot testing
            if ( osEnvMap.isTesting ) {
                osEnvMap.dcTag = vTag
            //production does not use -RC
            } else if ( osEnvMap.isProduction ) {
                //Change dcTag
                osEnvMap.dcTag = given.GIT_TAG
                vAppTag = given.GIT_TAG + '-' + given.COUNTRY
            }
        }
        //when environment is development will use development as vConfigs_tag
        if ( environment == cust.development() ) {
            //vConfigs_tag
            vConfigs_tag = cust.development()
        } else {
            //vConfigs_tag
            vConfigs_tag = given.APP_NAME + '-' + osEnvMap.dcTag
        }
        //tSshScmUrl_project
        tSshScmUrl_project = utils.projectSshScmUrl(given.GIT_PROJECT.toLowerCase().trim())
        //build osMap
        osMap = osEnvMap +
            [appName: given.APP_NAME,
                git_project: given.GIT_PROJECT,
                git_config_repo: given.GIT_CONFIG_REPO,
                configs_tag: vConfigs_tag,
                repoInfraMaps: tSshScmUrl_project + "/${container_infra()}.git",
                repoConfigMaps: tSshScmUrl_project + "/${given.GIT_CONFIG_REPO}.git",
                config_repo_path: given.CONFIG_REPO_PATH,
                appTag: vAppTag,
            ]
    } catch (err) {
        log.error("openshiftHelper.osConfigsFromParameters:\n${err}")
    }
    log.debug("openshiftHelper.osConfigsFromParameters: osMap ${osMap}", logLevel)
    osMap
}

/**
 * openshiftHelper.osConfigsFromParametersDMC
 * Returns a Map with openshift configurations for a given environment, APP_NAME, NAMESPACE, COUNTRY, GIT_PROJECT, GIT_TAG
 *
 *  @param environment  String
 *  @param given        Map given parameters [CLUSTER, APP_NAME, NAMESPACE, COUNTRY, GIT_PROJECT, GIT_TAG]
 *  @param logLevel     String default 'INFO'
 *  @return osMap       Map as osConfigsFromParameters
 */
def osConfigsFromParametersDMC(String environment, Map given, String logLevel = 'INFO') {
    log.trace("openshiftHelper.osConfigsFromParametersDMC: environment ${environment} given ${given}", logLevel)
    //build map
    osMap = osConfigsFromParameters(environment, given, logLevel)
    //DMC
    //tSshScmUrl_project
    tSshScmUrl_project = utils.projectSshScmUrl(given.GIT_PROJECT.toLowerCase().trim())
    //tSshScmUrlRepoDMC
    tSshScmUrlRepoDMC = tSshScmUrl_project + "/${given.APP_NAME}-OSConfigs-dmc.git"
    //replace with dmc
    //infra template [${serverBitbucket}/${GIT_PROJECT}/${APP_NAME}-OSConfigs-dmc.git]
    osMap.repoInfraMaps = tSshScmUrlRepoDMC
    //ConfigMaps (data and file) [${serverBitbucket}/${GIT_PROJECT}/${APP_NAME}-OSConfigs-dmc.git],
    osMap.repoConfigMaps = tSshScmUrlRepoDMC
    //debug
    log.debug("openshiftHelper.osConfigsFromParametersDMC: osMap ${osMap}", logLevel)
    osMap
}

/**
 * openshiftHelper.readOpenshiftEnvironment
 * Returns a Map with openshift instance configurations for a given enviroment-nameSpace
 *
 *  @param deployEnvironment  String ['testing', 'development', 'production'] env.DEPLOY_ENVIRONMENT
 *  @param openShiftCluster   String ['openshift', 'openshift4ext', 'openshift4ixt']
 *  @param nameSpace          String known nameSpace in openshift
 *  @param logLevel           String default 'INFO'
 *  @return osEnvMap          Map osConfigs
 */
def readOpenshiftEnvironment(String deployEnvironment, String openShiftCluster, String nameSpace, String logLevel = 'INFO') {
    log.trace("openshiftHelper.readOpenshiftEnvironment: deployEnvironment ${deployEnvironment} nameSpace ${nameSpace}", logLevel)
    //Suffix no country
    sNoCountry = '-' + cust.shortNoCountry()
    //init with default
    vIsProduction = false
    vIsTesting = false
    //nameSpace removing -desa
    vNameSpace = nameSpace - '-desa'
    //Define country and dcTimezone based on nameSpace
    vCountry = vNameSpace.split('-')[-1]
    //Consider -np in vNameSpace means no country and uses values from default
    if ( vNameSpace.contains(sNoCountry) ) {
        //no country uses Argentina tz
        vCountry = cust.shortArgentina()
        //namespace does not include country
        vNameSpace = vNameSpace.replace(sNoCountry, '')
    }
    //Validate country
    if ( cust.countriesMap()[vCountry] ) {
        log.debug("openshiftHelper.readOpenshiftEnvironment: country is ${vCountry}", logLevel)
    } else {
        msgError = "No se pudo identificar el país donde desplegar: ${vCountry}. A partir de: ${vNameSpace}"
        log.error("openshiftHelper.readOpenshiftEnvironment: ${msgError}")
    }
    //init vTagBranch
    vTagBranch = ''
    vNameSpaceFrom = ''
    //define clusterOS, credentialsID, nameSpace, nameSpaceFrom, dcTag, isTesting, isProduction and tagBranch
    //    based on deployEnvironment and vNameSpace
    switch ( deployEnvironment ) {
        case cust.testing():
            vClusterOS = env.OS_CLUSTER_NP
            vCredentialsID = env.OS_CREDENTIALS_TEST
            vDcTag = 'stage'
            vIsTesting = true
            vNameSpaceFrom = "${vNameSpace}-desa"
            break
        case cust.production():
            vClusterOS = env.OS_CLUSTER_P
            vCredentialsID = env.OS_CREDENTIALS_PROD
            vDcTag = 'prod'
            vIsProduction = true
            vTagBranch = 'master'
            vNameSpaceFrom = vNameSpace
            break
        default: //development
            vClusterOS = env.OS_CLUSTER_NP
            vCredentialsID = env.OS_CREDENTIALS_DEV
            vNameSpace = "${vNameSpace}-desa"
            vDcTag = 'latest'
            break
    }
    //use vClusterOS with given openShiftCluster
    vOpenShiftCluster = openShiftCluster + '_'
    vClusterOS = vClusterOS.replace('openshift_', vOpenShiftCluster)
    //will only change nameSpace when is not featureNameSpace()
    featureNameSpace = featureNameSpace()
    if ( vNameSpace.startsWith(featureNameSpace) ) {
        vNameSpace = featureNameSpace
        log.trace("openshiftHelper.readOpenshiftEnvironment: keep ${featureNameSpace} vNameSpace ${vNameSpace}", logLevel)
    }
    //build map to return
    osEnvMap = [environment: deployEnvironment,
        clusterOS: vClusterOS,
        credentialsID: vCredentialsID,
        dcTag: vDcTag,
        country: vCountry,
        dcTimezone: cust.countriesMap().find { it.key == vCountry }.value.timeZone,
        isTesting: vIsTesting,
        isProduction: vIsProduction,
        nameSpace: vNameSpace,
        nameSpaceFrom: vNameSpaceFrom,
        tagBranch: vTagBranch,
        ]
    log.debug("openshiftHelper.readOpenshiftEnvironment: osEnvMap ${osEnvMap} Ok", logLevel)
    osEnvMap
}
/**
 * openshiftHelper.osDescribe
 * Shows all objects for a given project
 *
 *  @param osConfigs  Map osConfigs
 *  @param logLevel   String default 'INFO'
 */
def osDescribe(Map osConfigs, String logLevel = 'INFO') {
    log.trace("openshiftHelper.osDescribe: osConfigs ${osConfigs}", logLevel)
    appInfo = "in ${osConfigs.environment} ${osConfigs.clusterOS} ${osConfigs.nameSpace}/${osConfigs.appName}"
    log.info("openshiftHelper.osDescribe: describe content ${appInfo}", logLevel)
    try {
        //oc client
        withEnv(toolOC()) {
            openshift.withCluster(osConfigs.clusterOS) {
                openshift.withCredentials(osConfigs.credentialsID) {
                    openshift.withProject(osConfigs.nameSpace) {
                        vSelector = openshift.selector(utils.allLiteral(), [app: "${osConfigs.appName}"])
                        log.info('openshiftHelper.osDescribe: selector: all', logLevel)
                        if ( vSelector.exists() ) {
                            vSelector.describe()
                        } else {
                            log.error('openshiftHelper.osDescribe: selector: all not found!')
                        }
                    }
                }
            }
        }
        log.debug("openshiftHelper.osDescribe: ${osConfigs.nameSpace}/${osConfigs.appName} Ok", logLevel)
    } catch (err) {
        log.error("openshiftHelper.osDescribe ${osConfigs}\n${err}")
    }
}

/**
 * openshiftHelper.osDelete
 * Deletes all objects for a given project
 *
 *  @param osConfigs  Map osConfigs
 *  @param logLevel   String default 'INFO'
 */
def osDelete(Map osConfigs, String logLevel = 'INFO') {
    log.trace("openshiftHelper.osDelete: osConfigs ${osConfigs}", logLevel)
    appInfo = "in ${osConfigs.environment} ${osConfigs.clusterOS} ${osConfigs.nameSpace}/${osConfigs.appName}"
    log.info("openshiftHelper.osDelete: delete content ${appInfo}", logLevel)
    try {
        //oc client
        withEnv(toolOC()) {
            openshift.withCluster(osConfigs.clusterOS) {
                openshift.withCredentials(osConfigs.credentialsID) {
                    openshift.withProject(osConfigs.nameSpace) {
                       //oc delete all -l app=buscador
                        log.debug("openshiftHelper.osDelete: About to delete selector: all ${appInfo}", logLevel)
                        openshift.selector(utils.allLiteral(), [app: osConfigs.appName]).delete()
                        log.info("openshiftHelper.osDelete: selector: all ${appInfo} deleted", logLevel)
                    }
                }
            }
        }
        log.debug("openshiftHelper.osDelete: ${osConfigs.nameSpace}/${osConfigs.appName} Ok", logLevel)
    } catch (err) {
        log.error("openshiftHelper.osDelete ${osConfigs}\n${err}")
    }
}

/**
 * openshiftHelper.createDraftInfra
 * When selector app: draftConfigs.appName does not exist, call to DevOpsCreateInfraConfig
 *
 *  @param draftConfigs  Map
 *  @param logLevel      String default 'INFO'
 */
def createDraftInfra(Map draftConfigs, String logLevel = 'INFO') {
    log.trace("openshiftHelper.createDraftInfra: draftConfigs ${draftConfigs}", logLevel)
    appInfo = "in ${draftConfigs.osConfigs.environment} ${draftConfigs.osConfigs.clusterOS}"
    appInfo += " ${draftConfigs.osConfigs.nameSpace}/${draftConfigs.osConfigs.appName}"
    log.trace("openshiftHelper.createDraftInfra: ${appInfo}", logLevel)
    try {
        //oc client
        withEnv(toolOC()) {
            openshift.withCluster(draftConfigs.osConfigs.clusterOS) {
                openshift.withCredentials(draftConfigs.osConfigs.credentialsID) {
                    openshift.withProject(draftConfigs.osConfigs.nameSpace) {
                        if ( openshift.selector(utils.allLiteral(), [app: "${draftConfigs.osConfigs.appName}"]).exists() ) {
                            log.trace("openshiftHelper.createDraftInfra: app ${appInfo}", logLevel)
                        } else {
                            //DevOpsCreateInfraConfig
                            callDevOpsCreateInfraConfig(draftConfigs.jiraArtifactKey, draftConfigs.compile,
                                draftConfigs.osConfigs.nameSpace, logLevel)
                            log.trace('openshiftHelper.createDraftInfra: call to callDevOpsCreateInfraConfig Ok!', logLevel)
                        }
                    }
                }
            }
        }
        //info
        log.info("openshiftHelper.createDraftInfra: ${appInfo} Ok!", logLevel)
    } catch (err) {
        log.error("openshiftHelper.createDraftInfra: ${appInfo} failed!\n${err}")
    }
}

/**
 * openshiftHelper.execApplyInfra
 * Checkout infra repo and apply template
 *
 *  @param osConfigs  Map osConfigs
 *  @param logLevel   String default 'INFO'
 */
def execApplyInfra(Map osConfigs, String logLevel = 'INFO') {
    log.trace("openshiftHelper.execApplyInfra: osConfigs ${osConfigs}", logLevel)
    //In testing configs_tag is [app]-[version]-RC.[instance]
    //In production configs_tag is [app]-[version]
    if ( osConfigs.isProduction ) {
        osConfigs.configs_tag = osConfigs.configs_tag.split('-RC')[0]
        log.trace("openshiftHelper.execApplyInfra: FIX osConfigs.configs_tag ${osConfigs.configs_tag}", logLevel)
    }
    //checkout
    //build bbRepoInfraMaps
    bbRepoInfraMaps = bitbucketHelper.bbConfigs(osConfigs.repoInfraMaps, logLevel)
    //checkout container_infra using the corresponding tag
    bitbucketHelper.gitCheckout(bbRepoInfraMaps, osConfigs.configs_tag, logLevel)
    //log info
    log.debug("openshiftHelper.execApplyInfra: gitCheckout ${bbRepoInfraMaps} Ok", logLevel)
    //template
    infraTemplate = "${osConfigs.appName}-template.yaml"
    //message git_data
    git_data = "${osConfigs.repoInfraMaps} tag ${osConfigs.configs_tag} ${bbRepoInfraMaps.repoName}/${infraTemplate}"
    actionMessage = "apply-infra ${git_data} to ${osConfigs.environment} ${osConfigs.nameSpace}/${osConfigs.appName}"
    try {
        dir ( bbRepoInfraMaps.repoName ) {
            //Only if file exists and has more than 0 lines
            log.trace("openshiftHelper.execApplyInfra ${infraTemplate} ${fileNotEmpty(infraTemplate, logLevel)}", logLevel)
            if ( fileExists(infraTemplate) && fileNotEmpty(infraTemplate, logLevel) ) {
                log.debug("openshiftHelper.execApplyInfra: about to ${actionMessage}", logLevel)
                //oc client
                withEnv(toolOC()) {
                    sh label: 'show env', script: 'set'
                    openshift.withCluster(osConfigs.clusterOS) {
                        openshift.withCredentials(osConfigs.credentialsID) {
                            openshift.withProject(osConfigs.nameSpace) {
                                log.debug("openshiftHelper.execApplyInfra: applying ...${infraTemplate}", logLevel)
                                if ( logLevel.contains('TRACE') ) {
                                    sh label: 'showTemplate', script: "cat ${infraTemplate}"
                                }
                                openshift.apply(openshift.process(readFile(file: infraTemplate),
                                    addP(), "PARAM_NAMESPACE=${osConfigs.nameSpace}",
                                    addP(), "PARAM_DC_TAG=${osConfigs.dcTag}",
                                    addP(), "TZ=${osConfigs.dcTimezone}"))
                            }
                        }
                    }
                }
            } else {
                howToFix = 'To fix this problem you must run DevOpsCreateNewApp with proper JIRA_ID'
                log.error("openshiftHelper.execApplyInfra: ${git_data} is empty!\n ${howToFix}")
            }
        }
        //info
        log.info("openshiftHelper.execApplyInfra: ${actionMessage} Ok!", logLevel)
    } catch (err) {
        log.error("openshiftHelper.execApplyInfra: ${actionMessage} failed!\n${err}")
    }
}

/**
 * openshiftHelper.execCreateConfigMaps
 * Checkout configs repo and apply to osConfig
 *
 *  @param osConfigs  Map osConfigs
 *  @param logLevel   String default 'INFO'
 */
def execCreateConfigMaps(Map osConfigs, String logLevel = 'INFO') {
    log.debug("openshiftHelper.execCreateConfigMaps: osConfigs ${osConfigs}", logLevel)
    //complete bbRepoConfigsMap
    bbRepoConfigsMap = bitbucketHelper.bbConfigs(osConfigs.repoConfigMaps, logLevel)
    //checkout app-configuration using the corresponding tag
    bitbucketHelper.gitCheckout(bbRepoConfigsMap, osConfigs.configs_tag, logLevel)
    //message git_data
    git_data = "${osConfigs.repoConfigMaps}/${osConfigs.config_repo_path} tag ${osConfigs.configs_tag}"
    applyConfigMapsMessage = "ApplyConfigMaps ${git_data} to ${osConfigs.environment} ${osConfigs.nameSpace}/${osConfigs.appName}"
    //log
    log.info("openshiftHelper.execCreateConfigMaps: about to ${applyConfigMapsMessage}")
    //repoAndRepoPath
    repoAndRepoPath = "${bbRepoConfigsMap.repoName}/${osConfigs.config_repo_path}"
    //fail when repoAndRepoPath does not exists
    if ( ! fileExists(repoAndRepoPath) ) {
        log.error("openshiftHelper.execCreateConfigMaps: ${repoAndRepoPath} does not exists")
    }
    //in ConfigMaps dir
    dir ("${bbRepoConfigsMap.repoName}/${osConfigs.config_repo_path}") {
        //delete irrelevant regional configs
        //delete empty files and folders
        //rename -[country]
        prepareConfigurations(osConfigs.country, logLevel)
        //Create data
        dir ('data') {
            createDataConfigMaps(osConfigs, logLevel)
        }
        //Create File ConfigMaps
        dir ('file') {
            createFileConfigMaps(osConfigs, logLevel)
        }
    }
    log.info('openshiftHelper.execCreateConfigMaps: Ok!', logLevel)
}

/**
 * openshiftHelper.tagImageStream
 * Tags a nameSpace/appName:currentImgName as nameSpace/appName:newImgName
 *
 *  @param osConfigs        Map osConfigs
 *  @param currentTagName   String nameSpace/appName:tag
 *  @param newTagName       String name of the new image tag
 *  @param logLevel   String default 'INFO'
 */
def tagImageStream(Map osConfigs, String currentTagName, String newTagName, String logLevel = 'INFO') {
    log.trace("openshiftHelper.tagImageStream: osConfigs ${osConfigs} currentTagName ${currentTagName} newTagName ${newTagName}", logLevel)
    vNewImage = "${osConfigs.nameSpace}/${osConfigs.appName}:${newTagName}"
    tagImageStreamMessage = "Tag ImageStream ${currentTagName} as ${vNewImage} in ${osConfigs.environment}"
    try {
        //oc client
        withEnv(toolOC()) {
            //Openshift steps
            openshift.withCluster(osConfigs.clusterOS) {
                openshift.withCredentials(osConfigs.credentialsID) {
                    //openshift.withProject(osConfigs.nameSpace) {
                    openshift.withProject(osConfigs.nameSpaceFrom) {
                        if (openshift.selector(selectorISTag(), currentTagName).exists()) {
                            log.trace("openshiftHelper.tagImageStream: currentTagName ${currentTagName} exists", logLevel)
                            openshift.tag(currentTagName, vNewImage)
                        } else {
                            //selector 'istag' with currentTagName does not exist
                            errMsg = "No existe el selector ${selectorISTag()} con la imagen ${currentTagName}"
                            log.error("openshiftHelper.tagImageStream: ${errMsg}")
                        }
                    }
                }
            }
        }
        //info
        log.info("openshiftHelper.tagImageStream: ${currentTagName} tagged as ${vNewImage}", logLevel)
    } catch (err) {
        log.error("openshiftHelper.tagImageStream ${tagImageStreamMessage}\n${err}")
    }
}

/**
 * openshiftHelper.copyImageStreamSkopeo
 * Copies the image nameSpace/appName:tagName from sourceSkopeo to destSkopeo using skopeo
 * Assumes skopeo is in path
 *
 *  @param osConfigs      Map osConfigs
 *  @param logLevel       String default 'INFO'
 */
def copyImageStreamSkopeo(Map osConfigs, String logLevel = 'INFO') {
    log.trace("openshiftHelper.copyImageStreamSkopeo: osConfigs ${osConfigs}", logLevel)
    //build registrysource
    vRegistrySource = "${env.OS_REGISTRY_NP}/${osConfigs.nameSpace}/${osConfigs.appName}:${osConfigs.dcTag}"
    //build registrydest
    vRegistryDest = "${env.OS_REGISTRY_P}/${osConfigs.nameSpace}/${osConfigs.appName}:${osConfigs.dcTag}"
    try {
        withCredentials([
            usernamePassword(credentialsId: "${env.OS_SKOPEO_CREDENTIALS_TEST}", usernameVariable: 'saSrcName',
                passwordVariable: 'saSrcToken'),
            usernamePassword(credentialsId: "${env.OS_SKOPEO_CREDENTIALS_PROD}", usernameVariable: 'saDestName',
                passwordVariable: 'saDestToken'),
        ]) {
            skopeoCommand = "--debug copy --src-tls-verify=false --src-creds=${saSrcName}:${saSrcToken} --dest-tls-verify=false " +
                            " --dest-creds=${saDestName}:${saDestToken} " +
                            "${vRegistrySource} ${vRegistryDest}"
            log.debug("openshiftHelper.copyImageStreamSkopeo Skopeo command: ${skopeoCommand}", logLevel)
            sh label: 'openshiftHelper.copyImageStreamSkopeo', script: "${skopeoBin(logLevel)} ${skopeoCommand}"
        }
        //info
        log.info("openshiftHelper.copyImageStreamSkopeo: Copy from ${vRegistrySource} to ${vRegistryDest} Ok", logLevel)
    } catch (err) {
        log.error("openshiftHelper.copyImageStreamSkopeo: ${vRegistrySource} to ${vRegistryDest}\n${err}")
    }
}

/**
 * openshiftHelper.copyImageStream
 * Tags a nameSpace-desa/appName:currentImgName as nameSpace/appName:newImgName
 *
 *  @param osConfigs        Map osConfigs
 *  @param currentTagName   String name of the current image tag or actual image ID
 *  @param newTagName       String name of the new image tag
 *  @param logLevel   String default 'INFO'
 */
def copyImageStream(Map osConfigs, String currentTagName, String newImgName, String logLevel = 'INFO') {
    log.trace("openshiftHelper.copyImageStream: osConfigs ${osConfigs} currentTagName ${currentTagName} newImgName ${newImgName}", logLevel)
    vOsProjectDesa = "${osConfigs.nameSpace}-desa/${osConfigs.appName}"
    vOsProject = "${osConfigs.nameSpace}/${osConfigs.appName}"
    try {
        //Openshift steps
        //oc client
        withEnv(toolOC()) {
            openshift.withCluster(osConfigs.clusterOS) {
                openshift.withCredentials(osConfigs.credentialsID) {
                    openshift.withProject(osConfigs.nameSpace) {
                        openshift.tag("${vOsProjectDesa}:${currentTagName}", "${vOsProject}:${newImgName}")
                    }
                }
            }
        }
        //info
        msgInfo = "${vOsProjectDesa}:${currentTagName} tagged as ${vOsProject}:${newImgName} in ${osConfigs.environment}"
        log.info("openshiftHelper.copyImageStream: ${msgInfo} Ok", logLevel)
    } catch (err) {
        log.error("openshiftHelper.copyImageStream ${vOsProject}:${currentTagName} to ${vOsProject}:${newImgName} ${jiraIssue}\n${err}")
    }
}

/**
 * openshiftHelper.createDataConfigMaps
 * Creates configmap in a given os-nameSpace/appName from *.env files in dir for a given country
 * Assumes is in data directory
 *
 *  @param osConfigs      Map osConfigs
 *  @param logLevel       String default 'INFO'
 */
def createDataConfigMaps(Map osConfigs, String logLevel = 'INFO') {
    log.trace("openshiftHelper.createDataConfigMaps: osConfigs ${osConfigs}", logLevel)
    actionMessage = "data configmap from ${osConfigs.git_config_repo}/${osConfigs.config_repo_path}/data for ${osConfigs.country}"
    try {
        //files
        files = sh label: 'files', script: "find . -name '*.env'", returnStdout: true
        log.trace("openshiftHelper.createDataConfigMaps: files ${files} ${files.size()}", logLevel)
        if ( files.size() > 0 ) {
            log.debug("openshiftHelper.createDataConfigMaps: Creating ${actionMessage}", logLevel)
            //oc client
            withEnv(toolOC()) {
                openshift.withCluster(osConfigs.clusterOS) {
                    openshift.withCredentials(osConfigs.credentialsID) {
                        openshift.withProject(osConfigs.nameSpace) {
                            files.split(utils.newLine()).each { configFile ->
                                log.trace("openshiftHelper.createDataConfigMaps: file ${configFile}", logLevel)
                                //remove ./ init and extension
                                configMapName = configFile.split(cust.splitUXFile())[-1].split(cust.splitPattern())[0]
                                //create cmSelector
                                log.trace("openshiftHelper.createDataConfigMaps: cmSelector ${selectorConfigMap()}/${configMapName}",
                                    logLevel)
                                cmSelector = openshift.selector("${selectorConfigMap()}/${configMapName}")
                                keepLabels = null
                                //save delete selector configmap/${configMapName}; keep keepLabels
                                if ( cmSelector.exists() ) {
                                    keepLabels = cmSelector.object().metadata.labels
                                    log.trace("openshiftHelper.createDataConfigMaps: will delete cmSelector and keep ${keepLabels}",
                                        logLevel)
                                    cmSelector.delete()
                                }
                                //build from dc/appName
                                if ( keepLabels == null ) {
                                    dcSelector = openshift.selector("${selectorDeploymentConfig()}/${osConfigs.appName}")
                                    keepLabels = dcSelector.object().metadata.labels
                                    //trace
                                    log.trace("openshiftHelper.createDataConfigMaps: keepLabels ${keepLabels}", logLevel)
                                }
                                //log
                                log.debug("openshiftHelper.createDataConfigMaps: create ${configMapName} --from-env-file ${configFile}",
                                    logLevel)
                                //create the config in nameSpace
                                openshift.create(selectorConfigMap(), configMapName, '--from-env-file', "${configFile}")
                                //log
                                log.debug("openshiftHelper.createDataConfigMaps: create dc/${configMapName}", logLevel)
                                //newCMConfig
                                //newCMonfig = openshift.selector("${selectorConfigMap()}/${configMapName}")
                                //add to keepLabels
                                //keepLabels += newCMonfig.object().metadata.labels
                                //apply labels
                                log.debug("openshiftHelper.createDataConfigMaps: apply ${keepLabels.toString()} labels", logLevel)
                                openshift.selector(selectorConfigMap(), configMapName).label(keepLabels, overwrite())
                                //ok log
                                log.debug("openshiftHelper.createDataConfigMaps: applied ${configMapName} --from-env-file ${configFile} Ok",
                                    logLevel)
                            }
                        }
                    }
                }
            }
            //info
            log.info("openshiftHelper.createDataConfigMaps: Created ${actionMessage} Ok", logLevel)
        } else {
            log.warning("openshiftHelper.createDataConfigMaps: Nothing to apply for ${actionMessage}")
        }
    } catch (err) {
        log.error("openshiftHelper.createDataConfigMaps creating ${actionMessage}\n${err}")
    }
}

/**
 * openshiftHelper.createFileConfigMaps
 * Assumes is in file directory
 *
 *  @param osConfigs      Map osConfigs
 *  @param logLevel       String default 'INFO'
 */
def createFileConfigMaps(Map osConfigs, String logLevel = 'INFO') {
    log.trace("openshiftHelper.createFileConfigMaps: osConfigs ${osConfigs}", logLevel)
    actionMessage = "file configmap from ${osConfigs.git_config_repo}/${osConfigs.config_repo_path}/file for ${osConfigs.country}"
    try {
        //trace
        log.trace("openshiftHelper.createFileConfigMaps: in ${osConfigs.git_config_repo}/${osConfigs.config_repo_path}/file", logLevel)
        //this searches for all folders inside file (excluding file using '-not -path .')
        folders = sh label: 'folders', script: 'find . -not -path . -type d', returnStdout: true
        log.trace("openshiftHelper.createFileConfigMaps: folders ${folders}", logLevel)
        //Consider nothing to apply in FileConfigMaps
        if ( folders ) {
            //each folder
            folders.split(utils.newLine()).each { longFolderName ->
                //trace
                log.trace("openshiftHelper.createFileConfigMaps: longFolderName ${longFolderName}", logLevel)
                //configMapName = (longFolderName - (longFolderName - ~ /\s{1}[a-zA-Z0-9\-_]*$/)).trim()
                configMapName = longFolderName.replace(cust.splitUXFile(), '')
                //trace
                log.trace("openshiftHelper.createFileConfigMaps: configMapName ${configMapName}", logLevel)
                //in configMapName
                actionMessage += " dir ${configMapName}"
                //info
                log.debug("openshiftHelper.createFileConfigMaps: creating ${actionMessage}", logLevel)
                //oc client
                withEnv(toolOC()) {
                    openshift.withCluster(osConfigs.clusterOS) {
                        openshift.withCredentials(osConfigs.credentialsID) {
                            openshift.withProject(osConfigs.nameSpace) {
                                //cmSelector
                                cmSelector = openshift.selector("${selectorConfigMap()}/${configMapName}")
                                cfLabels = null
                                //cfLabels with configmap/configMapName
                                if ( cmSelector.exists() ) {
                                    log.trace("openshiftHelper.createFileConfigMaps: cmSelector ${cmSelector} exists()", logLevel)
                                    cfLabels = cmSelector.object().metadata.labels
                                    cmSelector.delete()
                                }
                                //cfLabels with dc/appName
                                if ( cfLabels == null ) {
                                    dcSelector = openshift.selector("${selectorDeploymentConfig()}/${osConfigs.appName}")
                                    cfLabels = dcSelector.object().metadata.labels
                                }
                                //create the config
                                log.debug("openshiftHelper.createFileConfigMaps: create ${configMapName} --from-file ${configMapName}",
                                    logLevel)
                                openshift.create(selectorConfigMap(), configMapName, '--from-file', configMapName)
                                //apply labels
                                log.debug("openshiftHelper.createFileConfigMaps: apply ${cfLabels.toString()} labels to new configmap",
                                    logLevel)
                                openshift.selector(selectorConfigMap(), configMapName).label(cfLabels, overwrite())
                            }
                        }
                    }
                }
            }
            //info
            log.info("openshiftHelper.createFileConfigMaps: created ${actionMessage} Ok", logLevel)
        } else {
            log.warning("openshiftHelper.createFileConfigMaps: Nothing to apply for ${actionMessage}")
        }
    } catch (err) {
        log.error("openshiftHelper.createFileConfigMaps creating ${actionMessage}\n${err}")
    }
}

/**
 * openshiftHelper.buildSourceToImage
 * Build Source to Image
 *
 *  @param configsMap     Map osConfigs
 *  @param logLevel       String default 'INFO'
 */
def buildSourceToImage(Map configsMap, String logLevel = 'INFO') {
    log.trace("openshiftHelper.buildSourceToImage: configsMap ${configsMap}", logLevel)
    //s2iAppName
    s2iAppName = "${configsMap.osConfigs.appName}-s2i"
    //osTarget
    osTarget = "${configsMap.osConfigs.clusterOS} ${configsMap.osConfigs.environment}"
    actionMessage = "s2i ${configsMap.delivery.producedArtifact} -> ${s2iAppName} in ${osTarget}"
    try {
        //oc client
        withEnv(toolOC()) {
            openshift.withCluster(configsMap.osConfigs.clusterOS) {
                openshift.withCredentials(configsMap.osConfigs.credentialsID) {
                    openshift.withProject(configsMap.osConfigs.nameSpace) {
                        log.trace("openshiftHelper.buildSourceToImage: ${actionMessage}", logLevel)
                        openshift.selector('bc', s2iAppName).startBuild ("--from-${configsMap.delivery.producedArtifact} --wait=true")
                    }
                }
            }
        }
        //info
        log.info("openshiftHelper.buildSourceToImage ${actionMessage} Ok", logLevel)
    } catch (err) {
        log.error("openshiftHelper.buildSourceToImage ${actionMessage}\n${err}")
    }
}

/**
 * openshiftHelper.rolloutVersion
 * Rollout Version
 *
 *  @param osConfigs      Map osConfigs
 *  @param logLevel       String default 'INFO'
 */
def rolloutVersion(Map osConfigs, String logLevel = 'INFO') {
    log.trace("openshiftHelper.rolloutVersion: osConfigs ${osConfigs}", logLevel)
    actionMessage = "${osConfigs.nameSpace}/${osConfigs.appName} in ${osConfigs.clusterOS} ${osConfigs.environment}"
    try {
        //Openshift steps
        //oc client
        withEnv(toolOC()) {
            openshift.withCluster(osConfigs.clusterOS) {
                openshift.withCredentials(osConfigs.credentialsID) {
                    openshift.withProject(osConfigs.nameSpace) {
                        try {
                            log.trace('openshiftHelper.rolloutVersion: about to rollout', logLevel)
                            dcRolloutManager = openshift.selector(selectorDeploymentConfig(), osConfigs.appName).rollout()
                            log.trace('openshiftHelper.rolloutVersion: about to latest', logLevel)
                            dcRolloutManager.latest()
                        } catch ( rolloutException ) {
                            log.debug("openshiftHelper.rolloutVersion: rollout exception ${rolloutException}", logLevel)
                            if ( rolloutException.toString().contains('is already in progress') ) {
                                log.warning('openshiftHelper.rolloutVersion: is already in progress')
                            } else {
                                log.error("openshiftHelper.rolloutVersion: rollout exception ${rolloutException}")
                            }
                        }
                        // this will wait until the desired replicas are available
                        timeout (time: 20, unit: 'MINUTES') {
                            rolledOut = false
                            while ( !rolledOut ) {
                                statusR = dcRolloutManager.status('--watch=false')
                                if (statusR.out.contains('successfully rolled out')) {
                                    rolledOut = true
                                } else {
                                    sleep time: 3, unit: 'SECONDS'
                                }
                            }
                        }
                    }
                }
            }
        }
        //info
        log.info("openshiftHelper.rolloutVersion rollout ${actionMessage} Ok", logLevel)
        //description
        utils.description("\nRollout ${actionMessage}")
    } catch (err) {
        log.error("openshiftHelper.rolloutVersion rollout ${actionMessage}\n${err}\nCheck Kibana")
    }
}

/**
 * openshiftHelper.prepareConfigurations
 * deletes irrelevant files for a country, assumes is inside folder
 *
 *  @param country      String country to keep
 *  @param logLevel     String default 'INFO'
 */
void prepareConfigurations(String country, String logLevel = 'INFO') {
    log.trace("openshiftHelper.prepareConfigurations: country ${country}", logLevel)
    try {
        //show before
        showList(logLevel)
        //delete irrelevant regional configs
        deleteIrrelevantFilesRecurse(country, logLevel)
        //delete empty files and folders
        deleteEmptyRecurse(logLevel)
        //rename -[country]
        renameFilesRecurse(country, logLevel)
        //show after
        showList(logLevel)
        //log
        log.debug("openshiftHelper.prepareConfigurations: keep country ${country} Ok", logLevel)
    } catch (err) {
        log.error("openshiftHelper.prepareConfigurations: keep country ${country}\n${err}")
    }
}

/**
 * openshiftHelper.deleteIrrelevantFilesRecurse
 * deletes irrelevant files for a country, assumes is inside folder
 *
 *  @param country      String country to keep
 *  @param logLevel     String default 'INFO'
 */
void deleteIrrelevantFilesRecurse(String country, String logLevel = 'INFO') {
    log.trace("openshiftHelper.deleteIrrelevantFilesRecurse: country ${country}", logLevel)
    //Delete files matching *-*.* but *-[country].*
    allCountries = cust.validCountries()
    //remove selected country from allCountries
    removeCountries = allCountries - country
    //For each irrelevant country
    removeCountries.each {
        //delete files matching *-[country].*
        vDeleteScript = "find . -name '*-${it}.*' -delete || true"
        log.trace("openshiftHelper.deleteIrrelevantFilesRecurse: vDeleteScript ${vDeleteScript}", logLevel)
        //delete
        sh label: 'delete', script: vDeleteScript
    }
    //log
    log.debug("openshiftHelper.deleteIrrelevantFilesRecurse: keep country ${country} Ok", logLevel)
}

/**
 * openshiftHelper.deleteEmptyRecurse
 * deletes empty files and folders, assumes is inside folder
 *
 *  @param logLevel     String default 'INFO'
 */
void deleteEmptyRecurse(String logLevel = 'INFO') {
    log.trace('openshiftHelper.deleteEmptyRecurse', logLevel)
    //Delete empty files
    vDeleteEmptyScript = 'find . -type f -empty -delete || true'
    //delete
    sh label: 'delete empty files', script: vDeleteEmptyScript
    //log
    log.debug('openshiftHelper.deleteEmptyRecurse: empty files deleted', logLevel)
    //Delete empty folders inside data
    vDeleteEmptyScript = 'find data -type d -empty -delete || true'
    sh label: 'delete empty data folders', script: vDeleteEmptyScript
    //Delete empty folders inside file
    vDeleteEmptyScript = 'find file -type d -empty -delete || true'
    sh label: 'delete empty file folders', script: vDeleteEmptyScript
    //log
    log.debug('openshiftHelper.deleteEmptyRecurse: empty folders deleted', logLevel)
}

/**
 * openshiftHelper.renameFilesRecurse
 * renames files for a country, assumes is inside folder
 *
 *  @param country      String country to keep
 *  @param logLevel     String default 'INFO'
 */
void renameFilesRecurse(String country, String logLevel = 'INFO') {
    log.trace("openshiftHelper.renameFilesRecurse: country ${country}", logLevel)
    //Rename files recurse
    vRenameScript = "find . -name *-${country}.* | awk -F'-${country}' '{system(\"mv \" \$0 \" \" \$1\$2) }' || true"
    //delete
    sh label: 'rename', script: vRenameScript
    //log
    log.debug("openshiftHelper.renameFilesRecurse: rename country ${country} Ok", logLevel)
}

/**
 * openshiftHelper.selectorISTag
 * Returns a String with 'imagestreamtags' as configured in openshift
 * Avoid sonarqube duplications and allow to use alias or type of resource without alias
 *
 *  @return  String with 'imagestreamtags'
 */
def selectorISTag() {
    'imagestreamtags'
}

/**
 * openshiftHelper.selectorConfigMap
 * Returns a String with 'configmap' as configured in openshift
 * Avoid sonarqube duplications
 *
 *  @return  String with 'configmap'
 */
def selectorConfigMap() {
    'configmap'
}

/**
 * openshiftHelper.selectorDeploymentConfig
 * Returns a String with 'dc' as configured in openshift
 * Avoid sonarqube duplications
 *
 *  @return  String with 'dc'
 */
def selectorDeploymentConfig() {
    'dc'
}

/**
 * openshiftHelper.selectorBuildConfigs
 * Returns a String with 'buildconfigs' as configured in openshift
 * Avoid sonarqube duplications and allow to use alias or type of resource without alias
 * Returns a String with 'buildconfigs' or 'bc' as configured in openshift
 *
 *  @return  String with 'buildconfigs'
 */
def selectorBuildConfigs() {
    'buildconfigs'
}

/**
 * openshiftHelper.overwrite
 * Returns a String with 'overwrite' from openshift options
 * Avoid sonarqube duplications
 *
 *  @return  String with 'overwrite'
 */
def overwrite() {
    '--overwrite'
}

/**
 * openshiftHelper.container_infra
 * Returns a String with 'container_infra'
 * Avoid sonarqube duplications
 *
 *  @return  String with 'container_infra'
 */
def container_infra() {
    'container_infra'
}

/**
 * openshiftHelper.featureNameSpace
 * Returns a String with 'claro-apps'
 * Avoid sonarqube duplications
 *
 *  @return  String with 'claro-apps'
 */
def featureNameSpace() {
    'claro-apps'
}

/**
 * openshiftHelper.addP
 * Returns a String with '-p' from openshift options
 * Avoid sonarqube duplications
 *
 *  @return  String with '-p'
 */
def addP() {
    '-p'
}

/**
 * openshiftHelper.fileNotEmpty
 * checks if a given file has lines
 *
 *  @param fileName     String file to check
 *  @param logLevel     String default 'INFO'
 *  @return             boolean true not Empty
 */
def fileNotEmpty(String fileName, String logLevel = 'INFO') {
    log.trace("openshiftHelper.fileNotEmpty: fileName ${fileName}", logLevel)
    notEmpty = true
    try {
        //test file
        existReturn = sh label: "-s ${fileName}", script: """
if [ -s ${fileName} ]
then
    echo notempty
else
    echo empty
fi
""",
            returnStdout: true
        if ( existReturn == 'empty' ) {
            notEmpty = false
        }
        log.debug("openshiftHelper.fileNotEmpty: ${existReturn}", logLevel)
    } catch (err) {
        log.error("openshiftHelper.fileNotEmpty: check file ${fileName}\n${err}")
    }
    notEmpty
}

/**
 * openshiftHelper.skopeoBin
 * Returns a String with full path to skopeo binary file
 * Read path from SKOPEO_PATH and verifies if binary file name is included
 * Tests
 *  find /home/newdd/skopeo -name skopeo
 *    /home/newdd/skopeo
 *  find /home/newdd/ -name skopeo
 *    /home/newdd/skopeo
 *  find /home/newdd -name skopeo
 *    /home/newdd/skopeo
 *
 *  @param logLevel     String default 'INFO'
 *  @return skopeoBin   String
 */
def skopeoBin(String logLevel = 'INFO') {
    log.trace("openshiftHelper.skopeoBin SKOPEO_PATH ${env.SKOPEO_PATH}", logLevel)
    skopeoBin = env.SKOPEO_PATH
    //use ux find
    try {
        skopeoBin = sh label: 'hardcoded find', script: "find ${skopeoBin} -name skopeo -type f", returnStdout: true
        log.trace("openshiftHelper.skopeoBin skopeoBin ${skopeoBin}", logLevel)
        skopeoBin.replace('\n', '')
    } catch (err) {
        log.error("openshiftHelper.skopeoBin: skopeoBin ${skopeoBin}\n" + err)
    }
}

/**
 * openshiftHelper.showList
 * Executes ls -laR when logLevel is TRACE
 *
 *  @param logLevel     String default 'INFO'
 */
def showList(String logLevel = 'INFO') {
    log.trace('openshiftHelper.showList', logLevel)
    if ( logLevel.startsWith('TRACE') ) {
        sh label: 'show list', script: 'ls -laR'
    }
}

/**
 * openshiftHelper.callDevOpsCreateInfraConfig
 * Returns a String with full path to skopeo binary file
 *
 *  @param jiraArtifactKey  String JIRA_ID
 *  @param compileMap       Map devopsConfigs.compile
 *  @param nameSpace        String nameSpace
 *  @param logLevel         String default 'INFO'
 */
def callDevOpsCreateInfraConfig(String jiraArtifactKey, Map compileMap, String nameSpace, String logLevel = 'INFO') {
    dParams = "jiraArtifactKey ${jiraArtifactKey} compileMap ${compileMap} nameSpace ${nameSpace}"
    log.trace("openshiftHelper.callDevOpsCreateInfraConfig ${dParams}", logLevel)
    try {
        templateTech = compileMap.templateTech ?: compileMap.language
        log.trace("openshiftHelper.callDevOpsCreateInfraConfig: templateTech ${templateTech}", logLevel)
        cicParams = [ string(name: 'JIRA_ID', value: jiraArtifactKey),
            string(name: 'templateTech', value: templateTech),
            string(name: 'NAMESPACE_CLARO', value: nameSpace),]
        //debug
        log.debug("openshiftHelper.callDevOpsCreateInfraConfig: Call DevOpsCreateInfraConfig with ${cicParams}", logLevel)
        //Create infra using in folder job
        build job: 'DevOpsCreateInfraConfig',
            parameters: cicParams,
            wait: true
    } catch (err) {
        log.error('openshiftHelper.callDevOpsCreateInfraConfig: DevOpsCreateInfraConfig failed\n' + err)
    }
}

/**
 * openshiftHelper.toolOC
 * Returns the List ["PATH+OC=${tool 'OS-311'}"]
 * Avoid sonarqube duplications
 *
 *  @return List
 */
def toolOC() {
    ["PATH+OC=${tool 'OS-311'}"]
}
