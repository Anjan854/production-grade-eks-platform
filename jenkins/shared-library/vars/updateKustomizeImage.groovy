def call(String service, String image, String tag, String path) {

    if (!service || !image || !tag || !path) {
        error("Missing required parameters for updateKustomizeImage")
    }

    echo "Updating Kustomize image for ${service}"

    sh """
        set -e

        cd ${path}

        /opt/homebrew/bin/kustomize edit set image \
        ${service}=${image}:${tag}

        echo "Updated kustomization.yaml:"
        cat kustomization.yaml
    """
}