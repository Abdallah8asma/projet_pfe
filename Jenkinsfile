pipeline
{
 agent any
    stages {

        stage('Clone') {
            steps{
                git branch: 'migration_devops', credentialsId: 'Gitlab', url: 'git@gitlab.com:Abdallah8asma/projet_pfe.git'        
 
   }}

           stage('Installer_Docker'){
            steps{
                ansiblePlaybook credentialsId: 'ansible', installation: 'ansible', inventory: '', 
            playbook: 'install_docker.yaml', vaultTmpPath: ''
            }
        }


     stage('Build') {
            steps {
                dir('/var/lib/jenkins/workspace/premier_job/socle') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/premier_job/socle-j2ee') {
                    sh 'mvn clean install'
        
            }
                dir('/var/lib/jenkins/workspace/premier_job/socle-j2ee-tiers') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/premier_job/scole-j2ee-mt') {
                    sh 'mvn clean install'
        
            }
                dir('/var/lib/jenkins/workspace/premier_job/mt-socle') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/premier_job/mt-commun') {
                    sh 'mvn clean install'
        
            }
                dir('/var/lib/jenkins/workspace/premier_job/mt-gpro-commun') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/premier_job/ma-gpro-logistique') {
                    sh 'mvn clean install'      }

               dir('/var/lib/jenkins/workspace/premier_job/ma-gpro-design-war') {
                    sh 'mvn clean install'                }

                dir('/var/lib/jenkins/workspace/premier_job/ma-gpro-atelier-war') {
                    sh 'mvn clean install' }
        }
        }

          
        
  

} }




