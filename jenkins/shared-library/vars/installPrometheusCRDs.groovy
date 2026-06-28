def call() {

    sh '''
    set -e

    export PATH=/usr/local/bin:/opt/homebrew/bin:$PATH

    VERSION=v0.85.0
    BASE=https://raw.githubusercontent.com/prometheus-operator/prometheus-operator/${VERSION}/example/prometheus-operator-crd

    CRDS="
    monitoring.coreos.com_alertmanagerconfigs.yaml
    monitoring.coreos.com_alertmanagers.yaml
    monitoring.coreos.com_podmonitors.yaml
    monitoring.coreos.com_probes.yaml
    monitoring.coreos.com_prometheusagents.yaml
    monitoring.coreos.com_prometheuses.yaml
    monitoring.coreos.com_prometheusrules.yaml
    monitoring.coreos.com_scrapeconfigs.yaml
    monitoring.coreos.com_servicemonitors.yaml
    monitoring.coreos.com_thanosrulers.yaml
    "

    for crd in $CRDS
    do
      kubectl apply \
        --server-side \
        --force-conflicts \
        -f ${BASE}/${crd}
    done
    '''

    echo 'Prometheus CRDs installed successfully'
}