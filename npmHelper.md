# npmHelper.groovy
  
   Library for common npm tasks
  
## Implements
### npmHelper.buildConfigsN
   Assumes current folder contains checkout
   Reads and parses the configuration to the return LinkedHashMap
  
    @param buildConfigsN   Map with required parameters
    @param logLevel        String default 'INFO'
    @return buildConfigsN  Map with old + new data
  
### npmHelper.npmStart
   Verify existing configs and files
  
    @param buildConfigsMap  Map with required parameters
    @param logLevel         String default 'INFO'
  
### npmHelper.nodejsCompile
   Executes npm install and build with parameters
  
    @param buildConfigsMap  Map with required parameters
    @param logLevel         String default 'INFO'
  
### npmHelper.nodejsTest
   Executes npm test
  
    @param buildConfigsMap  Map with required parameters
    @param logLevel         String default 'INFO'
  
### npmHelper.nodejsSonar
   Executes sonar-scanner with default sonar host
  
    @param buildConfigsMap  Map with required parameters
    @param logLevel         String default 'INFO'
  
### npmHelper.nodejsPrepare
   copies nginxFile to build folder if exists
  
    @param buildConfigsMap  Map with required parameters
    @param logLevel         String default 'INFO'
  
### npmHelper.nodejsPerform
   Executes npm publish
  
    @param buildConfigsMap  Map with required parameters
    @param logLevel         String default 'INFO'
  
### npmHelper.npmGoal
   Generic npm step with parameters
  
    @param buildConfigsMap  Map with required parameters
    @param goal             String goal(s) to execute
    @param logLevel         String default 'INFO'
  
### npmHelper.packageJSON
   Returns a string with '.
   Avoid sonarqube duplications
  
    @return  String '.
  
## Uses:
- devops-openshift-libraries.bitbucketHelper.gitTagPush
- devops-openshift-libraries.cust.defaultNexus3ID
- devops-openshift-libraries.cust.repositoryManagers
- devops-openshift-libraries.cust.setComment
- devops-openshift-libraries.cust.sonarqubeID
- devops-openshift-libraries.doHelper.settingsSuffix
- devops-openshift-libraries.log.debug
- devops-openshift-libraries.log.error
- devops-openshift-libraries.log.info
- devops-openshift-libraries.log.trace
- devops-openshift-libraries.log.warning
- devops-openshift-libraries.utils.timeStamp
