def call(String terraformDir = 'terraform') {

    dir(terraformDir) {

        sh '''
        set -e

        /opt/homebrew/bin/terraform plan 
        '''
    }

    echo 'Terraform plan completed'
}