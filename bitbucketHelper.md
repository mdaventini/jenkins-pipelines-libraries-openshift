# bitbucketHelper.groovy
   Library for bitbucket
  
  
## Implements
### bitbucketHelper.bbConfigs
   Returns a Map with bitbucket configurations for a given sshScmUrl
   Assumes will always use jenkins credentials
  
    @param sshScmUrl String sshUrl to repo
    @param logLevel  String default 'INFO'
    @return Map [bareHttpUrl, bareSshUrl, sshRepoUrl, httpRepoUrl, browseUrl,
                 projectKey, repoName,
                 repoLocator,
                 httpCredentialsID, sshCredentialsID]
  
### bitbucketHelper.checkBitbucketProject
   Returns true if a project exists in Bitbucket
  
    @param bbConfigsMap  Map [bareHttpUrl, bareSshUrl, sshRepoUrl, httpRepoUrl, projectKey, repoName, httpCredentialsID, sshCredentialsID]
    @param logLevel      String default 'INFO'
    @return isOk         Boolean
  
### bitbucketHelper.checkBitbucketRepoProject
   Returns true if a project
  
    @param bbConfigsMap  Map [bareHttpUrl, bareSshUrl, sshRepoUrl, httpRepoUrl, projectKey, repoName, httpCredentialsID, sshCredentialsID]
    @param logLevel      String default 'INFO'
    @return isOk         Boolean
  
### bitbucketHelper.createEmptyBitbucketRepoProject
   Creates an empty repo in projectKey in Bitbucket
  
    @param bbConfigsMap  Map [bareHttpUrl, bareSshUrl, sshRepoUrl, httpRepoUrl, projectKey, repoName, httpCredentialsID, sshCredentialsID]
    @param logLevel       String default 'INFO'
  
### bitbucketHelper.createBranch
   Creates a branch in current repo
   Uses sshagent with 'ssh_jenkins' credentials
   Assumes is running inside repo folder and origin branch
  
    @param branchName  String branch name
    @param tagName     String tag name
    @param logLevel    String default 'INFO'
  
### bitbucketHelper.gitCheckout
   Executes checkout scm using ssh auth and extensions
  
    @param bbConfigsMap  Map [bareHttpUrl, bareSshUrl, sshRepoUrl, httpRepoUrl, projectKey, repoName, httpCredentialsID, sshCredentialsID]
    @param scmBranch     String branch in repo
    @param logLevel      String default 'INFO'
  
### bitbucketHelper.gitAddCommitPush
   Executes [add] commit and push to repository in a Bitbucket project
   Uses sshagent with 'ssh_jenkins' credentials
   assumes is inside local repository folder
  
    @param commitMessage  String referencing job
    @param mustAdd        Boolean true when data is new
    @param logLevel       String default 'INFO'
  
### bitbucketHelper.gitTagPush
   Executes tag and push to repository in a Bitbucket project (must be in a ref head)
   Uses sshagent with 'ssh_jenkins' credentials
   assumes is inside local repository folder
  
    @param tagName        String valid tagName
    @param commitMessage  String user message
    @param logLevel       String default 'INFO'
  
### bitbucketHelper.gitLastCommit
   Gets last commit in a branch
   Uses sshagent with 'ssh_jenkins' credentials
  
    @param repoFolder           String repo folder
    @param logLevel             String default 'INFO'
    @return lastCommitDateTime  String with format 'yyyyMMddHHmmss'
  
### bitbucketHelper.gitListTags
   Lists tags in a remote repository (without checkingout to workspace)
   Uses sshagent with 'ssh_jenkins' credentials
  
    @param sshScmUrl  String sshUrl to repo
    @param logLevel   String default 'INFO'
    @return           String multiline with tags in the format [tagName]
  
### bitbucketHelper.ensureRepoWebhook
   Creates the webhook to this jenkins instance if not present
   VERSION_5
  
    @param bbConfigsMap  Map [bareHttpUrl, bareSshUrl, sshRepoUrl, httpRepoUrl, projectKey, repoName, httpCredentialsID, sshCredentialsID]
    @param logLevel      String default 'INFO'
  
### bitbucketHelper.ensureRepoWebhook7
   Creates the webhook to this jenkins instance if not present
   VERSION_7
  
    @param bbConfigsMap7  Map [bareHttpUrl, bareSshUrl, sshRepoUrl, httpRepoUrl, projectKey, repoName, httpCredentialsID, sshCredentialsID]
    @param logLevel       String default 'INFO'
  
