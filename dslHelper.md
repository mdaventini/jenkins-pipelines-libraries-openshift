# dslHelper.groovy
  
   Library for configurations as code using dsl
  
## Implements
### dslHelper.createJob
   Creates a MultibranchPipelineJob or PipelineJob from a dsl resource
  
    @param jobConfig  Map
        fileDsl         String path to file containing dsl text
        nameJob         String
        displayName     String
        descriptionJob  String
        parameters      String
        sshRepo         String remote repository sshURL. This uses the same syntax as your git clone command
        sshCredentials  String credentialsId to use
        httpCredentials String http credentialsId
        branchesRepo    String branches to include
        factoryScript   String script or id for factory workflow
        buildStrategies String buildStrategies closure
        scan            Boolean true for scanning multibranchpipeline
        firstBuild      Boolean true for execute pipeline to run only 'Init Parameters' stage
    @param logLevel     String default 'INFO'
  
### dslHelper.createView
   Creates a view in jenkins
  
   @param dslView      String libraryResource
   @param logLevel     String default 'INFO'
  
### dslHelper.createFolder
   Creates a folder for a Bitbucket projectKey
  
   @param projectKey   String existing key in Bitbucket
   @param logLevel     String default 'INFO'
  
### dslHelper.createJobsInFolder
   Creates required jobs for a given buildConfigs in project folder
   Assumes will always use jenkins credentials
   Assumes folder exists
  
    @param buildConfigs Map
    @param logLevel     String default 'INFO'
  
### dslHelper.createJobInfra
   Creates Infra job
   Assumes will always use jenkins credentials
   Assumes folder exists
  
    @param yamlFile   String
    @param nameJob    String
    @param logLevel   String default 'INFO'
  
### dslHelper.readJobConfig
   Reads a libraryResource object, parses the configuration to the returning jobConfigsMap
  
    @param yamlFile      String yaml filename
    @param logLevel      String default 'INFO'
    @return jobConfigsMap   Map
  
## Uses:
- devops-openshift-libraries.cust.bitbucketLDAPCredentialsID
- devops-openshift-libraries.cust.bitbucketSshCredentialsID
- devops-openshift-libraries.cust.branchesToBuild
- devops-openshift-libraries.cust.multibranchDsl
- devops-openshift-libraries.cust.traitPullRequestDiscovery
- devops-openshift-libraries.interfaceHelper.devopsJira
- devops-openshift-libraries.log.debug
- devops-openshift-libraries.log.error
- devops-openshift-libraries.log.info
- devops-openshift-libraries.log.trace
- devops-openshift-libraries.utils.description
- devops-openshift-libraries.utils.getProjectKey
- devops-openshift-libraries.utils.getRepo
- devops-openshift-libraries.utils.httpScmUrl
