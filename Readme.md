# jenkins-pipelines-libraries-openshift
Shared libraries, Pipelines, jenkinsfiles and resources

## Shared libraries used by pipelines
- [bitbucketHelper] (http://[repoHost]:[repoPort]/projects/[projectKey]/repos/[repoLib]/browse/bitbucketHelper.md)
- [cacHelper] (http://[repoHost]:[repoPort]/projects/[projectKey]/repos/[repoLib]/browse/cacHelper.md)
- [cust] (http://[repoHost]:[repoPort]/projects/[projectKey]/repos/[repoLib]/browse/cust.md)
- [cvs2GitHelper] (http://[repoHost]:[repoPort]/projects/[projectKey]/repos/[repoLib]/browse/cvs2GitHelper.md)
- [doHelper] (http://[repoHost]:[repoPort]/projects/[projectKey]/repos/[repoLib]/browse/doHelper.md)
- [dslHelper] (http://[repoHost]:[repoPort]/projects/[projectKey]/repos/[repoLib]/browse/dslHelper.md)
- [interfaceHelper] (http://[repoHost]:[repoPort]/projects/[projectKey]/repos/[repoLib]/browse/interfaceHelper.md)
- [jiraHelper] (http://[repoHost]:[repoPort]/projects/[projectKey]/repos/[repoLib]/browse/jiraHelper.md)
- [log] (http://[repoHost]:[repoPort]/projects/[projectKey]/repos/[repoLib]/browse/log.md)
- [mvnHelper] (http://[repoHost]:[repoPort]/projects/[projectKey]/repos/[repoLib]/browse/mvnHelper.md)
- [notifierHelper] (http://[repoHost]:[repoPort]/projects/[projectKey]/repos/[repoLib]/browse/notifierHelper.md)
- [npmHelper] (http://[repoHost]:[repoPort]/projects/[projectKey]/repos/[repoLib]/browse/npmHelper.md)
- [openshiftHelper] (http://[repoHost]:[repoPort]/projects/[projectKey]/repos/[repoLib]/browse/openshiftHelper.md)
- [procHelper] (http://[repoHost]:[repoPort]/projects/[projectKey]/repos/[repoLib]/browse/procHelper.md)
- [utils] (http://[repoHost]:[repoPort]/projects/[projectKey]/repos/[repoLib]/browse/utils.md

## Pipelines
- [PipelineCreateNewApp] (http://[repoHost]:[repoPort]/projects/[projectKey]/repos/[repoLib]/browse/PipelineCreateNewApp.md)
- [PipelineCreateProject] (http://[repoHost]:[repoPort]/projects/[projectKey]/repos/[repoLib]/browse/PipelineCreateProject.md)
- [PipelineCvs2Git] (http://[repoHost]:[repoPort]/projects/[projectKey]/repos/[repoLib]/browse/PipelineCvs2Git.md)
- [PipelineDelivery] (http://[repoHost]:[repoPort]/projects/[projectKey]/repos/[repoLib]/browse/PipelineDelivery.md)
- [PipelineDeliveryDB] (http://[repoHost]:[repoPort]/projects/[projectKey]/repos/[repoLib]/browse/PipelineDeliveryDB.md)
- [PipelineDevOps] (http://[repoHost]:[repoPort]/projects/[projectKey]/repos/[repoLib]/browse/PipelineDevOps.md)
- [PipelineIC] (http://[repoHost]:[repoPort]/projects/[projectKey]/repos/[repoLib]/browse/PipelineIC.md)
- [PipelineListOs] (http://[repoHost]:[repoPort]/projects/[projectKey]/repos/[repoLib]/browse/PipelineListOs.md)
- [PipelineRollout] (http://[repoHost]:[repoPort]/projects/[projectKey]/repos/[repoLib]/browse/PipelineRollout.md)
- [PipelineRolloutWithParameters] (http://[repoHost]:[repoPort]/projects/[projectKey]/repos/[repoLib]/browse/PipelineRolloutWithParameters.md)
- [PipelineUpdateWithParameters] (http://[repoHost]:[repoPort]/projects/[projectKey]/repos/[repoLib]/browse/PipelineUpdateWithParameters.md)

## Directory structure
 The directory structure of this Shared Library repository is as follows:
```
(devops-openshift-libraries)
|+- [groovyName].md            # documentation for *.groovy in vars
+- vars
|   +- [libraryname].groovy            # implemented defs in [libraryname]
|   +- Pipeline[functionality].groovy  # implemented Pipeline
|   +- jenkinsfile-[functionality]     # Infrastructure jenkinsfile
+- resources  # required resource files
|   +- com
|      +- claro
|         +- amx
|            +- devops
|              -os-infra-template-java.yaml    # infra template for java
|              -os-infra-template-nginx.yaml   # infra template for nginx
|              -os-infra-template-nodejs.yaml  # infra template for nodejs
|   +- claro
|      +- amx
|         +- devops
|            -cvs2git-changes.txt       # regex for required replaces
|            -cvs2git-template.options  # template options
|            -cvs2git-readme.md         # file to add in new git repositories migrated from cvs
|            -envProc.yaml              # yaml proc configuration
|            -DevOps*.yaml              # yaml Pipeline configuration
|            -*.dsl                     # jenkins dsl configurations
```

The `vars` directory hosts Pipelines and scripts that define global variables accessible from Pipelines. 
The basename of each `*.groovy file` should be a Groovy (~ Java) identifier, conventionally camelCased. 
The `resources` directory allows the libraryResource step to be used from an external library to load associated non-Groovy files.

### Reference
[Jenkins shared libraries #directory-structure](https://jenkins.io/doc/book/pipeline/shared-libraries/#directory-structure "Jenkins docs")

## Required jenkins configurations
[Jenkins Configurations] (http://[repoHost]:[repoPort]/projects/[projectKey]/repos/[repoLib]/browse/jenkins.md)