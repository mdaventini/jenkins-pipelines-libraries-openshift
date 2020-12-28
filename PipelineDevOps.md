# PipelineDevOps.groovy
   Initial Pipeline (must be configured manually)
   Manual Pipeline for Jenkins configurations
   Internal devops pipeline to inspect jenkins configs and create
  
   Requires: @Library(['devops-openshift-libraries'])_
  
    @param logLevel String default 'INFO'
  
## Implements
   DevOpsConfig
  
## Uses:
- devops-openshift-libraries.cacHelper.gatherInstanceConfigs
- devops-openshift-libraries.cacHelper.missingPlugins
- devops-openshift-libraries.cust.console
- devops-openshift-libraries.cust.development
- devops-openshift-libraries.cust.emailMainteiner
- devops-openshift-libraries.cust.emailSubject
- devops-openshift-libraries.cust.rootGroup
- devops-openshift-libraries.doHelper.workspaceHousekeeping
- devops-openshift-libraries.dslHelper.createJob
- devops-openshift-libraries.dslHelper.createJobInfra
- devops-openshift-libraries.dslHelper.createView
- devops-openshift-libraries.dslHelper.readJobConfig
- devops-openshift-libraries.jiraHelper.showAllJiraFields
- devops-openshift-libraries.log.debug
- devops-openshift-libraries.log.error
- devops-openshift-libraries.log.info
- devops-openshift-libraries.notifierHelper.emailNotifier
- devops-openshift-libraries.utils.description
