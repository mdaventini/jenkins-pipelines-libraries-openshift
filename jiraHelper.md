# jiraHelper.groovy
  
   Library for Jira integration
  
## Implements
### jiraHelper.standardFields
   Returns a list with some jira standardFields
   Avoid sonarqube duplicates
  
    @return standardFields List
  
### jiraHelper.readRawJiraData
   Reads a jira issue and parses to a map
   Throws error if jiraGetIssue fails
   Uses: jira-plugin, jira-steps-plugin
  
    @param jiraIssue     String Jira Issue i.e LIC-421
    @param jiraSite      String known jira site defined with jira-plugin
    @param logLevel      String default 'INFO'
    @return rawJiraData  Map of collected jira data
  
### jiraHelper.extJiraGetIssue
   Reads a jira issue as raw and parses to a map with standardFields + customFields + issuetypeId issuetypeName statusId statusName
   Throws error if jiraGetIssue fails
   Uses: jira-plugin, jira-steps-plugin
  
    @param jiraIssue     String Jira Issue i.e LIC-421
    @param jiraSite      String known jira site defined with jira-plugin
    @param customFields  List with customFields to collect
    @param logLevel      String default 'INFO'
    @return cJiraData    Map of collected jira data
  
### jiraHelper.searchJiraUser
   Searchs a user by queryStr in jiraSite and returns its name
  
    @param queryStr   String name, username or email address. (partial string are allowed)
    @param jiraSite   String known jira site
    @param logLevel   String default 'INFO'
    @return userData [self, key, name, emailAddress, avatarUrls[], displayName, active, timeZone, locale]
  
### jiraHelper.addCommentInJiraIssue
   Adds a comment in a jira issue
  
    @param jiraIssue     String Jira Issue i.e LIC-421
    @param comment       String comment to add
    @param jiraSite      String known jira site
    @param logLevel      String default 'INFO'
  
### jiraHelper.writeTransitionJiraIssue
   NEVER TESTED
   Transitions a jira issue to transitionID with message transitionMsg
  
    @param jiraIssue     String Jira Issue i.e LIC-421
    @param transitionID  String known jira transition id
    @param transitionMsg String message for transition
    @param logLevel      String default 'INFO'
  
### jiraHelper.downloadJiraAttachments
   Downloads each attachment in current dir
  
    @param jiraAttachments Map attachment
    @param logLevel        String default 'INFO'
  
### jiraHelper.searchJiraIssues
   Search in jira using jql
  
    @param jiraJql         String jql to search
    @param customFields    List with customFields to collect
    @param jiraSite        String known jira site id
    @param logLevel        String default 'INFO'
    @return foundIssues    Map
  
### jiraHelper.validEmail
   When userFilter is null returns userDefault
   Searchs by userFilter in jira and when exists returns its emailAddress; when not returns userDefault
  
    @param userFilter     String user or email to search
    @param userDefault    String email to return when not found
    @param logLevel       String default 'INFO'
    @return emailAddress  String
  
### jiraHelper.showAllJiraFields
   Returns a list of map with known Jira fields
  
   @param logLevel        String default 'INFO'
   @return allJiraFields  Map
  
## Uses:
- devops-openshift-libraries.cust.defaultJiraSiteID
- devops-openshift-libraries.cust.jiraLDAPCredentialsID
- devops-openshift-libraries.log.debug
- devops-openshift-libraries.log.error
- devops-openshift-libraries.log.trace
- devops-openshift-libraries.log.unstable
