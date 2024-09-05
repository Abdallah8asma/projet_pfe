pipeline {
    agent any
    environment {
        DOCKER_IMAGE_NAME_FRONT = 'asmaabdallah518329/front'
        DOCKER_IMAGE_NAME_DATA = 'asmaabdallah518329/data'
        DOCKER_IMAGE_NAME_BACK_COMMUN = 'asmaabdallah518329/mt-gpro-commun-rest'
        DOCKER_IMAGE_NAME_BACK_LOGISTIQUE = 'asmaabdallah518329/ma-gpro-logistique-rest'
        BUILD_NUMBER = "${BUILD_NUMBER}"
    }
       stages {
        stage('Clone') {
            steps {
                git branch: 'migration_devops', credentialsId: 'Gitlab', url: 'git@gitlab.com:Abdallah8asma/pfe_docker.git'
            }
        }

            stage('Build') {
            steps {
                dir('/var/lib/jenkins/workspace/pfe_docker/socle') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/pfe_docker/socle-j2ee') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/pfe_docker/socle-j2ee-tiers') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/pfe_docker/scole-j2ee-mt') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/pfe_docker/mt-socle') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/pfe_docker/mt-commun') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/pfe_docker/mt-gpro-commun') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/pfe_docker/ma-gpro-logistique') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/pfe_docker/ma-gpro-design-war') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/pfe_docker/ma-gpro-atelier-war') {
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


        
      stage('Clean Docker') {
            steps {
                script {
                    sh '''
                        docker stop $(docker ps -aq) || true
                        docker rm $(docker ps -aq) || true
                        docker rmi -f $(docker image ls -q) || true
                    '''
                }
            }
        }
 

stage('Build Docker Images') {
            steps {
                sh 'docker build -t asmaabdallah518329/front -f dockerfile-front .'

                sh 'docker build -t asmaabdallah518329/ma-gpro-logistique-rest -f dockerfile-logistique .'
                
                sh 'docker build -t asmaabdallah518329/mt-gpro-commun-rest -f dockerfile-commun .'

                sh 'docker build -t asmaabdallah518329/data -f dockerfile-data .'
            }
        }
               stage('Run Containers') {
             steps {
                sh 'docker run -d --name frontc $DOCKER_IMAGE_NAME_FRONT'

                sh 'docker run -d --name backl $DOCKER_IMAGE_NAME_BACK_LOGISTIQUE '

                sh 'docker run -d --name backc $DOCKER_IMAGE_NAME_BACK_COMMUN'
                
                sh 'docker volume create --name pgdata'
                sh 'docker run -d -v pgdata:/pgdata --name datac $DOCKER_IMAGE_NAME_DATA'

          }
       }
    
 stage('Push to DockerHub & Tag') {
            steps {
                withDockerRegistry([credentialsId: "dockerHub", url: ""]) {
                    sh 'docker tag asmaabdallah518329/front asmaabdallah518329/front:latest'
                    sh 'docker push asmaabdallah518329/front:latest' 
                    
                    sh 'docker tag asmaabdallah518329/ma-gpro-logistique-rest asmaabdallah518329/ma-gpro-logistique-rest:latest'
                    sh 'docker push asmaabdallah518329/ma-gpro-logistique-rest:latest'
                    
                    sh 'docker tag asmaabdallah518329/mt-gpro-commun-rest asmaabdallah518329/mt-gpro-commun-rest:latest'
                    sh 'docker push asmaabdallah518329/mt-gpro-commun-rest:latest'
                    
                    sh 'docker tag asmaabdallah518329/data asmaabdallah518329/data:latest'
                    sh 'docker push asmaabdallah518329/data:latest'
                }
            }
        }       

  

        }
}
