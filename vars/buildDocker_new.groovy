 def call(Map config) {
milestone()

properties([parameters([string(name: 'BRANCH_NAME', defaultValue: 'develop')])])
def branch = params.BRANCH_NAME
def NODE_ENV= ""
switch(branch){
case "develop" : NODE_ENV = "dev"
case "uat" : NODE_ENV = "uat"
case "master" : NODE_ENV = "prod"
}

        
             withCredentials([usernamePassword(credentialsId: 'fsoeacr-login', passwordVariable: 'passwd', usernameVariable: 'username')]) {
             		sh " sudo docker login -u ${username} -p ${passwd} acusnfsoeacr.azurecr.io"
                    sh "sudo docker build . -t acusnfsoeacr.azurecr.io/${config?.PROJECT ?: ''}/${NODE_ENV}:${config?.VERSION ?: ''}"
                    sh "sudo docker push acusnfsoeacr.azurecr.io/${config?.PROJECT ?: ''}/${NODE_ENV}:${config?.VERSION ?: ''}"
             }
             hygieiaDeployPublishStep applicationName: "${config?.testApplicationName ?: ''}", artifactDirectory: "${WORKSPACE}", artifactGroup: 'com.ey.fsoe.adc.aml', artifactName: "${env.ARTIFACT}", artifactVersion: "${config?.VERSION ?: ''}", buildStatus: 'Success', environmentName: "${NODE_ENV}"
             }