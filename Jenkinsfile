pipeline
{
 agent any
    stages {

        stage('Clone') {
            steps{
                git branch: 'migration_devops', credentialsId: 'Gitlab', url: 'git@gitlab.com:samer.gproconsulting/commercial-industriel.git'
        }
                    }
    
       

   stage('install docker'){
            steps{
                ansiblePlaybook credentialsId: 'Ansible', installation: 'ansible', inventory: '', 
            playbook: 'install_docker.yaml', vaultTmpPath: ''




       }
  }


  }

          

}




