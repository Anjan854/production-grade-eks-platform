def call(String terraformDir = 'terraform') {

    dir(terraformDir) {

        sh '''
        set -e

        /opt/homebrew/bin/terraform validate
        '''
    }

    echo 'Terraform validation completed'
}