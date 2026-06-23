def call(String message) {

    withCredentials([
        usernamePassword(
            credentialsId: 'github-creds',
            usernameVariable: 'GIT_USER',
            passwordVariable: 'GIT_TOKEN'
        )
    ]) {

        sh """
            set -e

            git checkout main

            git config user.email "jenkins@local"
            git config user.name "jenkins"

            git add kubernetes/

            git commit -m "${message}" || echo "No changes to commit"

            git push \
            https://${GIT_USER}:${GIT_TOKEN}@github.com/Anjan854/production-grade-eks-platform.git \
            HEAD:main
        """
    }
}