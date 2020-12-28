# doHelper.groovy
  
   Library for build and ic devops tasks
  
## Implements
### doHelper.prepareDeliveryConfigs
   Create buildConfigs from deliveryParams
  
    @param deliveryParams     Map
    @param logLevel           String default 'INFO'
    @return rDeliveryConfigs  Map
  
### doHelper.createFromRepoFolder
   Assumes repoFolder contains checkout
   Reads a folder and and parses the configuration to the return LinkedHashMap
   Throws error when
        Could not define compilation language
        You don't have a SNAPSHOT version in your pom
  
    @param repoFolder           String repository folder containing sources
    @param buildConfigsMapFRF   Map with data to be modified
    @param logLevel             String default 'INFO'
    @return buildConfigsMapFRF  Map with old + new data
  
### doHelper.prepareBuildConfigsWithDefaults
   Create buildConfigs from sshScmUrl
  
    @param sshScmUrl                   String ssh repo url
    @param fromBranch                  String branch to checkout (default develop)
    @param createRelease               Boolean (from PipelineDelivery)
    @param logLevel                    String default 'INFO'
    @return rBuildConfigsWithDefaults  Map
  
### doHelper.prepareDevOpsConfigs
   Create buildConfigs + osConfigs from jiraData
   Fails when
       doHelper.prepareBuildConfigsWithDefaults fails
  
    @param jiraData         Map with required jira Data
    @param fromBranch       String branch to checkout (default develop)
    @param createRelease    Boolean (from PipelineDelivery)
    @param logLevel         String default 'INFO'
    @return rDevOpsConfigs  Map
  
### doHelper.workspaceHousekeeping
   Delete workspace
  
    @param logLevel  String default 'INFO'
  
### doHelper.mavenHousekeeping
   Delete .m2 repositories in ${user.home}
  
    @param logLevel            String default 'INFO'
  
### doHelper.checkout
   Decides where to checkout based on language and performs bitbucketHelper.gitCheckout
  
    @param checkoutConfigs  Map [compile: [language: ], scmConfigMap:[sshRepoUrl:, sshCredentialsID:, repoName], branch:]
    @param logLevel         String default 'INFO'
  
### doHelper.doStep
   Decides method to invoke based on language and step
  
    @param buildConfigsMap  Map with required parameters
    @param step             String step to execute (compile, test, sonar, prepare, perform)
    @param logLevel         String default 'INFO'
  
### doHelper.uploadToNexus
   Uploads an artifact to a Nexus3 repository manager instance already defined in jenkins by id
  
    @param buildConfigsMap    Map with required parameters
    @param logLevel           String default 'INFO'
    @return                   List Url+buildInfo.getDeployedArtifacts()
  
### doHelper.uploadToArtifactory
   Uploads an artifact to an Artifactory repository manager instance already defined in jenkins by id
  
    @param deliveryConfigsMap    Map (buildConfigsMap)
    @param logLevel           String default 'INFO'
    @return                   List Url+buildInfo.getDeployedArtifacts()
  
### doHelper.publishInJenkins
   Publish an html report in jenkins
  
    @param reportDir      String
    @param logLevel       String default 'INFO'
  
### doHelper.checkDownloadArtifact
   Checks if an artifact exists in Repository Manager
  
    @param artifactUrl    String
    @param logLevel       String default 'INFO'
    @return               True if is ok
  
### doHelper.getDeployedArtifacts
   Read rlater.txt file and return as string
  
    @param logLevel       String default 'INFO'
    @return               String
  
### doHelper.producedArtifact
   Returns a string with artifact path+name based on delivery.artifact and delivery.packaging
  
   @param deliveryConfigs Map
   @param logLevel String default 'INFO'
   @return  String with artifact path+name based on delivery.artifact and delivery.packaging
  
### doHelper.sPerform
   Returns the string perform
   Avoid SonarQube duplicates
  
    @return  String perform
  
### doHelper.sDeploy
   Returns the string deploy
   Avoid SonarQube duplicates
  
    @return  String deploy
  
### doHelper.sanitySteps
   Pipeline to check Sanity
   Avoid sonarqube duplicates
   Assumes agent was previously defined
  
   @param buildConfigs Map
   @param logLevel String default 'INFO'
  
### doHelper.appDelivery
   Pipeline for application delivery from scm (SNAPSHOT or RELEASE)
   Avoid sonarqube duplicates
   Assumes agent was previously defined
  
   @param deliveryConfigs Map
   @param logLevel String default 'INFO'
  
### doHelper.checkQualityGate
   Wait SonarQube webhook response
   Throws error when Quality Gate is not OK or WARN
  
   @param logLevel String default 'INFO'
  
### doHelper.settingsSuffix
   Returns a suffix for a repoManager removing -default
  
    @param artifactUrl      String
    @param logLevel         String default 'INFO'
    @return settingsSuffix  String
  
## Uses:
- devops-openshift-libraries.bitbucketHelper.bbConfigs
- devops-openshift-libraries.bitbucketHelper.gitCheckout
- devops-openshift-libraries.bitbucketHelper.gitLastCommit
- devops-openshift-libraries.cacHelper.nexusIDs
- devops-openshift-libraries.cust.branchesToBuild
- devops-openshift-libraries.cust.defaultBitbucketServer
- devops-openshift-libraries.cust.defaultBranch
- devops-openshift-libraries.cust.defaultRepoManagerID
- devops-openshift-libraries.interfaceHelper.technologyOpenShift
- devops-openshift-libraries.log.debug
- devops-openshift-libraries.log.debug
- devops-openshift-libraries.log.error
- devops-openshift-libraries.log.info
- devops-openshift-libraries.log.trace
- devops-openshift-libraries.log.trace
- devops-openshift-libraries.log.warning
- devops-openshift-libraries.mvnHelper.buildConfigsMaven
- devops-openshift-libraries.mvnHelper.mvnBranch
- devops-openshift-libraries.mvnHelper.mvnCompile
- devops-openshift-libraries.mvnHelper.mvnPerform
- devops-openshift-libraries.mvnHelper.mvnPrepare
- devops-openshift-libraries.mvnHelper.mvnSonar
- devops-openshift-libraries.mvnHelper.mvnTest
- devops-openshift-libraries.npmHelper.buildConfigsN
- devops-openshift-libraries.npmHelper.nodejsCompile
- devops-openshift-libraries.npmHelper.nodejsPerform
- devops-openshift-libraries.npmHelper.nodejsPrepare
- devops-openshift-libraries.npmHelper.nodejsSonar
- devops-openshift-libraries.npmHelper.nodejsTest
- devops-openshift-libraries.npmHelper.packageJSON
- devops-openshift-libraries.procHelper.branchProc
- devops-openshift-libraries.procHelper.buildConfigsProc
- devops-openshift-libraries.procHelper.make
- devops-openshift-libraries.procHelper.performProc
- devops-openshift-libraries.procHelper.prepareProc
- devops-openshift-libraries.utils.previousDir
- devops-openshift-libraries.utils.sshScmUrl
