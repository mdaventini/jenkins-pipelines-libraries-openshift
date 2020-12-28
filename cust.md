# cust.groovy
   Library for customized global variables
  
## Implements
### cust.setComment
   Returns a string message for commits from jenkins
  
    @return  String message for commits from jenkins
  
### cust.setCommentSmudge
   Returns a string message for commits from jenkins without 
  
    @return  String message for commits from jenkins
  
### cust.console
   Returns a string for custom check console ...message
  
    @return  String message "Check console output at ${BUILD_URL}console"
  
### cust.emailSubject
   Returns a string for custom email Subject
  
    @return  String custom emailSubject
  
### cust.emailBody
   Returns a string for custom email Body
  
    @return  String custom emailBody
  
### cust.emailJenkins
   Returns a string with jenkins admin's email
  
    @return  String jenkins admin's email
  
### cust.emailDomain
   Returns a string with emailDomain to use in authors
  
    @return  String emailDomain to use in authors
  
### cust.repositoryManagersID
   Returns a List with known repository Managers ID
  
   @return  List with known repository Managers ID
  
### cust.repositoryManagers
   Returns a List with known repository Managers map
  
   @return  List with known repository Managers map
  
### cust.defaultRepoManagerID
   Returns a string with known repository Managers ID with DEFAULT_BIN_REPO_MANAGER_TYPE
  
   @return  String with known repository Managers ID
  
### cust.defaultNexus3ID
   Returns a string with default Nexus3 ID
  
   @return  String with known default Nexus3 ID
  
### cust.defaultArtifactoryID
   Returns a string with default Artifactory ID
  
   @return  String with known default Artifactory ID
  
### cust.sonarqubeID
   Returns a string with known sonarqube instance ID
  
   @return  String with known sonarqube instance ID
  
### cust.defaultBitbucketServer
   Returns a string with default http Bitbucket Server Url
  
    @return  String http Bitbucket Server Url
  
### cust.defaultJiraSiteID
   Returns a string with known Jira Site ID ('jira-default')
  
    @return  String with known Jira Site ID
  
