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
                dir('/var/lib/jenkins/workspace/PFE_Docker/socle') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/PFE_Docker/socle-j2ee') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/PFE_Docker/socle-j2ee-tiers') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/PFE_Docker/scole-j2ee-mt') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/PFE_Docker/mt-socle') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/PFE_Docker/mt-commun') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/PFE_Docker/mt-gpro-commun') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/PFE_Docker/ma-gpro-logistique') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/PFE_Docker/ma-gpro-design-war') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/PFE_Docker/ma-gpro-atelier-war') {
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



 stage('Installer Docker') {
       steps {
        ansiblePlaybook credentialsId: 'ansible', installation: 'ansible', inventory: '', playbook: 'install_docker.yaml', vaultTmpPath: ''
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

         stage('Run Containers') {
      steps {
           //run container front design 
                sh 'docker run -d --name frontc $DOCKER_IMAGE_NAME_FRONT'
            
         //run container back logistique
               sh 'docker run -d --name backl $DOCKER_IMAGE_NAME_BACK_LOGISTIQUE '

         //run container back commun
               sh 'docker run -d --name backc $DOCKER_IMAGE_NAME_BACK_COMMUN'
                
         //creation de volume pour data 
              sh 'docker volume create --name pgdata'
              sh 'docker run -d -v pgdata:/pgdata --name datac $DOCKER_IMAGE_NAME_DATA'

             // sh 'docker run -d -v pgdata:/pgdata $DOCKER_IMAGE_NAME_DATA'


          }
       }

     


        }
  
}
