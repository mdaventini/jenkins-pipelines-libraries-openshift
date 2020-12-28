# interfaceHelper.groovy
   Library for interface with other tools
  
## Implements
### interfaceHelper.cfCluster
   Returns a string with 'CLUSTER'
   Avoid sonarqube duplicates
  
    @return  String  'CLUSTER'
  
### interfaceHelper.cfGitProject
   Returns a string with 'GIT_PROJECT'
   Avoid sonarqube duplicates
  
    @return  String  'GIT_PROJECT'
  
### interfaceHelper.cfGitTag
   Returns a string with 'GIT_TAG'
   Avoid sonarqube duplicates
  
    @return  String  'GIT_TAG'
  
### interfaceHelper.openShiftJira
   Returns a string with 'OpenShift 
   Avoid sonarqube duplicates
  
    @return  String  'OpenShift 
  
### interfaceHelper.openShift4Jira
   Returns a List with 'OpenShift4Int' 'OpenShift4Ext'
   Avoid sonarqube duplicates
  
    @return  List
  
### interfaceHelper.devopsJira
   Returns a string with 'devops'
   Avoid sonarqube duplicates
  
    @return  String  'devops'
  
### interfaceHelper.technologyOpenShift
   Returns a List with technology or Tipo as configured in jira
   customfield_11501.value or customfield_10219.value
  
    @return  List of String
  
### interfaceHelper.technologyComponentes
   Returns a string with 'componentes'
   customfield_11501.value
  
    @return  String
  
### interfaceHelper.knownTechnologies
   Returns a List with knownTechnologies as configured in jira
   customfield_11501.value or customfield_10219.value
  
    @return  List of String
  
### interfaceHelper.knownTechnologiesOSJQL
   Returns a String with knownTechnologiesOSJQL
   customfield_11501.value or customfield_10219.value
  
    @return String knownTechnologiesOSJQL
  
### interfaceHelper.knownTechnologiesICJQL
   Returns a String with knownTechnologiesICJQL
   customfield_11501.value
  
    @return String knownTechnologiesICJQL
  
### interfaceHelper.technologyBaseDeDatos
   Returns a String with technology as configured in jira
   customfield_11501.value
  
    @return String 'Base de Datos'
  
### interfaceHelper.technologyPL
   Returns a String with Tipo as configured in jira
   customfield_10219.value
  
    @return String 'PL'
  
### interfaceHelper.technologyDB
   Returns a List with technology or Tipo as configured in jira
   customfield_11501.value or customfield_10219.value
  
    @return  List of String
  
### interfaceHelper.knownTechnologiesDBJQL
   Returns a String with knownTechnologiesDBJQL
   customfield_11501.value or customfield_10219.value
  
    @return String knownTechnologiesDBJQL
  
### interfaceHelper.customJiraFields
   Returns a list of map with custom Jira fields
  
   @return list of customJiraFields
    key              String with field name
    value            String name in jira
  
### interfaceHelper.itNameArtefacto
   Returns a String with 'Artefacto'
   Avoid sonarqube duplicates
  
   @return String with 'Artefacto'
  
### interfaceHelper.itNameInstanciaDeArtefacto
   Returns a String with 'Instancia de Artefacto'
   Avoid sonarqube duplicates
  
   @return String with 'Instancia de Artefacto'
  
### interfaceHelper.itNameSubArtefacto
   Returns a String with 'Sub-Artefacto'
   Avoid sonarqube duplicates
  
   @return String with 'Sub-Artefacto'
  
### interfaceHelper.itNameConfig
   Returns a String with 'Config'
   Avoid sonarqube duplicates
  
   @return String with 'Config'
  
### interfaceHelper.readJiraIssue
   Reads a jira issue and parses to a map with required data
   Throws error if parse fails
  
    @param jiraIssue  String Jira Issue i.e LIC-421
    @param logLevel   String default 'INFO'
    @return jiraData  Map
  
