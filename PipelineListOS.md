# PipelineListOS.groovy
   Manual Pipeline for list Openshift configs based on a JIRA issueType Artefacto
   The JIRA_ID must be issueType Artefacto and the git_proyect in Jira must be scmProjectKey
   Is executed manually by any project member
  
   Requires: @Library(['devops-openshift-libraries'])_
  
    @param logLevel String default 'INFO'
  
## Implements
   DevOpsListOS
  
## Uses:
- devops-openshift-libraries.cust.console
- devops-openshift-libraries.cust.defaultJiraSiteID
- devops-openshift-libraries.cust.emailMainteiner
- devops-openshift-libraries.cust.emailSubject
- devops-openshift-libraries.cust.validCountries
- devops-openshift-libraries.interfaceHelper.searchValidIssue
- devops-openshift-libraries.interfaceHelper.technologyOpenShift
- devops-openshift-libraries.log.debug
- devops-openshift-libraries.log.error
- devops-openshift-libraries.log.trace
- devops-openshift-libraries.notifierHelper.emailNotifier
- devops-openshift-libraries.openshiftHelper.osConfigsFromParameters
- devops-openshift-libraries.openshiftHelper.osDelete
- devops-openshift-libraries.openshiftHelper.osDescribe
- devops-openshift-libraries.utils.description
- devops-openshift-libraries.utils.validateParameters
