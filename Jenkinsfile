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

        stage('Build Docker Images') {
            steps {
                dir('/var/lib/jenkins/workspace/commercial_industriel1/ma-gpro-design-war') {
                    sh 'docker build -t $DOCKER_IMAGE_NAME_FRONT .'
                }
                dir('/var/lib/jenkins/workspace/commercial_industriel1/data') {
                    sh 'docker build -t $DOCKER_IMAGE_NAME_DATA .'
                }
                sh 'docker build -f dockerfile-commun -t $DOCKER_IMAGE_NAME_BACK_COMMUN .'
                sh 'docker build -f dockerfile-logistique -t $DOCKER_IMAGE_NAME_BACK_LOGISTIQUE .'
            }
        }

        stage('Push Docker Images') {
            steps {
                withDockerRegistry([credentialsId: 'dockerHub', url: '']) {
                    sh 'docker push $DOCKER_IMAGE_NAME_FRONT'
                    sh 'docker push $DOCKER_IMAGE_NAME_DATA'
                    sh 'docker push $DOCKER_IMAGE_NAME_BACK_COMMUN'
                    sh 'docker push $DOCKER_IMAGE_NAME_BACK_LOGISTIQUE'
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                withKubeConfig(credentialsId: 'k8s', serverUrl: 'https://172.31.54.180:6443') {

            sh 'kubectl delete deployments --all || true' // Supprimer tous les déploiements existants
            sh 'kubectl delete services --all || true' // Supprimer tous les services existants

  

                    sh 'kubectl apply -f postgres-pv-pvc.yaml'
                    sh 'kubectl apply -f deployservice-postgres.yaml'

                    sh 'kubectl apply -f frontend-deployment.yaml' 

                    sh 'kubectl apply -f commun-deployment.yaml' 
                    sh 'kubectl apply -f logistique-deployment.yaml'

                    
                }
            }
        }
    }
}
