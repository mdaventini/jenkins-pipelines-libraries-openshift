# openshiftHelper.groovy
### openshiftHelper.groovy
   Library for Openshift integration
  
  
## Implements
   osConfigs
  
   @Map
        @environment       String parameter with known environment cust.validEnvironments()
        @clusterOS         String known Openshift cluster (from environment variable dependig on environment)
        @credentialsID     String known credentials ID for Openshift (from environment variable dependig on environment)
        @dcTag             String represents current tag
        @country           String extract from nameSpace
        @dcTimezone        String calculated from country
        @isTesting         Boolean dependig on environment
        @isProduction      Boolean dependig on environment
        @nameSpace         String nameSpace in Openshift (NAMESPACE)
        @nameSpaceFrom     String nameSpace to copy from in Openshift
        @appName           String appName in Openshift (APP_NAME)
        @git_project       String projectKey in Bitbucket (GIT_PROJECT)
        @git_repo          String app repo in Bitbucket (GIT_REPO)
        @tag               String tag in git_project
        @tagBranch         String branch to tag when after rollout ok
        @countryTag        String tag-country
        @git_config_repo   String repo with configurations - git_project
        @config_repo_path  String folder git_config_repo containig data and file (CONFIG_REPO_PATH)
        @configs_tag       String tag containing data and file in config_repo_path
        @appTag            String tag to create in app git_repo
        @repoConfigMaps    String ssh url to git_config_repo with ConfigMaps (data and file)
        @repoInfraMaps     String ssh url to git_config_repo with infra template
  
