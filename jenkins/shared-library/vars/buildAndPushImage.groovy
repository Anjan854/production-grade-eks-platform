def call(Map config) {

    sh """
        /usr/local/bin/docker buildx inspect --bootstrap

        /usr/local/bin/docker buildx build \
            --platform linux/amd64 \
            -t ${config.accountId}.dkr.ecr.${config.region}.amazonaws.com/${config.repository}:${config.tag} \
            --push \
            ${config.context}
    """
}