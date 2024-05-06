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
                dir('/var/lib/jenkins/workspace/Pfe1/socle') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/Pfe1/socle-j2ee') {
                    sh 'mvn clean install'
        
            }
                dir('/var/lib/jenkins/workspace/Pfe1/socle-j2ee-tiers') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/Pfe1/scole-j2ee-mt') {
                    sh 'mvn clean install'
        
            }
                dir('/var/lib/jenkins/workspace/Pfe1/mt-socle') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/Pfe1/mt-commun') {
                    sh 'mvn clean install'
        
            }
                dir('/var/lib/jenkins/workspace/Pfe1/mt-gpro-commun') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/Pfe1/ma-gpro-logistique') {
                    sh 'mvn clean install'      }

               dir('/var/lib/jenkins/workspace/Pfe1/ma-gpro-design-war') {
                    sh 'mvn clean install'                }

                dir('/var/lib/jenkins/workspace/Pfe1/ma-gpro-atelier-war') {
                    sh 'mvn clean install' }
        }
        }

          
        
  

} }