### openshiftHelper.osConfigsFromParameters
   Returns a Map with openshift configurations for a given environment and params
  
    @param environment  String
    @param given        Map given parameters [CLUSTER, APP_NAME, NAMESPACE, COUNTRY, GIT_PROJECT,
    @param logLevel     String default 'INFO'
    @return osMap       Map osConfigs
  
### openshiftHelper.osConfigsFromParametersDMC
   Returns a Map with openshift configurations for a given environment, APP_NAME, NAMESPACE, COUNTRY, GIT_PROJECT, GIT_TAG
  
    @param environment  String
    @param given        Map given parameters [CLUSTER, APP_NAME, NAMESPACE, COUNTRY, GIT_PROJECT, GIT_TAG]
    @param logLevel     String default 'INFO'
    @return osMap       Map as osConfigsFromParameters
  
### openshiftHelper.readOpenshiftEnvironment
   Returns a Map with openshift instance configurations for a given enviroment-nameSpace
  
    @param deployEnvironment  String ['testing', 'development', 'production'] env.DEPLOY_ENVIRONMENT
    @param openShiftCluster   String ['openshift', 'openshift4ext', 'openshift4ixt']
    @param nameSpace          String known nameSpace in openshift
    @param logLevel           String default 'INFO'
    @return osEnvMap          Map osConfigs
  
### openshiftHelper.osDescribe
   Shows all objects for a given project
  
    @param osConfigs  Map osConfigs
    @param logLevel   String default 'INFO'
  
### openshiftHelper.osDelete
   Deletes all objects for a given project
  
    @param osConfigs  Map osConfigs
    @param logLevel   String default 'INFO'
  
### openshiftHelper.createDraftInfra
   When selector app: draftConfigs.appName does not exist, call to DevOpsCreateInfraConfig
  
    @param draftConfigs  Map
    @param logLevel      String default 'INFO'
  
### openshiftHelper.execApplyInfra
   Checkout infra repo and apply template
  
    @param osConfigs  Map osConfigs
    @param logLevel   String default 'INFO'
  
### openshiftHelper.execCreateConfigMaps
   Checkout configs repo and apply to osConfig
  
    @param osConfigs  Map osConfigs
    @param logLevel   String default 'INFO'
  
### openshiftHelper.tagImageStream
   Tags a nameSpace
  
    @param osConfigs        Map osConfigs
    @param currentTagName   String nameSpace
    @param newTagName       String name of the new image tag
    @param logLevel   String default 'INFO'
  
### openshiftHelper.copyImageStreamSkopeo
   Copies the image nameSpace
   Assumes skopeo is in path
  
    @param osConfigs      Map osConfigs
    @param logLevel       String default 'INFO'
  
### openshiftHelper.copyImageStream
   Tags a nameSpace-desa
  
    @param osConfigs        Map osConfigs
    @param currentTagName   String name of the current image tag or actual image ID
    @param newTagName       String name of the new image tag
    @param logLevel   String default 'INFO'
  
### openshiftHelper.createDataConfigMaps
   Creates configmap in a given os-nameSpace
   Assumes is in data directory
  
    @param osConfigs      Map osConfigs
    @param logLevel       String default 'INFO'
  
### openshiftHelper.createFileConfigMaps
   Assumes is in file directory
  
    @param osConfigs      Map osConfigs
    @param logLevel       String default 'INFO'
  
### openshiftHelper.buildSourceToImage
   Build Source to Image
  
    @param configsMap     Map osConfigs
    @param logLevel       String default 'INFO'
  
### openshiftHelper.rolloutVersion
   Rollout Version
  
    @param osConfigs      Map osConfigs
    @param logLevel       String default 'INFO'
  
### openshiftHelper.prepareConfigurations
   deletes irrelevant files for a country, assumes is inside folder
  
    @param country      String country to keep
    @param logLevel     String default 'INFO'
  
### openshiftHelper.deleteIrrelevantFilesRecurse
   deletes irrelevant files for a country, assumes is inside folder
  
    @param country      String country to keep
    @param logLevel     String default 'INFO'
  
### openshiftHelper.deleteEmptyRecurse
   deletes empty files and folders, assumes is inside folder
  
    @param logLevel     String default 'INFO'
  
### openshiftHelper.renameFilesRecurse
   renames files for a country, assumes is inside folder
  
    @param country      String country to keep
    @param logLevel     String default 'INFO'
  
### openshiftHelper.selectorISTag
   Returns a String with 'imagestreamtags' as configured in openshift
   Avoid sonarqube duplications and allow to use alias or type of resource without alias
  
    @return  String with 'imagestreamtags'
  
### openshiftHelper.selectorConfigMap
   Returns a String with 'configmap' as configured in openshift
   Avoid sonarqube duplications
  
    @return  String with 'configmap'
  
### openshiftHelper.selectorDeploymentConfig
   Returns a String with 'dc' as configured in openshift
   Avoid sonarqube duplications
  
    @return  String with 'dc'
  
### openshiftHelper.selectorBuildConfigs
   Returns a String with 'buildconfigs' as configured in openshift
   Avoid sonarqube duplications and allow to use alias or type of resource without alias
   Returns a String with 'buildconfigs' or 'bc' as configured in openshift
  
    @return  String with 'buildconfigs'
  
### openshiftHelper.overwrite
   Returns a String with 'overwrite' from openshift options
   Avoid sonarqube duplications
  
    @return  String with 'overwrite'
  
### openshiftHelper.container_infra
   Returns a String with 'container_infra'
   Avoid sonarqube duplications
  
    @return  String with 'container_infra'
  
### openshiftHelper.featureNameSpace
   Returns a String with 'claro-apps'
   Avoid sonarqube duplications
  
    @return  String with 'claro-apps'
  
### openshiftHelper.addP
   Returns a String with '-p' from openshift options
   Avoid sonarqube duplications
  
    @return  String with '-p'
  
### openshiftHelper.fileNotEmpty
   checks if a given file has lines
  
    @param fileName     String file to check
    @param logLevel     String default 'INFO'
    @return             boolean true not Empty
  
### openshiftHelper.skopeoBin
   Returns a String with full path to skopeo binary file
   Read path from SKOPEO_PATH and verifies if binary file name is included
   Tests
    find 
      
    find 
      
    find 
      
  
    @param logLevel     String default 'INFO'
    @return skopeoBin   String
  
### openshiftHelper.showList
   Executes ls -laR when logLevel is TRACE
  
    @param logLevel     String default 'INFO'
  
### openshiftHelper.callDevOpsCreateInfraConfig
   Returns a String with full path to skopeo binary file
  
    @param jiraArtifactKey  String JIRA_ID
    @param compileMap       Map devopsConfigs.compile
    @param nameSpace        String nameSpace
    @param logLevel         String default 'INFO'
  
### openshiftHelper.toolOC
   Returns the List ["PATH+OC=${tool 'OS-311'}"]
   Avoid sonarqube duplications
  
    @return List
  
## Uses:
- devops-openshift-libraries.bitbucketHelper.bbConfigs
- devops-openshift-libraries.bitbucketHelper.gitCheckout
- devops-openshift-libraries.cust.countriesMap
- devops-openshift-libraries.cust.development
- devops-openshift-libraries.cust.production
- devops-openshift-libraries.cust.shortArgentina
- devops-openshift-libraries.cust.shortNoCountry
- devops-openshift-libraries.cust.splitUXFile
- devops-openshift-libraries.cust.testing
- devops-openshift-libraries.cust.validCountries
- devops-openshift-libraries.cust.validEnvironments
- devops-openshift-libraries.log.debug
- devops-openshift-libraries.log.error
- devops-openshift-libraries.log.info
- devops-openshift-libraries.log.trace
- devops-openshift-libraries.log.warning
- devops-openshift-libraries.utils.allLiteral
- devops-openshift-libraries.utils.description
- devops-openshift-libraries.utils.newLine
- devops-openshift-libraries.utils.projectSshScmUrl
