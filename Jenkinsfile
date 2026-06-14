@Library('platform-shared-lib') _

pipeline {

    agent {
        label 'Agent-Mac'
    }

    environment {
        AWS_REGION = 'ap-south-1'
        ACCOUNT_ID = '739022091869'
        REPOSITORY = 'frontend'
        IMAGE_TAG = "${BUILD_NUMBER}"
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

        stage('Build and Push') {
            steps {

                buildAndPushImage(
                    accountId : env.ACCOUNT_ID,
                    region    : env.AWS_REGION,
                    repository: env.REPOSITORY,
                    tag       : env.IMAGE_TAG,
                    context   : 'microservices/src/frontend'
                )

            }
        }
    }
}