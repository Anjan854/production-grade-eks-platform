def call(String terraformDir = 'terraform') {

    dir(terraformDir) {

        sh '''
        set -e

        /opt/homebrew/bin/terraform init
        '''
    }

    echo 'Terraform init completed'
}