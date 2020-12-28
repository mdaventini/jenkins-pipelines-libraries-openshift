# cvs2GitHelper.groovy
   Library for cvs2git
  
## Implements
### cvs2GitHelper.cvsCheckout
   Executes checkout with parameters
  
    @param cvsroot   String cvsroot
    @param module    String pmodule in cvs
    @param logLevel  String default 'INFO'
    @return isOk     Boolean
  
### cvs2GitHelper.createCvsTemp
   Executes rsync recursively
  
    @param origin    String SRC path
    @param temp      String DEST path
    @param logLevel  String default 'INFO'
  
### cvs2GitHelper.createOptionsFile
   Create options file in workspace
   Reads a libraryResource object, copies the text to a new file in workspace and replaces the rUPPERCASE values
  
    @param cvsroot       String cvsroot
    @param module        String pmodule in cvs
    @param logLevel      String default 'INFO'
    @return optionsFile  String optionsFile name
  
### cvs2GitHelper.getAuthors
   Returns a string with parsed authors from CVSROOT
  
    @param cvsroot             String cvsroot
    @param logLevel            String default 'INFO'
    @return authors_transform  String with parsed authors
  
### cvs2GitHelper.changesForBitbucket
   Executes checkout from cvs and transforms files based on libraryResource transformsFile
  
    @param cvsrootTemp  String cvsroot temp
    @param module       String module in cvs
    @param logLevel     String default 'INFO'
  
### cvs2GitHelper.execCvs2Git
   Executes cvs2git
  
    @param optionsFile  String optionsFile name
    @param logLevel     String default 'INFO'
  
### cvs2GitHelper.cvsrootOrigin
   Returns a string with path to cvs root origin
  
    @return cvsrootOrigin  String
  
### cvs2GitHelper.cvsExecutable
   Returns a string with path to cvs executable in agent
  
    @return cvsExecutable  String
  
### cvs2GitHelper.translateCvs2Git
   Returns a string with cvs repo translated to git
  
    @param cvsRepo            String cvs module name
    @param logLevel           String default 'INFO'
    @return translateCvs2Git  String
  
### cvs2GitHelper.createBitbucketRepo
   Executes fast-import and push to repository in a Bitbucket project
  
    @param bbConfigsMap  Map
        [bareHttpUrl, bareSshUrl, sshRepoUrl, httpRepoUrl, projectKey, repoName, httpCredentialsID, sshCredentialsID]
    @param readmeFile    String readme file name and extension
    @param logLevel      String default 'INFO'
  
### cvs2GitHelper.createReadme
   Create readme.md file in workspace
   Reads a libraryResource object, replaces the rUPPERCASE values and copies the text to a new file in workspace
  
    @param module        String pmodule in cvs
    @param bbConfigsMap  Map
        [bareHttpUrl, bareSshUrl, sshRepoUrl, httpRepoUrl, projectKey, repoName, httpCredentialsID, sshCredentialsID]
    @param readmeFile    String readme file name and extension
    @param logLevel      String default 'INFO'
  
## Uses:
- devops-openshift-libraries.cust.knownProcEnv
- devops-openshift-libraries.cust.rootGroup
- devops-openshift-libraries.cust.setCommentSmudge
- devops-openshift-libraries.jiraHelper.validEmail
- devops-openshift-libraries.log.debug
- devops-openshift-libraries.log.error
- devops-openshift-libraries.log.trace
- devops-openshift-libraries.log.warning
- devops-openshift-libraries.utils.comma
- devops-openshift-libraries.utils.gitSCM
- devops-openshift-libraries.utils.newLine
- devops-openshift-libraries.utils.slash
- devops-openshift-libraries.utils.unknownUsr
