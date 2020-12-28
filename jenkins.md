# Required jenkins configurations
Each jenkins instance should be updated to LTS version and all installed plugins updated.

## Required Plugins

### Management Plugins
- [Audit Trail](https://plugins.jenkins.io/audit-trail/)
- [Configuration as Code](https://plugins.jenkins.io/configuration-as-code/)
- [LDAP](https://plugins.jenkins.io/ldap/)
- [Role-based Authorization Strategy](https://plugins.jenkins.io/role-strategy/)

### Tools Plugins
- [NodeJS](https://plugins.jenkins.io/nodejs/)
- [OpenShift Client](https://plugins.jenkins.io/openshift-client/)

### Integration with other corporate tools plugins
- [Artifactory](https://plugins.jenkins.io/artifactory/)
- [Bitbucket](https://plugins.jenkins.io/bitbucket)
- [Jira](https://plugins.jenkins.io/jira/)
- [JIRA Pipeline Steps](https://plugins.jenkins.io/jira-steps/)
- [Nexus Platform](https://plugins.jenkins.io/nexus-jenkins-plugin/)
- [SonarQube Scanner](https://plugins.jenkins.io/sonar/)

### DevOps pipelines Plugins
- [Config File Provider](https://plugins.jenkins.io/config-file-provider/)
- [Credentials](https://plugins.jenkins.io/credentials/)
- [SSH Credentials](https://plugins.jenkins.io/ssh-credentials/)
- [Job DSL](https://plugins.jenkins.io/job-dsl/)
- [SSH Agent](https://plugins.jenkins.io/ssh-agent/)
- [Extended Choice Parameter](https://plugins.jenkins.io/extended-choice-parameter/)
- [Email Extension](https://plugins.jenkins.io/email-ext/)
- [File Operations](https://plugins.jenkins.io/file-operations/)
- [HTTP Request](https://plugins.jenkins.io/http_request/)
- [Pipeline: Build Step](https://plugins.jenkins.io/pipeline-build-step/)
- [Pipeline: Basic Steps](https://plugins.jenkins.io/workflow-basic-steps/)
- [Pipeline Utility Steps](https://plugins.jenkins.io/pipeline-utility-steps/)
- [Pipeline: Multibranch with defaults](https://plugins.jenkins.io/pipeline-multibranch-defaults/)
- [Pipeline: Shared Groovy Libraries](https://plugins.jenkins.io/workflow-cps-global-lib/)
- [Pipeline Maven Integration](https://plugins.jenkins.io/pipeline-maven/)
- [Pipeline NPM Integration](https://plugins.jenkins.io/pipeline-npm/)

## Configurations
### Jenkins
```
jenkins:
  agentProtocols:
  - "Ping"
  crumbIssuer:
    standard:
      excludeClientIPFromCrumb: false
  disableRememberMe: false
  labelAtoms:
  - name: "Jenkins_Master"
  - name: "java"
  - name: "master"
  - name: "maven"
  - name: "nodejs"
  labelString: "Jenkins_Master master java nodejs"
  markupFormatter: "plainText"
  mode: NORMAL
  myViewsTabBar: "standard"
  numExecutors: 4
  primaryView:
    all:
      name: "all"
  projectNamingStrategy: "standard"
  quietPeriod: 5
  remotingSecurity:
    enabled: true
  scmCheckoutRetryCount: 0
  slaveAgentPort: 50000
  updateCenter:
    sites:
    - id: "default"
      url: "http://updates.jenkins-ci.org/update-center.json"
  viewsTabBar: "standard"
```
### Security
```
security:
  apiToken:
    creationOfLegacyTokenEnabled: false
    tokenGenerationOnCreationEnabled: false
    usageStatisticsEnabled: true
  globalJobDslSecurityConfiguration:
    useScriptSecurity: false
  sSHD:
    port: -1
  scriptApproval:
    approvedSignatures:
```
### Tool
```
---
tool:
  git:
    installations:
    - home: "git"
      name: "Default"
  jdk:
    installations:
    - name: "JDK11"
      properties:
      - installSource:
          installers:
          - zip:
              subdir: "jdk-11.0.8"
              url: "http://trigo.claro.amx:8081/repository/local-claro-tools/java/jdk-11.0.8_linux-x64_bin.tar.gz"
    - name: "JDK8"
      properties:
      - installSource:
          installers:
          - zip:
              subdir: "jdk1.8.0_261"
              url: "http://trigo.claro.amx:8081/repository/local-claro-tools/java/jdk-8u261-linux-x64.tar.gz"
  maven:
    installations:
    - name: "M3-LTS"
      properties:
      - installSource:
          installers:
          - maven:
              id: "3.6.2"
  nodejs:
    installations:
    - name: "NODEJS-LTS"
      properties:
      - installSource:
          installers:
          - nodeJSInstaller:
              id: "12.10.0"
              npmPackages: "sonarqube-scanner"
              npmPackagesRefreshHours: 72
          - zip:
              subdir: "node-v12.18.3-linux-x64"
              url: "http://trigo.claro.amx:8081/repository/local-claro-tools/nodejs/node-v12.18.3-linux-x64.tar.xz"
  oc:
    installations:
    - home: "/apps/os_3.11_64"
      name: "OC311"
    - name: "OS-311"
      properties:
      - installSource:
          installers:
          - zip:
              subdir: "os_3.11_64"
              url: "http://trigo.claro.amx:8081/repository/local-claro-tools/oc/os_3.11_64.tar.gz"
  pipelineMaven:
    triggerDownstreamUponResultAborted: false
    triggerDownstreamUponResultFailure: false
    triggerDownstreamUponResultNotBuilt: false
    triggerDownstreamUponResultSuccess: true
    triggerDownstreamUponResultUnstable: false
  sonarRunnerInstallation:
    installations:
    - name: "SONARSCANNER-LTS"
      properties:
      - installSource:
          installers:
          - zip:
              subdir: "sonar-scanner-4.4.0.2170-linux"
              url: "http://trigo.claro.amx:8081/repository/local-claro-tools/sonarqube-scanner/sonar-scanner-cli-4.4.0.2170-linux.zip"
```
### Other
```
---
unclassified:
  artifactoryBuilder:
    artifactoryServers:
    - artifactoryUrl: "http://ribera.claro.amx:8081/artifactory"
      bypassProxy: false
      connectionRetry: 3
      deployerCredentialsConfig:
        overridingCredentials: false
        password: "{AQAAABAAAAAQe6nFRDCkFhVmpAO/55lG7Yupooo15KgHPxSVpEf4GeA=}"
        username: "****"
      deploymentThreads: 3
      resolverCredentialsConfig:
        overridingCredentials: false
        username: "****"
      serverId: "artifactory-ribera-default"
      timeout: 300
    jfrogPipelinesServer:
      bypassProxy: false
      connectionRetries: 3
      credentialsConfig:
        ignoreCredentialPluginDisabled: true
        overridingCredentials: false
        password: "{AQAAABAAAAAQRw/25OJPgHJ0zp72gRxs0TWoxhAU4n2ouG4aIH7YW7M=}"
        username: "****"
      timeout: 300
    useCredentialsPlugin: true
  audit-trail:
    logBuildCause: true
    pattern: ".*/(?:configSubmit|doDelete|postBuildResult|enable|disable|cancelQueue|stop|toggleLogKeep|doWipeOutWorkspace|createItem|createView|toggleOffline|cancelQuietDown|quietDown|restart|exit|safeExit)/?.*"
  badgePlugin:
    disableFormatHTML: false
  bitbucketEndpointConfiguration:
    endpoints:
    - bitbucketCloudEndpoint:
        enableCache: false
        manageHooks: false
        repositoriesCacheDuration: 0
        teamCacheDuration: 0
    - bitbucketServerEndpoint:
        callCanMerge: false
        callChanges: false
        credentialsId: "ldap_jenkins"
        displayName: "bitbucket-default"
        manageHooks: true
        serverUrl: "http://tapias.claro.amx:7990"
        serverVersion: VERSION_6
  buildDiscarders:
    configuredBuildDiscarders:
    - "jobBuildDiscarder"
    - simpleBuildDiscarder:
        discarder:
          logRotator:
            numToKeepStr: "30"
  buildStepOperation:
    enabled: false
  casCGlobalConfig:
    configurationPath: "http://trigo.claro.amx:8081/repository/local-claro-tools/jenkins-casoc/casoc_tools.yaml"
  defaultFolderConfiguration:
    healthMetrics:
    - worstChildHealthMetric:
        recursive: true
  email-ext:
    adminRequiredForTemplateTesting: false
    allowUnregisteredEnabled: false
    charset: "UTF-8"
    debugMode: false
    defaultBody: |-
      $PROJECT_NAME - Build # $BUILD_NUMBER - $BUILD_STATUS:

      Check console output at $BUILD_URL to view the results.
    defaultContentType: "text/plain"
    defaultSubject: "$PROJECT_NAME - Build # $BUILD_NUMBER - $BUILD_STATUS!"
    defaultTriggerIds:
    - "hudson.plugins.emailext.plugins.trigger.FailureTrigger"
    mailAccount:
      address: "jenkins-jenkins01adl-7990 <nobody@nowhere>"
      smtpPassword: "{AQAAABAAAAAQERaYYvp5w1aHIlDvVIY6zG5YWhd2tZm0gY7U6cKVirc=}"
    maxAttachmentSize: -1
    maxAttachmentSizeMb: -1
    precedenceBulk: false
    smtpPassword: "{AQAAABAAAAAQERaYYvp5w1aHIlDvVIY6zG5YWhd2tZm0gY7U6cKVirc=}"
    watchingEnabled: false
  fTPPublisher:
    flattenFilesSelectedByDefault: false
  fingerprints:
    fingerprintCleanupDisabled: false
    storage: "file"
  gitHubConfiguration:
    apiRateLimitChecker: ThrottleForNormalize
  gitHubPluginConfig:
    hookUrl: "http://jenkins01adl.claro.amx:7990/github-webhook/"
  gitSCM:
    allowSecondFetch: false
    createAccountBasedOnEmail: false
    disableGitToolChooser: false
    globalConfigEmail: "jenkins@claro.com.ar"
    globalConfigName: "jenkins"
    hideCredentials: false
    showEntireCommitSummaryInChanges: false
    useExistingAccountWithSameEmail: false
  githubPullRequests:
    actualiseOnFactory: false
  globalConfigFiles:
    configs:
    - groovyScript:
        comment: "Using DEBUG"
        content: |
          //@Library(['devops-pipelines', 'devops-libraries'])_
          @Library(['devops-openshift-libraries'])_
          PipelineDelivery(false, 'INFO')
        id: "PipelineDelivery"
        name: "PipelineDelivery"
        providerId: "org.jenkinsci.plugins.configfiles.groovy.GroovyScript"
    - groovyScript:
        comment: "DEBUG"
        content: |-
          @Library(['devops-pipelines', 'devops-libraries'])_
          PipelineDeliveryOS('DEBUG')
        id: "PipelineDeliveryOS"
        name: "PipelineDeliveryOS"
        providerId: "org.jenkinsci.plugins.configfiles.groovy.GroovyScript"
    - groovyScript:
        comment: "INFO"
        content: |-
          //@Library(['devops-pipelines', 'devops-libraries'])_
          @Library(['devops-openshift-libraries'])_
          PipelineIC('INFO')
        id: "PipelineIC"
        name: "PipelineIC"
        providerId: "org.jenkinsci.plugins.configfiles.groovy.GroovyScript"
    - groovyScript:
        comment: "Use INFO"
        content: |-
          //@Library(['devops-pipelines', 'devops-libraries'])_
          @Library(['devops-openshift-libraries'])_
          PipelineDelivery(true, 'INFO')
        id: "PipelineReleaseDelivery"
        name: "PipelineReleaseDelivery"
        providerId: "org.jenkinsci.plugins.configfiles.groovy.GroovyScript"
    - npm:
        comment: "Using http://madariaga1.claro.amx:38081/"
        content: "; Force npm to always require authentication when accessing the\
          \ registry, even for GET requests.\n; always-auth = false\n\n; The location\
          \ of npm's cache directory. See npm-cache (https://docs.npmjs.com/cli/cache)\n\
          ; Default: Windows: %AppData%\\npm-cache, Posix: ~/.npm\ncache = ^${JENKINS_HOME}/.npm-lab\n\
          \n; What level of logs to report. On failure, all logs are written to npm-debug.log\
          \ in the current working directory.\n; Any logs of a higher level than the\
          \ setting are shown. The default is \"warn\", which shows warn and error\
          \ output.\n; Default: \"warn\"\n; Values: \"silent\", \"error\", \"warn\"\
          , \"http\", \"info\", \"verbose\", \"silly\"\nloglevel = \"info\"\n\n; The\
          \ config file to read for global config options.\n; Default: {prefix}/etc/npmrc\n\
          ; globalconfig = \n\n; The location to install global items. If set on the\
          \ command line, then it forces non-global commands to run in the specified\
          \ folder.\n; Default: see npm-folders (https://docs.npmjs.com/files/folders)\n\
          ; prefix = \n\n; The base URL of the npm package registry.\n; Default: https://registry.npmjs.org/\n\
          ; registry = \n\n; If set to false, then ignore npm-shrinkwrap.json files\
          \ when installing.\n; Default: true\n; shrinkwrap ="
        id: "npmrc-nexus3-madariaga"
        name: "npmrc-nexus3-madariaga"
        providerId: "jenkins.plugins.nodejs.configfiles.NPMConfig"
        registries:
        - credentialsId: "deployer_nexus3"
          hasScopes: false
          url: "http://madariaga1.claro.amx:38081/repository/virtual-claro-npm/"
    - npm:
        comment: "Using http://trigo.claro.amx:8081/"
        content: "; Force npm to always require authentication when accessing the\
          \ registry, even for GET requests.\n; always-auth = false\n\n; The location\
          \ of npm's cache directory. See npm-cache (https://docs.npmjs.com/cli/cache)\n\
          ; Default: Windows: %AppData%\\npm-cache, Posix: ~/.npm\ncache = ^${JENKINS_HOME}/.npm\n\
          \n; What level of logs to report. On failure, all logs are written to npm-debug.log\
          \ in the current working directory.\n; Any logs of a higher level than the\
          \ setting are shown. The default is \"warn\", which shows warn and error\
          \ output.\n; Default: \"warn\"\n; Values: \"silent\", \"error\", \"warn\"\
          , \"http\", \"info\", \"verbose\", \"silly\"\nloglevel = \"info\"\n\n; The\
          \ config file to read for global config options.\n; Default: {prefix}/etc/npmrc\n\
          ; globalconfig = \n\n; The location to install global items. If set on the\
          \ command line, then it forces non-global commands to run in the specified\
          \ folder.\n; Default: see npm-folders (https://docs.npmjs.com/files/folders)\n\
          ; prefix = \n\n; The base URL of the npm package registry.\n; Default: https://registry.npmjs.org/\n\
          ; registry = \n\n; If set to false, then ignore npm-shrinkwrap.json files\
          \ when installing.\n; Default: true\n; shrinkwrap ="
        id: "npmrc-nexus3-trigo"
        name: "npmrc-nexus3-trigo"
        providerId: "jenkins.plugins.nodejs.configfiles.NPMConfig"
        registries:
        - credentialsId: "deployer_trigo"
          hasScopes: false
          url: "http://trigo.claro.amx:8081/repository/virtual-claro-npm/"
    - mavenSettings:
        comment: "settings.xml using artifactory in ribera"
        content: "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<settings xmlns=\"http://maven.apache.org/SETTINGS/1.0.0\"\
          \ xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"\
          http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd\"\
          >\n    <!--<localRepository>^${user.home}/artifactory/.m2/repository</localRepository>-->\n\
          \  <localRepository>^${JENKINS_HOME}/artifactory/.m2/repository</localRepository>\n\
          \    <profiles>\n\t\t<profile>\n\t\t\t<id>devops</id>\n\t\t\t<activation>\n\
          \t\t\t\t<activeByDefault>true</activeByDefault>\n\t\t\t</activation>\n\t\
          \t\t<properties>\n\t\t\t\t<!--Claro-->\n\t\t\t\t<bitbucketServer>tapias.claro.amx</bitbucketServer>\n\
          \t\t\t\t<docHome>http://gracia.claro.amx:8090</docHome>\n\t\t\t\t<issueManagementHome>http://savigne.claro.amx:8080/projects</issueManagementHome>\n\
          \                <ciHome>http://jenkins01adl.claro.amx:7990</ciHome>\n \
          \               <repoManager>http://ribera.claro.amx:8081/artifactory</repoManager>\n\
          \                <claroRegistry>http://trigo.claro.amx:8081/repository/virtual-claro-npm/</claroRegistry>\n\
          \t\t\t</properties>\n\t\t</profile>\n    </profiles>\n    <activeProfiles>\n\
          \        <activeProfile>devops</activeProfile>\n    </activeProfiles>\n\
          \    <mirrors>\n        <mirror>\n            <id>local.artifactory.ribera</id>\n\
          \            <mirrorOf>*</mirrorOf>\n            <url>http://ribera.claro.amx:8081/artifactory/virtual-claro-maven/</url>\n\
          \            <name>artifactory-ribera</name>\n        </mirror>\n    </mirrors>\n\
          \t<servers>\n\t\t<server>\n\t\t\t<id>local-release</id>\n\t\t\t<username>jenkins</username>\n\
          \t\t</server>\n\t\t<server>\n\t\t\t<id>local-snapshot</id>\n\t\t\t<username>jenkins</username>\n\
          \t\t</server>\n    </servers>\n</settings>\n"
        id: "settings-artifactory-ribera"
        isReplaceAll: true
        name: "settings-artifactory-ribera"
        providerId: "org.jenkinsci.plugins.configfiles.maven.MavenSettingsConfig"
        serverCredentialMappings:
        - credentialsId: "jenkins_ribera"
          serverId: "local-release"
        - credentialsId: "jenkins_ribera"
          serverId: "local-snapshot"
  globalLibraries:
    libraries:
    - defaultVersion: "master"
      includeInChangesets: false
      name: "devops-shared-libraries"
      retriever:
        modernSCM:
          scm:
            git:
              credentialsId: "ssh_jenkins-pc"
              id: "46e2850c-c307-4d8a-9f26-a8a6aa0a8212"
              remote: "ssh://git@tapias.claro.amx:7999/ic/devops-shared-libraries.git"
              traits:
              - "gitBranchDiscovery"
    - defaultVersion: "master"
      name: "devops-openshift-libraries"
      retriever:
        modernSCM:
          scm:
            git:
              credentialsId: "ssh_jenkins-pc"
              id: "46e2850c-c307-4d8a-9f26-a8a6aa0a8212"
              remote: "ssh://git@tapias.claro.amx:7999/ic/devops-openshift-libraries.git"
              traits:
              - "gitBranchDiscovery"
    - defaultVersion: "master"
      includeInChangesets: false
      name: "devops-libraries"
      retriever:
        modernSCM:
          scm:
            git:
              credentialsId: "ssh_jenkins-pc"
              id: "dc2e058f-3ea5-40ec-8b4a-4ab01480bcc6"
              remote: "ssh://git@tapias.claro.amx:7999/adi/devops-libraries.git"
              traits:
              - "gitBranchDiscovery"
    - defaultVersion: "master"
      includeInChangesets: false
      name: "devops-pipelines"
      retriever:
        modernSCM:
          scm:
            git:
              credentialsId: "ssh_jenkins-pc"
              id: "dc2e058f-3ea5-40ec-8b4a-4ab01480bcc6"
              remote: "ssh://git@tapias.claro.amx:7999/adi/devops-pipelines.git"
              traits:
              - "gitBranchDiscovery"
    - defaultVersion: "master"
      name: "forms-shared-jenkins-scripts"
      retriever:
        modernSCM:
          scm:
            git:
              credentialsId: "ssh_jenkins"
              id: "b93a9f5d-220e-48c0-9e63-25b6a8be0cee"
              remote: "ssh://git@tapias.claro.amx:7999/sci/forms-shared-jenkins-scripts.git"
              traits:
              - "gitBranchDiscovery"
  globalNexusConfiguration:
    instanceId: "a55e55af4793419581918f70340ccd9a"
    nxrmConfigs:
    - nxrm3Configuration:
        displayName: "nexus3-trigo-default"
        id: "nexus3-trigo-default"
        internalId: "fd4c01a6-285d-4a07-8482-4e06ace5a02e"
        serverUrl: "http://trigo.claro.amx:8081"
    - nxrm3Configuration:
        displayName: "nexus3-madariaga-testing"
        id: "nexus3-madariaga-testing"
        internalId: "da856dcb-d567-4757-99f9-8e39d6f652cd"
        serverUrl: "http://madariaga1.claro.amx:38081"
  globalPluginConfiguration:
    buildConfigListInterval: 100
    buildListInterval: 100
    configMapListInterval: 100
    enabled: false
    foldersEnabled: true
    imageStreamListInterval: 100
    namespace: "ndd-ar-desa"
    secretListInterval: 100
    server: "https://osen01-api.claro.amx/"
  ivyBuildTrigger:
    extendedVersionMatching: false
  jiraGlobalConfiguration:
    sites:
    - alternativeUrl: "http://savigne.claro.amx:8080/"
      appendChangeTimestamp: true
      credentialsId: "jenkins-jira"
      url: "http://savigne.claro.amx:8080/"
      useHTTPAuth: true
  jiraStepsConfig:
    sites:
    - credentialsId: "jenkins-jira"
      loginType: "CREDENTIAL"
      name: "jira-default"
      password: "{AQAAABAAAAAQqvUGBS2PJNJ+cb7JGeJ7H+/P5WjO9ENokhnQbtEw3v0=}"
      readTimeout: 30000
      secret: "{AQAAABAAAAAQ4CrsAOmXA3mS8UGpW9d1NiRBCRGtgwsAPHUsOsddlE4=}"
      timeout: 20000
      token: "{AQAAABAAAAAQWwfkOQbf1MradtS1BpM9SIRDZqBbUgMzhuPdxXuWK74=}"
      url: "http://savigne.claro.amx:8080"
      userName: "jenkins"
  junitTestResultStorage:
    storage: "file"
  location:
    adminAddress: "jenkins-jenkins01adl-7990 <nobody@nowhere>"
    url: "http://jenkins01adl.claro.amx:7990/"
  mailer:
    charset: "UTF-8"
    useSsl: false
    useTls: false
  mavenModuleSet:
    localRepository: "default"
  openShift:
    clusterConfigs:
    - credentialsId: "develop-robot-deployer"
      name: "openshift_no_productivo"
      serverUrl: "https://osen01-api.claro.amx"
      skipTlsVerify: true
  pollSCM:
    pollingThreadCount: 10
  sonarGlobalConfiguration:
    buildWrapperEnabled: false
    installations:
    - name: "sonarqube-default"
      serverUrl: "http://integraci01adw.claro.amx:9000"
      triggers:
        skipScmCause: false
        skipUpstreamCause: false
  timestamper:
    allPipelines: false
    elapsedTimeFormat: "'<b>'HH:mm:ss.S'</b> '"
    systemTimeFormat: "'<b>'HH:mm:ss'</b> '"
```