# PipelineDelivery.groovy
   Manual Delivery Pipeline
   for Multibranch Pipelines
   Based on a Jira Issue (Must contain GIT_PROJECT and GIT_REPO)
   createRelease parameter performs release
   Discover build configurations from repository
  
   Requires: @Library(['devops-openshift-libraries'])_
  
    @param logLevel String default 'INFO'
  
## Implements
### PipelineDelivery
  
## Uses:
- devops-openshift-libraries.cust.console
- devops-openshift-libraries.cust.defaultJiraSiteID
- devops-openshift-libraries.cust.emailMainteiner
- devops-openshift-libraries.cust.emailSubject
- devops-openshift-libraries.cust.shortArgentina
- devops-openshift-libraries.doHelper.appDelivery
- devops-openshift-libraries.doHelper.prepareDevOpsConfigs
- devops-openshift-libraries.interfaceHelper.discoverJiraKeyFromBranch
- devops-openshift-libraries.interfaceHelper.itNameArtefacto
- devops-openshift-libraries.interfaceHelper.itNameInstanciaDeArtefacto
- devops-openshift-libraries.interfaceHelper.itNameSubArtefacto
- devops-openshift-libraries.interfaceHelper.searchValidIssue
- devops-openshift-libraries.log.debug
- devops-openshift-libraries.log.error
- devops-openshift-libraries.log.info
- devops-openshift-libraries.log.trace
- devops-openshift-libraries.notifierHelper.emailNotifier
- devops-openshift-libraries.openshiftHelper.buildSourceToImage
- devops-openshift-libraries.openshiftHelper.createDraftInfra
- devops-openshift-libraries.openshiftHelper.execApplyInfra
- devops-openshift-libraries.openshiftHelper.execCreateConfigMaps
- devops-openshift-libraries.openshiftHelper.featureNameSpace
- devops-openshift-libraries.openshiftHelper.osConfigsFromParameters
- devops-openshift-libraries.openshiftHelper.rolloutVersion
- devops-openshift-libraries.utils.description
- devops-openshift-libraries.utils.doubleQuote
- devops-openshift-libraries.utils.slash
