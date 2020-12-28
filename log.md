# log.groovy
  
   Library for logging messages and errors
  
## Implements
### log.info
   Prints message with [PIPELINEINFO] prefix only if logLevel is 'TRACE', 'DEBUG' or 'INFO'
  
    @param message   String
    @param logLevel  String default 'INFO'
  
### log.warning
   Prints message with [PIPELINEINFO][WARNING] prefix
  
    @param message   String
  
### log.debug
   Prints message with [PIPELINEDEBUG] prefix only if logLevel is 'TRACE' or 'DEBUG'
  
    @param message   String
    @param logLevel  String default 'INFO'
  
### log.trace
   Prints message with [PIPELINETRACE] prefix only if logLevel is 'TRACE'
  
    @param message   String
    @param logLevel  String default 'INFO'
  
### log.error
   Prints message with [PIPELINEERROR] prefix, adds the message to currentBuild.description and fails the pipeline
  
    @param message   String
  
### log.unstable
   Prints message with [PIPELINEINFO][WARNING] prefix, adds the message to currentBuild.description and mark the pipeline as UNSTABLE
  
    @param message   String
  
## Uses:
- devops-openshift-libraries.cust.splitPattern
- devops-openshift-libraries.utils.description
