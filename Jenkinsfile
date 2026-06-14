@Library('platform-shared-lib') _

pipeline {

    agent {
        label 'Agent-Mac'
    }

    environment {
        AWS_REGION = 'ap-south-1'
        ACCOUNT_ID = '739022091869'
        IMAGE_TAG  = "${BUILD_NUMBER}"
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Login ECR') {
            steps {
                ecrLogin(
                    env.AWS_REGION,
                    env.ACCOUNT_ID
                )
            }
        }

        stage('Build and Push All Services') {
            steps {
                script {

                    def services = [
                        'frontend',
                        'adservice',
                        'cartservice/src',
                        'checkoutservice',
                        'currencyservice',
                        'emailservice',
                        'paymentservice',
                        'productcatalogservice',
                        'recommendationservice',
                        'shippingservice',
                        'loadgenerator',
                        'shoppingassistantservice'

                    ]

                    services.each { service ->

                        echo "Building ${service}"

                        buildAndPushImage(
                            accountId : env.ACCOUNT_ID,
                            region    : env.AWS_REGION,
                            repository: service,
                            tag       : env.IMAGE_TAG,
                            context   : "microservices/src/${service}"
                        )
                    }
                }
            }
        }
    }

    post {

        success {
            echo "All images pushed successfully"
        }

        failure {
            echo "Pipeline failed"
        }
    }
}