pipeline {
    agent any
        environment {
       DOCKER_IMAGE_NAME_FRONT = 'front'
       DOCKER_IMAGE_NAME_DATA = 'data'
       BUILD_NUMBER = "${BUILD_NUMBER}"

    }
    stages {
        stage('Clone') {
            steps {
                git branch: 'migration_devops', credentialsId: 'Gitlab', url: 'git@gitlab.com:Abdallah8asma/projet_pfe.git'
            }
        }

            stage('Build') {
            steps {
                dir('/var/lib/jenkins/workspace/commercial_industriel1/socle') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/commercial_industriel1/socle-j2ee') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/commercial_industriel1/socle-j2ee-tiers') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/commercial_industriel1/scole-j2ee-mt') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/commercial_industriel1/mt-socle') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/commercial_industriel1/mt-commun') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/commercial_industriel1/mt-gpro-commun') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/commercial_industriel1/ma-gpro-logistique') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/commercial_industriel1/ma-gpro-design-war') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/commercial_industriel1/ma-gpro-atelier-war') {
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
   stage('Setup Docker Permissions') {
            steps {
                    sh '''
                        sudo chown root:docker /var/run/docker.sock
                        sudo chmod 666 /var/run/docker.sock
                    '''
                    }
        }


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

 stage('Importation de la base de données') {
            steps {
                // Copier le fichier de sauvegarde vers le conteneur Docker
                script {
                    sh "docker cp Backup/commercial-industriel_Libanese_2024_03_21__12_00_15.backup commercial_industriel1-postgres-1:/data.backup"
                }
            }
        }
     stage('Restauration de la base de données') {
            steps {
                script {
                    // Restauration de la base de données avec pg_restore
                    sh "docker exec commercial_industriel1-postgres-1 pg_restore -U postgres -d \\\"commercial-industriel\\\" /data.backup"
                }
            }
        }
      


        stage('Déploiement sur Tomcat') {
            steps {
     
                    // Déploiement de ma-gpro-design
                    deploy adapters: [tomcat9(credentialsId: 'Tomcat', path: '', url: 'http://54.242.23.170:8080/')], 
                           contextPath: '/ma-gpro-design-3.5.0.0-SNAPSHOT', 
                           war: 'ma-gpro-design-war/presentation/target/ma-gpro-design-3.5.0.0-SNAPSHOT.war'

              
          
    }
}


    }
}
