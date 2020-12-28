# notifierHelper.groovy
  
   Library for notifications
  
## Implements
### notifierHelper.emailNotifier
   Sends mail using emailext
  
    @param eSubject            String Bitbucket http URL
    @param eBody               String project key in Bitbucket
    @param eRecipientProviders List
    @param eTo                 String email
    @param logLevel            String default 'INFO'
  
## Uses:
- devops-openshift-libraries.log.debug
