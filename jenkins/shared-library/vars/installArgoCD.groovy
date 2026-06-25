def call() {

    sh '''
    set -e

    kubectl create namespace argocd \
      --dry-run=client -o yaml | kubectl apply -f -

    /usr/local/bin/kubectl apply \
      -n argocd \
      -f https://raw.githubusercontent.com/argoproj/argo-cd/stable/manifests/install.yaml
    '''

    echo 'Waiting for ArgoCD components...'

    sh '''
    /usr/local/bin/kubectl rollout status deployment/argocd-server \
      -n argocd \
      --timeout=300s

    /usr/local/bin/kubectl rollout status deployment/argocd-repo-server \
      -n argocd \
      --timeout=300s

    /usr/local/bin/kubectl rollout status deployment/argocd-applicationset-controller \
      -n argocd \
      --timeout=300s
    '''

    echo 'ArgoCD installation completed'
}