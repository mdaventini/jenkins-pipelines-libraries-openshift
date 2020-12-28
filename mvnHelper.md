# mvnHelper.groovy
  
   Library for common mvn tasks
  
## Implements
### mvnHelper.buildConfigsMaven
   Assumes current folder contains checkout
   Reads and parses the configuration to the return LinkedHashMap
  
    @param buildConfigsMaven   Map with required parameters
    @param logLevel            String default 'INFO'
    @return buildConfigsMaven  Map with old + new data
  
### mvnHelper.mvnCompile
   Executes mvn compile with parameters
  
    @param buildConfigsMap  Map with required parameters
    @param logLevel         String default 'INFO'
  
### mvnHelper.mvnTest
   Executes mvn test with parameters
  
    @param buildConfigsMap  Map with required parameters
    @param logLevel         String default 'INFO'
  
### mvnHelper.mvnSonar
   Executes mvn sonar with parameters and waits for status, aborting the pipeline if quality gate is failure
  
    @param buildConfigsMap  Map with required parameters
    @param logLevel         String default 'INFO'
  
### mvnHelper.mvnPrepare
   Executes prepare with parameters
  
    @param buildConfigsMap  Map with required parameters
    @param logLevel         String default 'INFO'
  
### mvnHelper.mvnPerform
   Executes perform with parameters
  
    @param buildConfigsMap  Map with required parameters
    @param logLevel         String default 'INFO'
  
### mvnHelper.mvnBranch
   Executes prepare with parameters
  
    @param buildConfigsMap  Map with required parameters
    @param logLevel         String default 'INFO'
  
### mvnHelper.mvnGoal
   Generic mvn step with parameters
  
    @param buildConfigsMap  Map with required parameters
    @param goal             String goal(s) to execute
    @param logLevel         String default 'INFO'
  
### mvnHelper.releasePlugin
   Returns a string with maven-release-plugin group:artifactId:version
  
    @return  String maven-release-plugin group:artifactId:version
  
### mvnHelper.deployPlugin
   Returns a string with maven-deploy-plugin group:artifactId:version
  
    @return  String maven-deploy-plugin group:artifactId:version
  
### mvnHelper.traceMavenUpdate
   Returns the string 'TRACEMVNU'
   Avoid sonarqube duplications
  
    @return String
  
### mvnHelper.traceMaven
   Returns a List with strings with TRACEMVN, TRACEALL
   Avoid sonarqube duplications
  
    @return List
  
### mvnHelper.tagNameToMap
   Converts a tagName string into map
  
    @param tagName   String tag name to convert
    @param logLevel  String default 'INFO'
    @return          Map (tagName, major, minor, patch, qualifier )
  
## Uses:
- devops-openshift-libraries.bitbucketHelper.gitTagPush
- devops-openshift-libraries.cust.bitbucketSshCredentialsID
- devops-openshift-libraries.cust.repositoryManagers
- devops-openshift-libraries.cust.setComment
- devops-openshift-libraries.cust.sonarqubeID
- devops-openshift-libraries.doHelper.getDeployedArtifacts
- devops-openshift-libraries.doHelper.producedArtifact
- devops-openshift-libraries.doHelper.settingsSuffix
- devops-openshift-libraries.log.debug
- devops-openshift-libraries.log.error
- devops-openshift-libraries.log.info
- devops-openshift-libraries.log.trace
- devops-openshift-libraries.log.trace
- devops-openshift-libraries.utils.slash
- devops-openshift-libraries.utils.stringAsList
