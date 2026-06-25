def call(Map config = [:]) {

    sh """
    set -e

    /usr/local/bin/aws eks update-kubeconfig \
      --region ${config.region} \
      --name ${config.clusterName}
    """

    echo "Kubeconfig updated for ${config.clusterName}"
}