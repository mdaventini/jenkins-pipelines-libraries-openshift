# PipelineDeliveryDB.groovy
   Pipeline for PL
   Obtain DB scripts based on Jira parameters
  
   Requires: @Library(['devops-openshift-libraries'])_
  
    @param logLevel String default 'INFO'
  
## Implements
   DevOpsDeliveryDB
  
## Uses:
- devops-openshift-libraries.bitbucketHelper.bbConfigs
- devops-openshift-libraries.bitbucketHelper.gitCheckout
- devops-openshift-libraries.bitbucketHelper.readBranchesByJiraIssue
- devops-openshift-libraries.cust.console
- devops-openshift-libraries.cust.defaultJiraSiteID
- devops-openshift-libraries.cust.emailMainteiner
- devops-openshift-libraries.cust.emailSubject
- devops-openshift-libraries.interfaceHelper.itNameConfig
- devops-openshift-libraries.interfaceHelper.itNameInstanciaDeArtefacto
- devops-openshift-libraries.interfaceHelper.itNameSubArtefacto
- devops-openshift-libraries.interfaceHelper.searchIssuesWithFields
- devops-openshift-libraries.interfaceHelper.technologyBaseDeDatos
- devops-openshift-libraries.interfaceHelper.technologyDB
- devops-openshift-libraries.interfaceHelper.technologyPL
- devops-openshift-libraries.jiraHelper.downloadJiraAttachments
- devops-openshift-libraries.log.debug
- devops-openshift-libraries.log.error
- devops-openshift-libraries.log.info
- devops-openshift-libraries.log.trace
- devops-openshift-libraries.log.unstable
- devops-openshift-libraries.notifierHelper.emailNotifier
- devops-openshift-libraries.utils.description
- devops-openshift-libraries.utils.removeTmpDir
- devops-openshift-libraries.utils.sshScmUrl
- devops-openshift-libraries.utils.timeStamp
