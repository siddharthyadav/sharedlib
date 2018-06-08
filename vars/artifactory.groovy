 def call (Map config){

 def server = Artifactory.server 'artifactory_unit'
 def buildInfo = Artifactory.newBuildInfo()
      buildInfo.env.capture = true
      buildInfo.env.collect()
    def uploadSpec = """{
  "files": [
    {
      "pattern": "target/*.jar",
      "target": "${config?.repo_name ?: ''}/BUILD_${BUILD_NUMBER}/"
    },
     {
      "pattern": "target/*.zip",
      "target": "${config?.repo_name ?: ''}/BUILD_${BUILD_NUMBER}/"
    }
 ]
}"""
server.upload(uploadSpec)
server.publishBuildInfo buildInfo
}