pipeline
{
 agent any
    stages {

        stage('Clone') {
            steps{
                git branch: 'migration_devops', credentialsId: 'Gitlab', url: 'git@gitlab.com:samer.gproconsulting/commercial-industriel.git'
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
                    sh 'mvn clean install'
        
            }
               dir('/var/lib/jenkins/workspace/premier_job/ma-gpro-design-war') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/premier_job/ma-gpro-atelier-war') {
                    sh 'mvn clean install'
        
            }
        }
        }

        stage('Deploy'){
            steps {
                deploy adapters: [tomcat9(credentialsId: 'Tomcat', path: '', url: 'http://54.224.35.51:8080/')], contextPath: '/ma-gpro-design', war: '**/*.war'
    }
}


  }

          

}

}


