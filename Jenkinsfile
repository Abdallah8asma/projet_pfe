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
                git branch: 'migration_devops', credentialsId: 'Gitlab', url: 'git@gitlab.com:Abdallah8asma/pfe_k8s.git'
            }
        }

            stage('Build') {
            steps {
                dir('/var/lib/jenkins/workspace/kubernetes/socle') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/kubernetes/socle-j2ee') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/kubernetes/socle-j2ee-tiers') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/kubernetes/scole-j2ee-mt') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/kubernetes/mt-socle') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/kubernetes/mt-commun') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/kubernetes/mt-gpro-commun') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/kubernetes/ma-gpro-logistique') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/kubernetes/ma-gpro-design-war') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/kubernetes/ma-gpro-atelier-war') {
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
                sh 'docker build -t asmaabdallah518329/data -f dockerfile-data .'
                sh 'docker build -t asmaabdallah518329/ma-gpro-logistique-rest -f dockerfile-logistique .'
                sh 'docker build -t asmaabdallah518329/mt-gpro-commun-rest -f dockerfile-commun .'
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


stage("Deploy to Kubernetes") {
    steps {
        withKubeConfig(credentialsId: 'k8s', serverUrl: 'https://172.31.87.67:6443') {

            // Appliquer la définition du volume persistant et du volume persistant revendiqué
          //  sh 'kubectl apply -f postgres-pv-pvc.yaml'
          sh 'kubectl apply --validate=false -f postgres-pv-pvc.yaml'

            // Déployer le service frontend
            sh 'kubectl apply --validate=false -f frontend-deployment.yaml' 

            // Déployer le service logistique
            sh 'kubectl apply --validate=false -f logistique-deployment.yaml' 

            // Déployer le service commun
            sh 'kubectl apply --validate=false -f commun-deployment.yaml' 

            // Déployer le service de base de données PostgreSQL
            sh 'kubectl apply --validate=false -f deployservice-postgres.yaml' 
        }
    }
}

    
}

}