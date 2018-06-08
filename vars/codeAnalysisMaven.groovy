def call(Map config) {
                withMaven(maven: "${config?.Jenkins_Maven ?: ''}"){
                withSonarQubeEnv('SonarQube') {
                 sh 'mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install -Dmaven.test.failure.ignore=true -Dmaven.clean.failOnError=false'
                  hygieiaSonarPublishStep ceQueryIntervalInSeconds: '10', ceQueryMaxAttempts: '30'
                  sh "mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.2:sonar -Dmaven.test.failure.ignore=true -Dmaven.clean.failOnError=false"
                }
            }
        }