pipeline
{
 agent any
    stages {
        stage('Clone') {
            steps {
                script {
                    // Cloner la branche migration_devops
                    
                    checkout([$class: 'GitSCM', branches: [[name: 'migration_devops']], userRemoteConfigs: [[url: 'git@gitlab.com:samer.gproconsulting/commercial-industriel.git']]])
                }
            }
        }
        stage('Add other branches') {
            steps {
                script {
                    // Ajouter d'autres branches
                    checkout([$class: 'GitSCM', branches: [[name: 'master'], [name: 'dev']], userRemoteConfigs: [[url: 'git@gitlab.com:samer.gproconsulting/commercial-industriel.git']]])
                }
            }
        }
    }
        
        stage('Build') {
            steps {
                // Changer le répertoire de travail
                dir('/var/lib/jenkins/workspace/premier_job/mt-gpro-commun/mt-gpro-commun-coordination') {
                    // Exécuter les commandes Maven
                    sh 'mvn clean install package'
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


