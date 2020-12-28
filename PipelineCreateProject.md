# PipelineCreateProject.groovy
   Manual Pipeline for Jenkins configurations
   Is executed by any user
  
   Requires: @Library(['devops-openshift-libraries'])_
  
    @param logLevel String default 'INFO'
  
## Implements
   DevOpsCreateProject
  
## Uses:
- devops-openshift-libraries.bitbucketHelper.checkBitbucketProject
- devops-openshift-libraries.cust.bitbucketLDAPCredentialsID
- devops-openshift-libraries.cust.console
- devops-openshift-libraries.cust.defaultBitbucketServer
- devops-openshift-libraries.cust.defaultJiraSiteID
- devops-openshift-libraries.cust.development
- devops-openshift-libraries.cust.emailMainteiner
- devops-openshift-libraries.cust.emailSubject
- devops-openshift-libraries.cust.rootGroup
- devops-openshift-libraries.cust.testing
- devops-openshift-libraries.dslHelper.createFolder
- devops-openshift-libraries.dslHelper.createJob
- devops-openshift-libraries.dslHelper.createJobInfra
- devops-openshift-libraries.dslHelper.readJobConfig
- devops-openshift-libraries.interfaceHelper.knownTechnologiesICJQL
- devops-openshift-libraries.interfaceHelper.searchIssuesWithFields
- devops-openshift-libraries.jiraHelper.validEmail
- devops-openshift-libraries.log.debug
- devops-openshift-libraries.log.info
- devops-openshift-libraries.log.trace
- devops-openshift-libraries.notifierHelper.emailNotifier
- devops-openshift-libraries.utils.description
- devops-openshift-libraries.utils.validateParameters
