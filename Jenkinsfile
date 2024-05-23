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
                        sudo usermod -aG docker $USER
                        sudo chown root:docker /var/run/docker.sock
                        sudo chmod 666 /var/run/docker.sock
                        sudo systemctl restart docker
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

 stage('Supprimer le conteneur existant') {
    steps {
        // Change permissions of docker socket
       sh 'sudo chmod 666 /var/run/docker.sock'
        sh 'docker stop frontc || true'
        sh 'docker stop backc || true'
       sh 'docker rm -f frontc || true'
       sh 'docker rm -f backc || true'
    }}
  stage('Build Docker Images') {
       steps {
              dir('/var/lib/jenkins/workspace/commercial_industriel/ma-gpro-design-war') {
                  sh 'docker build -t $DOCKER_IMAGE_NAME_FRONT .'
              }
                  sh 'docker build -t $DOCKER_IMAGE_NAME_BACK .'
        }}
  stage('Push vers DockerHub & Tag') {
           steps {
                 withDockerRegistry([ credentialsId: "dockerHub", url: "" ]) {
                     sh 'docker tag $DOCKER_IMAGE_NAME_FRONT asmaabdallah518329 $DOCKER_IMAGE_NAME_FRONT:latest'
                     sh 'docker push asmaabdallah518329/$DOCKER_IMAGE_NAME_FRONT:latest' 
                     
                     sh 'docker tag $DOCKER_IMAGE_NAME_BACK asmaabdallah518329/$DOCKER_IMAGE_NAME_BACK:latest'
                     sh 'docker push asmaabdallah518329/$DOCKER_IMAGE_NAME_BACK:latest'
          }}}

    stage('Run Containers') {
      steps {
           //run container front design 
              dir('/var/lib/jenkins/workspace/commercial_industriel/ma-gpro-design-war') {
                sh 'docker run -d --name frontc $DOCKER_IMAGE_NAME_FRONT'
             }
         //run container back 
               sh 'docker run -d --name backc back'
                
         creation de volume pour data 
              sh 'docker volume create --name pgdata'
               sh 'docker run -d -v pgdata:/pgdata data'
          }}



    }
}



