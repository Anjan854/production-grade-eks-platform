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
    parameters {
        booleanParam(
        name: 'FORCE_BUILD',
        defaultValue: false,
        description: 'Force application deployment'
    )
    }

    stages {
        stage('Skip GitOps Commits') {
            steps {
                script {
                    def msg = sh(
                        script: 'git log -1 --pretty=%B',
                        returnStdout: true
                    ).trim()

                    echo "Last commit message: ${msg}"

                    if (msg.contains('[gitops]')) {
                        currentBuild.result = 'NOT_BUILT'
                        error('Skipping pipeline - GitOps commit detected')
                    }
                }
            }
        }

        stage('Check Application Changes') {
            steps {
                script {
                    if (params.FORCE_BUILD) {
                        echo 'FORCE_BUILD enabled. Skipping change detection.'
                        return
                    }
                    def changedFiles = []

                    for (changeLog in currentBuild.changeSets) {
                        for (entry in changeLog.items) {
                            for (file in entry.affectedFiles) {
                                changedFiles.add(file.path)
                            }
                        }
                    }

                    echo 'Changed files:'
                    changedFiles.each {
                        echo it
                    }

                    def appChanged = changedFiles.any {
                        it.startsWith('microservices/') ||
                it.startsWith('kubernetes/') ||
                it == 'Jenkinsfile'
                    }

                    if (!appChanged) {
                        currentBuild.result = 'NOT_BUILT'

                        error('No application changes detected')
                    }
                }
            }
        }

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
                        'cartservice',
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

        stage('Update Kustomize Images') {
            steps {
                script {
                    def services = [
                    'frontend',
                    'adservice',
                    'cartservice',
                    'checkoutservice',
                    'currencyservice',
                    'emailservice',
                    'paymentservice',
                    'productcatalogservice',
                    'recommendationservice',
                    'shippingservice'
            ]

                    services.each { service ->
                        def image = "${ACCOUNT_ID}.dkr.ecr.${AWS_REGION}.amazonaws.com/${service}"

                        updateKustomizeImage(
                    service,
                    image,
                    env.IMAGE_TAG,
                    "kubernetes/${service}"
                )
                    }
                }
            }
        }

        stage('Commit GitOps Changes') {
            steps {
                script {
                    echo 'Preparing GitOps commit...'
                    def tag = env.IMAGE_TAG

                    // Commit message (important for loop prevention)
                    def commitMessage = "[gitops] update image tags ${tag}"

                    // Call shared library function
                    gitOpsCommit(commitMessage)

                    echo 'GitOps changes pushed successfully'
                }
            }
        }
    }

    post {
        success {
            echo 'All images pushed successfully'
        }

        failure {
            echo 'Pipeline failed'
        }
    }
}