### bitbucketHelper.ensureRestrictions
   Creates default restrictions for protected branchs: 'master' and 'develop' in the repository
  
    @param bbConfigsMap  Map [bareHttpUrl, bareSshUrl, sshRepoUrl, httpRepoUrl, projectKey, repoName, httpCredentialsID, sshCredentialsID]
    @param logLevel      String default 'INFO'
  
### bitbucketHelper.ensureUserInstanceSshKey
   Adds user ssh key (ssh-rsa) in bitbucket
  
    @param httpServer         String Server Url
    @param httpCredentialsID  String credentials id name
    @param logLevel           String default 'INFO'
  
### bitbucketHelper.createPullRequest
   Creates a pull request from a branch to target branch
  
    @param bbConfigsMap   Map [bareHttpUrl, bareSshUrl, sshRepoUrl, httpRepoUrl, projectKey, repoName, httpCredentialsID, sshCredentialsID]
    @param pullRequestMap Map [fromBranch, toBranch, reviewer, title, description]
    @param logLevel       String default 'INFO'
  
### bitbucketHelper.checkRestriction
   Check if a restriction exists in the repository
  
    @param bbConfigsMap Map [bareHttpUrl, ..., repoLocator, httpCredentialsID]
    @param filter       Map [filter, type, object, branch] restrictions
    @param onlyGroups   String valid exemptions groups
    @param logLevel     String default 'INFO'
    @return true if exists
  
### bitbucketHelper.addBranchRestriction
   Adds a restriction in the repository parsing filter
  
    @param bbConfigsMap Map [bareHttpUrl, ..., repoLocator, httpCredentialsID]
    @param filter       Map [filter, type, object, branch] restrictions
    @param onlyGroups   String valid exemptions groups
    @param logLevel     String default 'INFO'
  
### bitbucketHelper.checkPullRequest
   Check if a pull request exists in scm repo
  
    @param bbConfigsMap   Map [bareHttpUrl, bareSshUrl, sshRepoUrl, httpRepoUrl, projectKey, repoName, httpCredentialsID, sshCredentialsID]
    @param pullRequestMap Map [fromBranch, toBranch, reviewer, title, description]
    @param logLevel       String default 'INFO'
  
### bitbucketHelper.branchPermissionsMap
   Returns a Map from a string
  
    @param filter  String filter to parse
    @return        Map [filter, type, object, branch]
  
### bitbucketHelper.readBranchesByJiraIssue
   Check if a pull request exists in scm repo for a given JiraIssue and returns it configs
  
    @param jiraId         String given JiraIssue id
    @param jiraSite       String known Jira Site Id
    @param logLevel       String default 'INFO'
    @return jiraBranches
  
### bitbucketHelper.restApiLatest
   Returns a String '
  
    @return  String
  
### bitbucketHelper.checkRepoPermission
   Check if the authenticated user has given permission on a repo slug and project key
  
    @param bbConfigsMap   Map [bareHttpUrl, projectKey, repoName, httpCredentialsID]
    @param permission     String (valid values are REPO_READ, REPO_WRITE and REPO_ADMIN)
    @param logLevel       String default 'INFO'
    @return foundRepoPermission
  
### bitbucketHelper.bitbucketServerVersion
   Returns a string with serverVersion for a given Bitbucket Server Url
  
    @param bareHttpUrl     String
    @param logLevel        String default 'INFO'
    @return serverVersion  String
  
## Uses:
- devops-openshift-libraries.cacHelper.bitbucketIDs
- devops-openshift-libraries.cacHelper.jiraSiteIDs
- devops-openshift-libraries.cacHelper.jiraStepsSiteIDs
- devops-openshift-libraries.cust.bitbucketLDAPCredentialsID
- devops-openshift-libraries.cust.bitbucketSshCredentialsID
- devops-openshift-libraries.cust.defaultBranch
- devops-openshift-libraries.cust.defaultJiraSiteID
- devops-openshift-libraries.cust.gitConfig
- devops-openshift-libraries.cust.onlyPRExemptionsGroups
- devops-openshift-libraries.cust.setComment
- devops-openshift-libraries.cust.webhookTitle
- devops-openshift-libraries.jiraHelper.searchJiraUser
- devops-openshift-libraries.log.debug
- devops-openshift-libraries.log.error
- devops-openshift-libraries.log.trace
- devops-openshift-libraries.log.unstable
- devops-openshift-libraries.log.warning
- devops-openshift-libraries.utils.credentialIdUsername
- devops-openshift-libraries.utils.getProjectKey
- devops-openshift-libraries.utils.getRepo
- devops-openshift-libraries.utils.getServer
- devops-openshift-libraries.utils.httpScmUrl
- devops-openshift-libraries.utils.literalOkNotFound
- devops-openshift-libraries.utils.slash
