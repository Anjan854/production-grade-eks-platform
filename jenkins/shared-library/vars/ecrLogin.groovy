def call(String awsRegion, String accountId) {

    withCredentials([
        [
            $class: 'AmazonWebServicesCredentialsBinding',
            credentialsId: 'aws-cred'
        ]
    ]) {

        sh """
            aws ecr get-login-password \
                --region ${awsRegion} \
            | docker login \
                --username AWS \
                --password-stdin \
                ${accountId}.dkr.ecr.${awsRegion}.amazonaws.com
        """
    }
}