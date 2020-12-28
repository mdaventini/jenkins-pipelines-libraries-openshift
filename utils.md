# utils.groovy
   Library for utilities
  
## Implements
### utils.description
   Adds message to currentBuild.description
  
    @param message  String to add in currentBuild.description
  
### utils.getRepo
   Returns repository name for a given scmUrl
  
    @param scmUrl    String full scm Url (http or ssh)
    @return repo     String
  
### utils.getProjectKey
   Returns projectKey for a given scmUrl
  
    @param scmUrl       String full scm Url (http or ssh)
    @return projectKey  String
  
### utils.getServer
   Returns server for a given scmUrl
  
    @param scmUrl    String full scm Url (http or ssh)
    @return server   String
  
### utils.sshScmUrl
   Returns a valid ssh SCM Url for a given httpScmUrl
  
    @param httpScmUrl  String valid http SCM Url
    @return sshScmUrl  String
  
### utils.projectSshScmUrl
   Returns a valid ssh SCM Url for a given project
   Assumes bitbucket is default
  
    @param gitProjectKey     String known git_project key in Bitbucket
    @return projectSshScmUrl String
  
### utils.httpScmUrl
   Returns a valid http SCM Url for a given sshScmUrl
  
    @param sshScmUrl   String valid ssh SCM Url
    @return httpScmUrl String
  
### utils.gitSCM
   Returns a map with hudson.plugins.git.GitSCM$DescriptorImpl configuration in this instance
  
    @return dataGitSCM  Map git config
  
### utils.credentialIdUsername
   Returns a string with the username of a given credentialId
  
    @param credentialId  String known credentialId in jenkins
    @return retUsername  String
  
### utils.userHome
   Returns a string with pwd home
  
    @return pwdHome  String
  
### utils.literalError
   Returns the string 'ERROR'
   Avoid sonarqube duplications
  
    @return          String
  
### utils.literalDisable
   Returns the string 'DISABLE'
   Avoid sonarqube duplications
  
    @return          String
  
### utils.literalOkNotFound
   Returns the string '200,404'
   Avoid sonarqube duplications cannot be configured as exception
  
    @return          String
  
### utils.qualifierRelease
   Returns the string 'RELEASE'
   Avoid sonarqube duplications
  
    @return          String
  
### utils.branchIsRelease
   Returns true if the branch is release
   Avoid sonarqube duplications
  
    @param branch  String branch to evaluate
    @return        boolean
  
### utils.slash
   Returns 
   Avoid sonarqube duplications
  
    @return        String 
  
### utils.hyphen
   Returns -
   Avoid sonarqube duplications
  
    @return        String -
  
### utils.comma
   Returns ,
   Avoid sonarqube duplications
  
    @return        String ,
  
### utils.doubleQuote
   Returns "
   Avoid sonarqube duplications
  
    @return        String "
  
### utils.newLine
   Returns '\\\n'
   Avoid sonarqube duplications
  
    @return        String '\\\n'
  
### utils.unknownUsr
   Returns unknown
   Avoid sonarqube duplications
  
    @return        String unknown
  
### utils.removeTmpDir
   This method removes all tmp directories (and its content) in the current directory
  
### utils.jobFolderName
   Returns the folder name for current job
  
    @return jobFolderName  String
  
### utils.validateParameters
   Thows error when some required parameter value is missing
  
    @param required  List with required parameters
    @param given     Map with given parameters
  
### utils.prefixPR
   Returns the string 'PR-'
  
    @return  String 'PR-'
  
### utils.prefixPullRequests
   Returns the string 'pull-requests'
  
    @return  String 'pull-requests'
  
### utils.timeStamp
   Returns a string with java.time.LocalDateTime.now() with format 'yyyyMMddHHmmss'
  
    @return strTimestamp  String
  
### utils.previousDir
   Returns the string ..
   Avoid sonarqube duplications
  
    @return  String ..
  
### utils.stringAsList
   Return a list from a \n separated string
   Thows error when no lines detected
  
    @param textToSplit    String
    @return               List Url
  
### utils.allLiteral
   Returns the String 'all'
   Avoid sonarqube duplications
  
    @return String
  
## Uses:
- devops-openshift-libraries.cust.defaultBitbucketServer
- devops-openshift-libraries.log.error
