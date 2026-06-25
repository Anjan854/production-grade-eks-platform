def call(Map config = [:]) {

    script {

        def vpcId = sh(
            script: """
            export PATH=/usr/local/bin:/opt/homebrew/bin:$PATH
            aws eks describe-cluster \
              --name ${config.clusterName} \
              --region ${config.region} \
              --query "cluster.resourcesVpcConfig.vpcId" \
              --output text
            """,
            returnStdout: true
        ).trim()

        echo "Detected VPC: ${vpcId}"

        sh """
        set -e

        export PATH=/usr/local/bin:/opt/homebrew/bin:$PATH
        kubectl apply -f ${config.saManifestPath}

        /opt/homebrew/bin/helm upgrade --install aws-load-balancer-controller \
          eks/aws-load-balancer-controller \
          -n kube-system \
          --set clusterName=${config.clusterName} \
          --set serviceAccount.create=false \
          --set serviceAccount.name=aws-load-balancer-controller \
          --set region=${config.region} \
          --set vpcId=${vpcId}
        """
    }

    echo 'AWS Load Balancer Controller installed'
}