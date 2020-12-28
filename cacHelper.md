# cacHelper.groovy
   Helper Library for configuration as code
  
## Implements
### cacHelper.globalLibraries
   Returns a map with org.jenkinsci.plugins.workflow.libs.GlobalLibraries configuration in this instance
  
    @param logLevel  String default 'INFO'
    @return          Map globalLibraries name and version configured
  
### cacHelper.installedJDKs
   Returns a map with class hudson.model.JDK$DescriptorImpl configuration in this instance
  
    @param logLevel  String default 'INFO'
    @return          Map jJDKs name and home
  
### cacHelper.installedMVNs
   Returns a map with class hudson.tasks.Maven.MavenInstallation configuration in this instance
  
    @param logLevel  String default 'INFO'
    @return          Map MVNs name and home
  
### cacHelper.installedNodeJSs
   Returns a map with class jenkins.plugins.nodejs.tools.NodeJSInstallation.DescriptorImpl configuration in this instance
  
    @param logLevel  String default 'INFO'
    @return          Map NodeJs name and home
  
### cacHelper.installedOSClients
   Returns a map with class com.openshift.jenkins.plugins.OpenShiftClientTools$DescriptorImpl configuration in this instance
  
    @param logLevel  String default 'INFO'
    @return          Map jOCs name and home
  
### cacHelper.installedJGitApacheTool
   Returns a map with class org.jenkinsci.plugins.gitclient.JGitApacheTool$DescriptorImpl configuration in this instance
  
    @param logLevel  String default 'INFO'
    @return          Map jGitAs name and home
  
### cacHelper.installedSonarScanner
   Returns a map with class hudson.plugins.sonar.SonarRunnerInstallation$DescriptorImpl configuration in this instance
  
    @param logLevel  String default 'INFO'
    @return          Map jSonarScanners name and home
  
### cacHelper.bitbucketIDs
   Returns a map with com.cloudbees.jenkins.plugins.bitbucket.endpoints.BitbucketEndpointConfiguration configuration in this instance
  
    @param logLevel  String default 'INFO'
    @return          Map bitbucket instance IDs configured
  
### cacHelper.sonarqubeIDs
   Returns a map with hudson.plugins.sonar.SonarGlobalConfiguration configuration in this instance
  
    @param logLevel  String default 'INFO'
    @return          Map sonarqube instance IDs configured
  
### cacHelper.artifactoryIDs
   Returns a map with org.jfrog.hudson.ArtifactoryBuilder$DescriptorImpl configuration in this instance
  
    @param logLevel  String default 'INFO'
    @return          Map artifactory server IDs configured
  
### cacHelper.nexusIDs
   Returns a map with org.sonatype.nexus.ci.config.GlobalNexusConfiguration configuration in this instance
  
    @param logLevel  String default 'INFO'
    @return          Map nexus server IDs configured
  
### cacHelper.mavenSettingsConfigIDs
   Returns a map with MavenSettingsConfig
  
    @param logLevel  String default 'INFO'
    @return          Map MavenSettingsConfig
  
### cacHelper.globalCredentialsIDs
   Returns a map with SystemCredentialsProvider IDs
  
    @param logLevel  String default 'INFO'
    @return          Map globalCredentialsConfig
  
### cacHelper.groovyScriptIDs
   Returns a map with GroovyScript
  
    @param logLevel  String default 'INFO'
    @return          Map id, name
  
### cacHelper.jiraSiteIDs
   Returns a map with jiraSite
  
    @param logLevel  String default 'INFO'
    @return          Map name, url, credentialsId
  
### cacHelper.jiraStepsSiteIDs
   Returns a map with jiraSite
  
    @param logLevel  String default 'INFO'
    @return          Map name, url, credentialsId
  
### cacHelper.openShiftClustersIDs
   Returns a map with openShiftClusters
  
    @param logLevel  String default 'INFO'
    @return          Map openShiftClusters
  
### cacHelper.installedPlugins
   Returns a list of map installedPlugins
  
    @param logLevel           String default 'INFO'
    @return installedPlugins  Map
  
### cacHelper.missingPlugins
   Returns a list of missingPlugins
  
    @param logLevel         String default 'INFO'
    @return missingPlugins  List
  
### cacHelper.gatherInstanceConfigs
   Gathers instance configurations
  
    @param logLevel          String default 'INFO'
    @return instanceConfigs  Map
  
### cacHelper.literalAutoInstalled
   Returns the string 'Automatically installed by Jenkins'
   Avoid sonarqube duplications
  
    @return          String
  
### cacHelper.literalALL
   Returns the string 'ALL'
   Avoid sonarqube duplications
  
    @return          String
  
## Uses:
- devops-openshift-libraries.cust.defaultRepoManagerID
- devops-openshift-libraries.cust.openshiftCredentialsClassString
- devops-openshift-libraries.cust.requiredPlugins
- devops-openshift-libraries.cust.skipPlainCredentials
- devops-openshift-libraries.log.debug
- devops-openshift-libraries.log.info
- devops-openshift-libraries.log.trace
- devops-openshift-libraries.log.warning
- devops-openshift-libraries.utils.gitSCM
