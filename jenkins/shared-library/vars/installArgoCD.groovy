def call() {

    sh '''
    set -e
    export PATH=/usr/local/bin:/opt/homebrew/bin:$PATH
    kubectl create namespace argocd \
      --dry-run=client -o yaml | kubectl apply -f -

    kubectl apply \
      -n argocd \
      -f https://raw.githubusercontent.com/argoproj/argo-cd/stable/manifests/install.yaml
    '''

    echo 'Waiting for ArgoCD components...'

    sh '''
    export PATH=/usr/local/bin:/opt/homebrew/bin:$PATH
    
    kubectl rollout status deployment/argocd-server \
      -n argocd \
      --timeout=300s

    kubectl rollout status deployment/argocd-repo-server \
      -n argocd \
      --timeout=300s

    kubectl rollout status deployment/argocd-applicationset-controller \
      -n argocd \
      --timeout=300s
    '''

    echo 'ArgoCD installation completed'
}