### interfaceHelper.readJiraIssueOrLinked
   Reads a jira issue, validating an issuetype, when required looks for a linked issuetype
   Throws error when
       jiraIssue is not found or linked is not found
       issuetype.name is not in issuetypeNames
  
    @param jiraIssue       String Jira Issue i.e LIC-421
    @param issuetypeNames  List with valid x issuetype.name
    @param linkedIssueType Map [whenIssuetypeName: lookForIssuetypeName]
    @param reqTechnology   List with valid x technology
    @param logLevel        String default 'INFO'
    @return validJiraData  Map
  
### interfaceHelper.flatAttachments
   Flatterns attachment
  
    @param jiraAttachment    List attachment
    @param logLevel          String default 'INFO'
    @return flatAttachments  Map
  
### interfaceHelper.flatCountries
   Flatterns and validates customfield_10114.value to countries
   Throws
       error if required configuration is invalid
       unstable if no country selected
  
    @param allCountries   List customfield_10114.value
    @param logLevel       String default 'INFO'
    @return flatCountries Map
  
### interfaceHelper.flatRawData
   Flat to a map by issuetype and technology
   Throws error when jiraData is not valid
  
    @param itData           Map raw data
    @param validTechnology  List with valid x technology
    @param logLevel         String default 'INFO'
    @return flatJiraData    Map
  
### interfaceHelper.validateJiraData
   Flat to a map and validates by issuetype and technology
   Throws error when jiraData is not valid
  
    @param flatJiraData     Map flat data
    @param validTechnology  List with valid x technology
    @param logLevel         String default 'INFO'
    @return flatJiraData   Map
  
### interfaceHelper.flatArtefacto
   Flatterns itData for 'Artefacto' and 'Instancia de Artefacto'
   Throws error if required configuration is invalid
  
    @param itData         Map raw data
    @param flatJiraData   Map flat data
    @param logLevel       String default 'INFO'
    @return flatJiraData  Map updated
  
### interfaceHelper.flatParams
   Flatterns customfield_11236 or customfield_11075 to flatDeployParams
   Throws error if required configuration is invalid
  
    @param deployParams       String
    @param logLevel           String default 'INFO'
    @return flatDeployParams  Map
  
### interfaceHelper.validateParams
   Validate required parameters depending on technology and issuetypeName
   Throws error if required configuration is invalid
  
    @param flatJiraData       Map
    @param logLevel           String default 'INFO'
  
### interfaceHelper.searchIssuesWithFields
   Search in jira using jiraJql + reqTechnology
   Return all jiraIssues found with desired fields
  
    @param jiraJql        String jql to search
    @param reqTechnology  List with valid x technology
    @param jiraSite       String known jira site id
    @param logLevel       String default 'INFO'
    @return jiraIssues    Map
  
### interfaceHelper.searchValidIssue
   Search in jira using jql with key and return all valid jiraIssues found with desired fields
   As key is given will return one valid issue
   Throw error when
     there is more than 1 issue meeting criteria
     no issue meeting criteria
  
    @param jiraJql        String jql to search
    @param reqTechnology  List with valid x technology
    @param jiraSite       String known jira site id
    @param logLevel       String default 'INFO'
    @return jiraIssue     Map
  
### interfaceHelper.discoverJiraKeyFromBranch
   Split branchName and return JiraId
  
    @param branchName  String from BRANCH_NAME
    @param logLevel    String default 'INFO'
    @return jiraKey    String
  
## Uses:
- devops-openshift-libraries.cust.defaultJiraSiteID
- devops-openshift-libraries.cust.shortNoCountry
- devops-openshift-libraries.cust.translateCountry
- devops-openshift-libraries.cust.translateTechnologyOpenShift
- devops-openshift-libraries.jiraHelper.extJiraGetIssue
- devops-openshift-libraries.jiraHelper.searchJiraIssues
- devops-openshift-libraries.log.debug
- devops-openshift-libraries.log.error
- devops-openshift-libraries.log.info
- devops-openshift-libraries.log.trace
- devops-openshift-libraries.log.unstable
- devops-openshift-libraries.utils.comma
- devops-openshift-libraries.utils.description
- devops-openshift-libraries.utils.hyphen
- devops-openshift-libraries.utils.slash
