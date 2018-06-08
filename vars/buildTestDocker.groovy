def call(Map config) {
milestone()
        
             withCredentials([usernamePassword(credentialsId: 'fsoeacr-login', passwordVariable: 'passwd', usernameVariable: 'username')]) {
             		sh "docker login -u ${username} -p ${passwd} acusnfsoeacr.azurecr.io"
                    sh "docker build . -t acusnfsoeacr.azurecr.io/${config?.PROJECT ?: ''}/${config?.NODE_ENV ?: ''}:${config?.VERSION ?: ''}"
                    sh "docker push acusnfsoeacr.azurecr.io/${config?.PROJECT ?: ''}/${config?.NODE_ENV ?: ''}:${config?.VERSION ?: ''}"
             }
             }