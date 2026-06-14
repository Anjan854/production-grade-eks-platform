def call(Map config) {

    sh """
        docker buildx create \
            --name builder \
            --driver docker-container \
            --use || true

        docker buildx inspect --bootstrap

        docker buildx build \
            --platform linux/amd64 \
            -t ${config.accountId}.dkr.ecr.${config.region}.amazonaws.com/${config.repository}:${config.tag} \
            --push \
            ${config.context}
    """
}