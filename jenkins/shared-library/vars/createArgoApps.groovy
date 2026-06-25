def call(String path = 'argocd/applications') {

    sh """
    set -e

    /usr/local/bin/kubectl apply -f ${path}/
    """

    echo "Applications created from ${path}"
}