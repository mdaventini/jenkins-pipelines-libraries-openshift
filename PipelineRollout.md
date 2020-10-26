# PipelineRollout.groovy
   Pipeline for Openshift rollout
   Rollout a config based on Jira parameters
  
   Requires: @Library(['devops-openshift-libraries'])_
  
    @param logLevel String default 'INFO'
  
## Implements
   DevOpsRollout
  
## Uses:
- devops-openshift-libraries.bitbucketHelper.bbConfigs
- devops-openshift-libraries.bitbucketHelper.checkRepoPermission
- devops-openshift-libraries.bitbucketHelper.gitCheckout
- devops-openshift-libraries.bitbucketHelper.gitListTags
- devops-openshift-libraries.bitbucketHelper.gitTagPush
- devops-openshift-libraries.cust.console
- devops-openshift-libraries.cust.defaultBitbucketServer
- devops-openshift-libraries.cust.defaultJiraSiteID
- devops-openshift-libraries.cust.emailMainteiner
- devops-openshift-libraries.cust.emailSubject
- devops-openshift-libraries.cust.validCountries
- devops-openshift-libraries.interfaceHelper.itNameConfig
- devops-openshift-libraries.interfaceHelper.itNameInstanciaDeArtefacto
- devops-openshift-libraries.interfaceHelper.readJiraIssueOrLinked
- devops-openshift-libraries.interfaceHelper.technologyOpenShift
- devops-openshift-libraries.jiraHelper.addCommentInJiraIssue
- devops-openshift-libraries.log.debug
- devops-openshift-libraries.log.error
- devops-openshift-libraries.log.info
- devops-openshift-libraries.log.trace
- devops-openshift-libraries.notifierHelper.emailNotifier
- devops-openshift-libraries.openshiftHelper.copyImageStreamSkopeo
- devops-openshift-libraries.openshiftHelper.execApplyInfra
- devops-openshift-libraries.openshiftHelper.execCreateConfigMaps
- devops-openshift-libraries.openshiftHelper.osConfigsFromParameters
- devops-openshift-libraries.openshiftHelper.rolloutVersion
- devops-openshift-libraries.openshiftHelper.tagImageStream
- devops-openshift-libraries.utils.description
- devops-openshift-libraries.utils.sshScmUrl
- devops-openshift-libraries.utils.validateParameters
