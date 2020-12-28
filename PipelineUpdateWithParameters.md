# PipelineUpdateWithParameters.groovy
   Pipeline for Openshift config and infra with parameters
   Exec config and infra based on given parameters
  
   Requires: @Library(['devops-openshift-libraries'])_
  
    @param logLevel String default 'INFO'
  
## Implements
   DevOpsUpdateWithParameters
  
## Uses:
- devops-openshift-libraries.cust.console
- devops-openshift-libraries.cust.emailMainteiner
- devops-openshift-libraries.cust.emailSubject
- devops-openshift-libraries.cust.validClusters
- devops-openshift-libraries.cust.validCountriesNoNP
- devops-openshift-libraries.interfaceHelper.cfGitProject
- devops-openshift-libraries.interfaceHelper.cfGitTag
- devops-openshift-libraries.log.debug
- devops-openshift-libraries.log.trace
- devops-openshift-libraries.notifierHelper.emailNotifier
- devops-openshift-libraries.openshiftHelper.execApplyInfra
- devops-openshift-libraries.openshiftHelper.execCreateConfigMaps
- devops-openshift-libraries.openshiftHelper.osConfigsFromParameters
- devops-openshift-libraries.utils.description
- devops-openshift-libraries.utils.validateParameters
