def call() {

    sh '''
    set -e

    export PATH=/usr/local/bin:/opt/homebrew/bin:$PATH

    kubectl apply --server-side \
      -f https://raw.githubusercontent.com/prometheus-operator/prometheus-operator/v0.85.0/example/prometheus-operator-crd/monitoring.coreos.com_alertmanagerconfigs.yaml

    kubectl apply --server-side \
      -f https://raw.githubusercontent.com/prometheus-operator/prometheus-operator/v0.85.0/example/prometheus-operator-crd/monitoring.coreos.com_alertmanagers.yaml

    kubectl apply --server-side \
      -f https://raw.githubusercontent.com/prometheus-operator/prometheus-operator/v0.85.0/example/prometheus-operator-crd/monitoring.coreos.com_prometheuses.yaml

    kubectl apply --server-side \
      -f https://raw.githubusercontent.com/prometheus-operator/prometheus-operator/v0.85.0/example/prometheus-operator-crd/monitoring.coreos.com_prometheusagents.yaml

    kubectl apply --server-side \
      -f https://raw.githubusercontent.com/prometheus-operator/prometheus-operator/v0.85.0/example/prometheus-operator-crd/monitoring.coreos.com_scrapeconfigs.yaml

    kubectl apply --server-side \
      -f https://raw.githubusercontent.com/prometheus-operator/prometheus-operator/v0.85.0/example/prometheus-operator-crd/monitoring.coreos.com_thanosrulers.yaml
    '''

    echo "Prometheus CRDs installed"
}