def call(String awsRegion, String accountId) {

    withCredentials([
        [
            $class: 'AmazonWebServicesCredentialsBinding',
            credentialsId: 'aws-cred'
        ]
    ]) {

        sh """
            /usr/local/bin/aws ecr get-login-password \
                --region ${awsRegion} \
            | /usr/local/bin/docker login \
                --username AWS \
                --password-stdin \
                ${accountId}.dkr.ecr.${awsRegion}.amazonaws.com
        """
    }
}