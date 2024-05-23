pipeline {
    agent any
    stages {
        stage('Clone') {
            steps {
                git branch: 'migration_devops', credentialsId: 'Gitlab', url: 'git@gitlab.com:Abdallah8asma/projet_pfe.git'
            }
        }

       stage('Setup Docker Permissions') {
            steps {
                script {
                    sh '''
                        sudo chown root:docker /var/run/docker.sock
                        sudo chmod 666 /var/run/docker.sock
                    '''
                }
            }
        }

             stage('Build') {
            steps {
                dir('/var/lib/jenkins/workspace/commercial_industriel@2/socle') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/commercial_industriel@2/socle-j2ee') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/commercial_industriel@2/socle-j2ee-tiers') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/commercial_industriel@2/scole-j2ee-mt') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/commercial_industriel@2/mt-socle') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/commercial_industriel@2/mt-commun') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/commercial_industriel@2/mt-gpro-commun') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/commercial_industriel@2/ma-gpro-logistique') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/commercial_industriel@2/ma-gpro-design-war') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/commercial_industriel@2/ma-gpro-atelier-war') {
                    sh 'mvn clean install'
                }
            }
        }
        
stage('Slack notification') {
    steps {
        script {
            def buildStatus = currentBuild.currentResult
            def message = "Pipeline Status: ${buildStatus}\nJob Name: ${env.JOB_NAME}\nBuild Number: ${env.BUILD_NUMBER}\nBuild URL: ${env.BUILD_URL}"

            if (buildStatus == 'SUCCESS') {
                slackSend(channel: '#devops', message: "Pipeline Succeeded\n${message}")
            } else {
                slackSend(channel: '#devops', message: "Pipeline Failed\n${message}")
            }
        }
    }
}

stage('Docker Swarm ') {
    steps {
        script {
            sh 'docker swarm init || true'
        }}}


  stage('Remove Docker Compose Containers') {
      steps {
        sh 'docker-compose down'
        }
    }
  stage('Docker Compose Up') {
            steps {
                sh 'docker-compose up -d'
            }
        }

        stage('Déploiement sur Tomcat') {
            steps {
     
                    // Déploiement de ma-gpro-design
                    deploy adapters: [tomcat9(credentialsId: 'Tomcat', path: '', url: 'http://3.85.117.33:8080/')], 
                           contextPath: '/ma-gpro-design-3.5.0.0-SNAPSHOT', 
                           war: 'ma-gpro-design-war/presentation/target/ma-gpro-design-3.5.0.0-SNAPSHOT.war'

              
          
    }
}


    }
}



