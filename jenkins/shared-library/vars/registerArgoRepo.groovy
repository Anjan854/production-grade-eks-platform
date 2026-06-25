def call(Map config = [:]) {

    withCredentials([
        usernamePassword(
            credentialsId: config.credentialsId,
            usernameVariable: 'GITHUB_USER',
            passwordVariable: 'GITHUB_PAT'
        )
    ]) {

        sh """
        cat <<EOF | /usr/local/bin/kubectl apply -f -
        apiVersion: v1
        kind: Secret
        metadata:
          name: github-repo
          namespace: argocd
          labels:
            argocd.argoproj.io/secret-type: repository

        stringData:
          type: git
          url: ${config.repoUrl}
          username: \$GITHUB_USER
          password: \$GITHUB_PAT
        EOF
        """
    }

    echo 'Repository registered in ArgoCD'
}