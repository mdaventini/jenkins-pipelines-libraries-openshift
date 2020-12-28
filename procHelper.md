# procHelper.groovy
  
   Library for common proc tasks
  
## Implements
### procHelper.buildConfigsProc
   Assumes current folder contains checkout
   Reads and parses the configuration to the return LinkedHashMap
  
    @param buildConfigsProc   Map with required parameters
    @param logLevel           String default 'INFO'
    @return buildConfigsProc  Map with old + new data
  
### procHelper.make
   Executes make with parameters
  
    @param buildConfigsMap  Map with required parameters
    @param logLevel         String default 'INFO'
  
### procHelper.prepareProc
   Creates a tag with the given name
  
    @param buildConfigsMap  Map with required parameters
    @param logLevel         String default 'INFO'
  
### procHelper.performProc
   Uploads the file in bin directory to repository manager
  
    @param buildConfigsMap  Map with required parameters
    @param logLevel         String default 'INFO'
  
### procHelper.branchProc
   Creates a new branch
  
    @param buildConfigsMap  Map with required parameters
    @param logLevel         String default 'INFO'
  
### procHelper.envProc
   Returns an array with environment definitions
  
   @param custEnv  String
   @param logLevel String default 'INFO'
   @return         Array with environment definitions
  
### procHelper.procPackaging
   Returns a String with 'gz'
  
    @return procPackaging  String
  
## Uses:
- devops-openshift-libraries.bitbucketHelper.createBranch
- devops-openshift-libraries.bitbucketHelper.gitTagPush
- devops-openshift-libraries.cust.countryEnv
- devops-openshift-libraries.cust.langProc
- devops-openshift-libraries.cust.repositoryManagers
- devops-openshift-libraries.cust.rootGroup
- devops-openshift-libraries.cust.setCommentSmudge
- devops-openshift-libraries.cust.splitPattern
- devops-openshift-libraries.doHelper.producedArtifact
- devops-openshift-libraries.doHelper.uploadToArtifactory
- devops-openshift-libraries.doHelper.uploadToNexus
- devops-openshift-libraries.log.debug
- devops-openshift-libraries.log.error
- devops-openshift-libraries.log.info
- devops-openshift-libraries.log.trace
- devops-openshift-libraries.log.warning
- devops-openshift-libraries.utils.allLiteral
- devops-openshift-libraries.utils.previousDir
