# PipelineCvs2Git.groovy
   Manual Cvs2Git Pipeline
  
   Requires: @Library(['devops-openshift-libraries'])_
  
    @param logLevel String default 'INFO'
  
## Implements
   DevOpsCvs2Git
  
## Uses:
- devops-openshift-libraries.bitbucketHelper.bbConfigs
- devops-openshift-libraries.bitbucketHelper.checkBitbucketProject
- devops-openshift-libraries.bitbucketHelper.checkBitbucketRepoProject
- devops-openshift-libraries.cust.console
- devops-openshift-libraries.cust.defaultBitbucketServer
- devops-openshift-libraries.cust.emailMainteiner
- devops-openshift-libraries.cust.emailSubject
- devops-openshift-libraries.cvs2GitHelper.changesForBitbucket
- devops-openshift-libraries.cvs2GitHelper.createBitbucketRepo
- devops-openshift-libraries.cvs2GitHelper.createCvsTemp
- devops-openshift-libraries.cvs2GitHelper.createOptionsFile
- devops-openshift-libraries.cvs2GitHelper.createReadme
- devops-openshift-libraries.cvs2GitHelper.cvsCheckout
- devops-openshift-libraries.cvs2GitHelper.cvsrootOrigin
- devops-openshift-libraries.cvs2GitHelper.execCvs2Git
- devops-openshift-libraries.cvs2GitHelper.translateCvs2Git
- devops-openshift-libraries.jiraHelper.validEmail
- devops-openshift-libraries.log.debug
- devops-openshift-libraries.log.info
- devops-openshift-libraries.notifierHelper.emailNotifier
- devops-openshift-libraries.utils.description
- devops-openshift-libraries.utils.sshScmUrl
