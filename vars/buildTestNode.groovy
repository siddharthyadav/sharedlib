 def call(Map config) {
      
      	def npm_v = "${config?.NPM_VERSION ?: ''}"
        sh "npm prune"
        if (npm_v == "6"){
        sh "npm i npm@6.0.0 -g"
        sh "npm install"
        sh "npm -v"
        sh "node -v"
        sh "npm rebuild node-sass"
        }
        else{
         sh "npm install"
         sh "npm -v"
         sh "node -v"
        }
        sh "npm run build.${config?.NODE_ENV ?: ''}"
      	
      } 
  