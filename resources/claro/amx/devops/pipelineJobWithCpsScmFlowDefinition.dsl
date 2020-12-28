pipelineJob('rNAMEJOB') {
	description ('rDESCRIPTIONJOB')
    rPARAMETERS
	definition {
        cpsScm {
            lightweight(true)
            scriptPath('rFACTORYSCRIPT')
            scm {
                gitSCM {
                    userRemoteConfigs {
                        userRemoteConfig {
                            url('rSSHREPO')
                            credentialsId('rSSHCREDENTIALS')
                            name('origin')
                            refspec('remote')
                        }
                    }
                    browser {
                        bitbucketWeb {
                            repoUrl('')
                        }
                    }
                    gitTool('')
                    branches {
                        branchSpec {
                            name('*/rJENKINSFILEBRANCH')
                        }
                    }
                    doGenerateSubmoduleConfigurations(false)
                }
            }
        }
	}
}