pipeline
{
 agent any
    stages {

        stage('Clone') {
            steps{
                git branch: 'migration_devops', credentialsId: 'Gitlab', url: 'git@gitlab.com:samer.gproconsulting/commercial-industriel.git'        
 
   }}

        stage('Installer_k8s'){
            steps{
                ansiblePlaybook credentialsId: 'Ansible', installation: 'ansible', inventory: '', 
            playbook: 'install_k8s.yaml', vaultTmpPath: ''
            }
        }
        
  

} }




