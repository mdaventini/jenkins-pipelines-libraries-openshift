# PipelineCreateNewApp.groovy
   Manual Pipeline for Create 
   The  must be issueType Artefacto and the git_proyect in Jira must be scmProjectKey
   Is executed manually by any project member
  
   Requires: @Library(['devops-openshift-libraries'])_
  
    @param logLevel String default 'INFO'
  
## Implements
   DevOpsCreateNewApp
  
## Uses:
- devops-openshift-libraries.bitbucketHelper.bitbucketServerVersion
- devops-openshift-libraries.bitbucketHelper.ensureRepoWebhook
- devops-openshift-libraries.bitbucketHelper.ensureRepoWebhook7
- devops-openshift-libraries.bitbucketHelper.ensureRestrictions
- devops-openshift-libraries.cust.console
- devops-openshift-libraries.cust.defaultJiraSiteID
- devops-openshift-libraries.cust.emailMainteiner
- devops-openshift-libraries.cust.emailSubject
- devops-openshift-libraries.cust.serverVersion5
- devops-openshift-libraries.cust.serverVersion7
- devops-openshift-libraries.doHelper.prepareDevOpsConfigs
- devops-openshift-libraries.dslHelper.createJobsInFolder
- devops-openshift-libraries.interfaceHelper.searchValidIssue
- devops-openshift-libraries.interfaceHelper.technologyOpenShift
- devops-openshift-libraries.jiraHelper.validEmail
- devops-openshift-libraries.log.debug
- devops-openshift-libraries.log.error
- devops-openshift-libraries.log.info
- devops-openshift-libraries.notifierHelper.emailNotifier
- devops-openshift-libraries.openshiftHelper.callDevOpsCreateInfraConfig
- devops-openshift-libraries.utils.description
- devops-openshift-libraries.utils.validateParameters
