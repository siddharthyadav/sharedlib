def call (Map config) {

            echo 'prune node manager and cleanup'
             sh 'npm prune'
             sh 'rm -rf node_modules'
             echo 'NPM Cleanup Successful'   

}