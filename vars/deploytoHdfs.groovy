def call(Map config) {   
    withCredentials([sshUserPrivateKey(credentialsId: "${config?.credential_id ?: ''}", keyFileVariable: 'id_rsa', passphraseVariable: '', usernameVariable: 'userid')]) {
            
              def VERSION = sh returnStdout: true, script: "git describe --always |tr -d '\n'"
              sh "ssh -o StrictHostKeyChecking=no -i ${id_rsa} ${userid}@${config?.host ?: ''} 'rm -rf ${config?.untar_location ?: ''}/${VERSION}'"
              sh "ssh -o StrictHostKeyChecking=no -i ${id_rsa} ${userid}@${config?.host ?: ''} 'mkdir ${config?.untar_location ?: ''}/${VERSION}'"
              sh "scp -o StrictHostKeyChecking=no -i ${id_rsa} ./${VERSION}.tar.gz ${userid}@${config?.host ?: ''}:${config?.tar_location ?: ''}/${VERSION}" 
              sh "ssh -o StrictHostKeyChecking=no -i ${id_rsa} ${userid}@${config?.host ?: ''} 'tar -xvf ${config?.tar_location ?: ''}/${VERSION}/${VERSION}.tar.gz -C ${config?.untar_location ?: ''}/${VERSION}'" 
              sh "ssh -o StrictHostKeyChecking=no -i ${id_rsa} ${userid}@${config?.host ?: ''} 'rm -rf ${config?.tar_location ?: ''}/${VERSION}/${VERSION}.tar.gz'"
              sh "ssh -o StrictHostKeyChecking=no -i ${id_rsa} ${userid}@${config?.host ?: ''} 'hdfs dfs -put -f ${config?.untar_location ?: ''}/${VERSION}/*  ${config?.hdfs_location ?: ''}/'"
              sh "ssh -o StrictHostKeyChecking=no -i ${id_rsa} ${userid}@${config?.host ?: ''} 'rm -rf ${config?.untar_location ?: ''}/${VERSION}'"

    }}