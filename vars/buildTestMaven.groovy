 def call(Map config) {
      try {
      	withMaven(maven: "${config?.Jenkins_Maven ?: ''}") {
          sh "mvn clean install"
      	}
      } finally {
          def path = "${config?.params ?: ''}"
          junit testResults: "${path}/*.xml", allowEmptyResults: true
           hygieiaTestPublishStep buildStatus: 'Success', testApplicationName: "${config?.testApplicationName ?: ''}", testEnvironmentName: "${config?.testEnvironmentName ?: ''}", testFileNamePattern: '*.xml', testResultsDirectory: "/${path}", testType: 'Unit'
      }
  }