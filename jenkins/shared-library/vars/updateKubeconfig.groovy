def call(Map config = [:]) {

    sh """
    set -e

    export PATH=/usr/local/bin:/opt/homebrew/bin:\$PATH
    export KUBECONFIG=\$HOME/.kube/config

    aws eks update-kubeconfig \
      --region ${config.region} \
      --name ${config.clusterName}
    """

    echo "Kubeconfig updated for ${config.clusterName}"
}