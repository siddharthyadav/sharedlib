def call (Map config) {
   echo "Succesfull build: ${BUILD_URL}"
            echo "Build ID : ${BUILD_ID}"
            bitbucketStatusNotify ( buildState: 'SUCCESSFUL' )
            hygieiaBuildPublishStep buildStatus: 'Success'
            slackSend channel: "${config?.slack_channel ?: ''}", color: 'good', message: "Passed ${JOB_NAME} version: ${config?.VERSION ?: ''} ${BUILD_NUMBER} (<${BUILD_URL}|Open>)"
}