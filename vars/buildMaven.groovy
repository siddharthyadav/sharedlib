 def call(Map config) {
      try {
          sh "mvn clean install"
      } finally {
          def path = "${config?.params ?: ''}"
          if (path != "") {
          junit testResults: "${path}/*.xml", allowEmptyResults: true
           hygieiaTestPublishStep buildStatus: 'Success', testApplicationName: "${config?.testApplicationName ?: ''}", testEnvironmentName: "${config?.testEnvironmentName ?: ''}", testFileNamePattern: '*.xml', testResultsDirectory: "/${path}", testType: 'Unit'
      }}
  }