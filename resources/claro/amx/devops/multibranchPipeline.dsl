multibranchPipelineJob('rNAMEJOB') {
	description ('rDESCRIPTIONJOB')
	displayName ('rDISPLAYNAME')
	branchSources {
		branchSource {
			source {
				bitbucket {
					id('rUUID')
					credentialsId('rHTTPCREDENTIALS')
					serverUrl('rHTTPBITBUCKET')
					repoOwner('rPROJECT')
					repository('rREPO')
				}
			}
            rBUILDSTRATEGIES
		}
	}
	configure {
		def traits = it / sources / data / 'jenkins.branch.BranchSource' / source  / traits
		traits << 'com.cloudbees.jenkins.plugins.bitbucket.BranchDiscoveryTrait' {
			strategyId(3) 
		}
		traits << 'jenkins.scm.impl.trait.WildcardSCMHeadFilterTrait' {
			includes('rBRANCHESREPO') 
			excludes('master')
		}
		traits << 'com.cloudbees.jenkins.plugins.bitbucket.SSHCheckoutTrait' {
			credentialsId('rSSHCREDENTIALS') 
		}
		traits << 'jenkins.plugins.git.traits.LocalBranchTrait' {
			extension(class:'hudson.plugins.git.extensions.impl.LocalBranch') {
				localBranch('**')
			}
		}
		traits << 'jenkins.plugins.git.traits.CleanBeforeCheckoutTrait' {
			extension(class:'hudson.plugins.git.extensions.impl.CleanBeforeCheckout')
		}
		traits << 'com.cloudbees.jenkins.plugins.bitbucket.WebhookConfigurationTrait' {
            committersToIgnore(jenkins)
        }
        rTRAITS
	}
	factory {
        pipelineBranchDefaultsProjectFactory {
			scriptId('rFACTORYSCRIPT')
			useSandbox(true)
		}
	}
    orphanedItemStrategy {
        discardOldItems {
            daysToKeep(2)
            numToKeep(2)
        }
    }
}