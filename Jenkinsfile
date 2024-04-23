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
                // Changer le répertoire de travail
                dir('/var/lib/jenkins/workspace/premier_job/mt-gpro-commun') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/premier_job/ma-gpro-logistique') {
                    sh 'mvn clean install'
        
            }
        }
        }

        stage('Deploy'){
            steps{
                deploy adapters: [tomcat9(credentialsId: 'tomcat', path: '', url: 'http://54.166.139.26:8080/')], contextPath: null, war: '**/*.war'
    }

  }

          

}

}


