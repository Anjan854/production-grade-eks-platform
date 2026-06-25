def call(String path = 'argocd/applications') {
    sh """
    set -e
    export PATH=/usr/local/bin:/opt/homebrew/bin:$PATH
    
    kubectl apply -f ${path}/
    """

    echo "Applications created from ${path}"
}
