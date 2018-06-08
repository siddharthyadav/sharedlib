def call(){
      
          stage('Git') {
              // Pulling
              deleteDir()
             sh "git config --global user.email charles.lambert@ey.com"
             sh "git config --global user.name celambert"
             checkout scm
              
          }
  }

  