### cust.defaultJiraSiteUrl
   Returns a string with known Jira Site Url ('http:
  
    @return  String with known Jira Site Url
  
### cust.webhookTitle
   Returns a string to use as Title in bitbucket webhook
  
    @return String to use as Title in bitbucket webhook
  
### cust.onlyPRExemptionsGroups
   Returns a String with 'jenkins-users' as configured in bitbucket
  
    @return  String with 'jenkins-users'
  
### cust.gitConfig
   Returns a string with configs to add before some sh calls to git commands
  
   @return  String configs to add before some sh calls to git commands
  
### cust.emailMainteiner
   Returns a string with this library mainteiner's email
  
    @return  String this library mainteiner's email
  
### cust.rootGroup
   Returns a string with resources root group
  
    @return  String resources root group
  
### cust.multibranchDsl
   Returns a string with path to resource multibranchDsl
  
    @return  String path to resource multibranchDsl
  
### cust.pipelineDsl
   Returns a string with path to resource pipelineDsl
  
    @return  String path to resource pipelineDsl
  
### cust.bitbucketLDAPCredentialsID
   Returns a string with already defined Credential ID for Bitbucket
  
    @return  String already defined Credential ID for Bitbucket
  
### cust.bitbucketSshCredentialsID
   Returns a string with already defined Credential ID for Bitbucket
  
    @return  String already defined Credential ID for Bitbucket
  
### cust.jiraLDAPCredentialsID
   Returns a string with already defined Credential ID for Jira
  
    @return  String already defined Credential ID for Jira
  
### cust.countriesMap
   Returns a list of map with known countries
  
   @return list of mapCountries
    key              String with short (ar, py, uy, np)
    short            String like key but replacing np with ar
    envProc          String with known envProc (cti_ar, cti_par, cti_uru)
    fullCountryName  String with known Countries in Jira (Argentina, Paraguay, Uruguay)
    timeZone         String with known TimeZones (America
  
### cust.shortNoCountry
   Returns the abbreviation for NoCountry
   Avoid sonarqube duplicates
  
   @return      String 'np'
  
### cust.shortArgentina
   Returns the abbreviation for Argentina
   Avoid sonarqube duplicates
  
   @return      String 'ar'
  
### cust.translateCountry
   Returns the country suffix for a given known jira country
  
    @param thisCountry  String known jira country
    @return suffix      String
  
### cust.countryEnv
   Returns the suffix -xx for a given  repo name
   See resources
  
   @param repo  String repository name
   @return      String env (default is -ar)
  
### cust.knownProcEnv
   Returns an Array with known ProC environments as [cvs modules]:[git repo suffix]
  
    @return  Array of map [key:value]
  
### cust.knownJiraCountries
   Returns a List of map with known Countries in Jira as [fullCountryName]:[countrySuffix]
  
    @return  List of map [key:value]
  
### cust.knownTimeZones
   Returns a List of map with known Countries and TimeZones [countrySuffix]:[timeZone]
  
    @return  List of map [key:value]
  
### cust.validCountries
   Returns an List with known valid countries
  
    @return  List
  
### cust.validCountriesNoNP
   Returns an List with known valid countries without -np
  
    @return  List
  
### cust.clustersMap
   Returns a list of map with known clusters
  
   @return list of clustersMap
    key     String with known interfaceHelper.technologyOpenShift()
    value   String with suffix ('openshift', 'openshift4ext', 'openshift4ixt')
  
### cust.translateTechnologyOpenShift
   Returns the cluster suffix for a given known jiraTech
  
    @param thisjiraTech  String known jiraTech
    @return suffix       String
  
### cust.validClusters
   Returns an List with known valid clusters suffix
  
    @return  List
  
### cust.validEnvironments
   Returns a List with known valid environments
  
    @return  List
  
### cust.openshiftCredentialsClassString
   Returns a List with Openshift Crendential Classes
  
    @return  List of String
  
### cust.skipPlainCredentials
   Returns a List with credential classes to skip
  
    @return  List of String
  
### cust.traitPullRequestDiscovery
   Returns a string with traits to use in IC DevOps pipelines (discover pull requests)
  
    @return  String
  
### cust.development
   Returns a string with known development environment
  
    @return  String
  
### cust.testing
   Returns a string with known testing environment
  
    @return  String
  
### cust.production
   Returns a string with known production environment
  
    @return  String
  
### cust.requiredPlugins
   Returns a list of requiredPlugins
  
    @return requiredPlugins  List
  
### cust.langProc
   Returns a String with proc
  
    @return langProc  String
  
### cust.splitPattern
   Returns a String with '\\.'
  
    @return splitPattern  String
  
### cust.splitUXFile
   Returns a String with '.
  
    @return splitPattern  String
  
### cust.defaultBranch
   Returns a String with 'develop'
  
    @return defaultBranch  String
  
### cust.branchesToBuild
   Returns a String with 'develop release
  
    @return branchesToBuild  String
  
### cust.artifactoryLiteral
   Returns the string 'artifactory'
   Avoid sonarqube duplications
  
    @return          String
  
### cust.serverVersion5
   Returns the string 'VERSION_5'
   Avoid sonarqube duplications
  
    @return          String
  
### cust.serverVersion7
   Returns the string 'VERSION_7'
   Avoid sonarqube duplications
  
    @return          String
  
## Uses:
- devops-openshift-libraries.cacHelper.artifactoryIDs
- devops-openshift-libraries.cacHelper.bitbucketIDs
- devops-openshift-libraries.cacHelper.jiraStepsSiteIDs
- devops-openshift-libraries.cacHelper.nexusIDs
- devops-openshift-libraries.cacHelper.sonarqubeIDs
- devops-openshift-libraries.interfaceHelper.openShiftJira
- devops-openshift-libraries.interfaceHelper.technologyOpenShift
- devops-openshift-libraries.utils.gitSCM
- devops-openshift-libraries.utils.slash
