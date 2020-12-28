pipelineJob('rNAMEJOB') {
	description ('rDESCRIPTIONJOB')
    rPARAMETERS
	definition { 
		cps {
			script("""
rFACTORYSCRIPT""")
			sandbox(true)
        }
	}
}