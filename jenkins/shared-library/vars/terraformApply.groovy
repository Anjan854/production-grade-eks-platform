def call(String terraformDir = 'terraform') {

    dir(terraformDir) {

        sh '''
        set -e

        /opt/homebrew/bin/terraform apply -auto-approve 
        '''
    }

    echo 'Terraform apply completed'
}