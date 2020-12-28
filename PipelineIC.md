# PipelineIC.groovy
   Continuos Integration Pipeline
   for Multibranch Pipelines
  
   Requires: @Library(['devops-openshift-libraries'])_
  
    @param logLevel String default 'INFO'
  
## Implements
### PipelineIC
  
## Uses:
- devops-openshift-libraries.cust.console
- devops-openshift-libraries.cust.emailMainteiner
- devops-openshift-libraries.cust.emailSubject
- devops-openshift-libraries.doHelper.prepareBuildConfigsWithDefaults
- devops-openshift-libraries.doHelper.sanitySteps
- devops-openshift-libraries.log.debug
- devops-openshift-libraries.log.info
- devops-openshift-libraries.log.trace
- devops-openshift-libraries.notifierHelper.emailNotifier
- devops-openshift-libraries.utils.description
- devops-openshift-libraries.utils.prefixPR
