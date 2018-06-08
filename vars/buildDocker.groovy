 def call(Map config) {
milestone()
        
             withCredentials([usernamePassword(credentialsId: 'fsoeacr-login', passwordVariable: 'passwd', usernameVariable: 'username')]) {
             		sh "sudo docker login -u ${username} -p ${passwd} acusnfsoeacr.azurecr.io"
                    sh "sudo docker build . -t acusnfsoeacr.azurecr.io/${config?.PROJECT ?: ''}/${config?.NODE_ENV ?: ''}:${config?.VERSION ?: ''}"
                    sh "sudo docker push acusnfsoeacr.azurecr.io/${config?.PROJECT ?: ''}/${config?.NODE_ENV ?: ''}:${config?.VERSION ?: ''}"
             }
             hygieiaDeployPublishStep applicationName: "${config?.testApplicationName ?: ''}", artifactDirectory: "${WORKSPACE}", artifactGroup: 'com.ey.fsoe.adc.aml', artifactName: "${env.ARTIFACT}", artifactVersion: "${config?.VERSION ?: ''}", buildStatus: 'Success', environmentName: "${config?.NODE_ENV ?: ''}"
             }