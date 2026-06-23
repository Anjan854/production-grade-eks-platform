def call(String message, String branch = "main") {

    if (!message) {
        error("Commit message cannot be empty")
    }

    echo "Committing GitOps changes..."

    sh """
        set -e

        git checkout main
        git config user.email "jenkins@local"
        git config user.name "jenkins"

        git add kubernetes/

        git commit -m "${message}" || echo "No changes to commit"

        git push origin ${branch}
    """
}