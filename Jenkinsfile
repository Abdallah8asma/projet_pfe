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
                git branch: 'migration_devops', credentialsId: 'Gitlab', url: 'git@gitlab.com:Abdallah8asma/pfe_docker_compose.git'
            }
        }

            stage('Build') {
            steps {
                dir('/var/lib/jenkins/workspace/Pfe_docker_compose/socle') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/Pfe_docker_compose/socle-j2ee') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/Pfe_docker_compose/socle-j2ee-tiers') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/Pfe_docker_compose/scole-j2ee-mt') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/Pfe_docker_compose/mt-socle') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/Pfe_docker_compose/mt-commun') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/Pfe_docker_compose/mt-gpro-commun') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/Pfe_docker_compose/ma-gpro-logistique') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/Pfe_docker_compose/ma-gpro-design-war') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/Pfe_docker_compose/ma-gpro-atelier-war') {
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





stage('Remove Docker Compose Containers') {
     steps {
        sh 'docker-compose down'
        }
    }
  stage('Docker Compose Up') {
            steps {
                sh 'docker-compose up --build -d'
            }
        }
        
        stage('Healthcheck') {
            steps {
                script {
                    def frontendStatus = sh(script: "docker inspect --format='\\{{json .State.Health}}\\' $(docker ps -q -f name=frontend) | jq .Status", returnStdout: true).trim()
                    def backendStatus = sh(script: "docker inspect --format='\\{{json .State.Health}}\\' $(docker ps -q -f name=backend) | jq .Status", returnStdout: true).trim()
                    def postgresStatus = sh(script: "docker inspect --format='\\{{json .State.Health}}\\' $(docker ps -q -f name=postgres) | jq .Status", returnStdout: true).trim()

                    if (frontendStatus != 'healthy' || backendStatus != 'healthy' || postgresStatus != 'healthy') {
                        error "One or more services are not healthy: frontend=${frontendStatus}, backend=${backendStatus}, postgres=${postgresStatus}"
            }
        }
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
                    
                }
            }
        }       


        } 
}