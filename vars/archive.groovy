def call(){
def VERSION = sh returnStdout: true, script: "git describe --always |tr -d '\n'"
     sh "tar --exclude='Jenkinsfile' --exclude='./.git' --exclude='README.md' -cvf ${VERSION}.tar.gz ./"
     archiveArtifacts artifacts: "${VERSION}.tar.gz" // saved in master             
     echo 'Stage successful'
      	}
     