def call(body){
	def config = [:]
        body.resolveStrategy = Closure.DELEGATE_FIRST
        body.delegate = config
        body()
		  stage('Prep') {
            echo "Workspace: ${WORKSPACE} with Jenkins home at ${JENKINS_HOME}"
            def pwdoutput = sh returnStdout: true, script: 'pwd'
            echo "Current directory location is: ${pwdoutput}"
            //slackSend channel: 'list-screening-cicd', color: 'good', message: "Started ${JOB_NAME} ${env.BUILD_NUMBER} (<${BUILD_URL}|Open>)"
            
          }
      
          stage('Git') {
              // Pulling
              deleteDir()
              sh "git config --global user.email charles.lambert@ey.com"
             sh "git config --global user.name celambert"
              def repositoryUrl = scm.userRemoteConfigs[0].url 

             // git branch: "${config.bitbucket_branch}" , url: "${repositoryUrl}", credentialsId: "67c2421e-3f9c-4326-aa51-fbdad8a9f4da"

              git branch: "${config.bitbucket_branch}" , url: "${repositoryUrl}", credentialsId: "87c99b52-e086-4331-8d9c-a245d99fb8b9"

              
                def VERSION = sh returnStdout: true, script: 'git describe --always'
                if (VERSION) {
                  echo "Building version ${VERSION}"
                }
              
          }
  }

  