pipeline {
    agent any
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
            def buildStatus = currentBuild.currentResult
            def message = "Pipeline Status: ${buildStatus}\nJob Name: ${env.JOB_NAME}\nBuild Number: ${env.BUILD_NUMBER}\nBuild URL: ${env.BUILD_URL}"

            if (buildStatus == 'SUCCESS') {
                slackSend(channel: '#devops', message: "Pipeline Succeeded\n${message}")
            } else {
                slackSend(channel: '#devops', message: "Pipeline Failed\n${message}")
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

stage('Suppression des conteneurs existants') {
    steps {
        sh 'docker stop $(docker ps -aq) && docker rm $(docker ps -aq)'
        sh 'docker rmi -f $(docker image ls -q)'
    
}
}
     stage('Build Docker Images') {
         steps {

             dir('/var/lib/jenkins/workspace/commercial_industriel/ma-gpro-design-war') {
            // Construction de l'image Docker pour le frontend
                sh 'docker build -t $DOCKER_IMAGE_NAME_FRONT .'        }

            // Construction de l'image Docker pour le data
                dir('/var/lib/jenkins/workspace/commercial_industriel/data') {
                    sh 'docker build -t $DOCKER_IMAGE_NAME_DATA .'}


            // Construction de l'image Docker pour mt-gpro-commun
                sh 'docker build -f Dockerfile.mt-gpro-commun -t mt-gpro-commun-rest:latest .'
     

            // Construction de l'image Docker pour ma-gpro-logistique
                sh 'docker build -f Dockerfile.ma-gpro-logistique -t ma-gpro-logistique-rest:latest .'
            }}


       stage('Run Containers') {
    steps {
        // Run container front design 
        dir('/var/lib/jenkins/workspace/commercial_industriel/ma-gpro-design-war') {
            sh 'docker run -d --name frontc $DOCKER_IMAGE_NAME_FRONT'
        }

        // Run container data
        dir('/var/lib/jenkins/workspace/commercial_industriel/data') {
            sh 'docker run -d --name datac $DOCKER_IMAGE_NAME_DATA'
        }

        // Run container backend
        sh 'docker run -d --name backc1 mt-gpro-commun-rest:latest'
        sh 'docker run -d --name backc2 ma-gpro-logistique-rest:latest'

        // Création de volume pour data 
        sh 'docker volume create --name pgdata'
        sh 'docker run -d -v pgdata:/pgdata data'
    }
}


      
stage('Push vers DockerHub & Tag') {
    steps {
        withDockerRegistry([credentialsId: "dockerHub", url: ""]) {

            // Tagging et push de l'image pour le frontend
            sh "docker tag $DOCKER_IMAGE_NAME_FRONT asmaabdallah518329/$DOCKER_IMAGE_NAME_FRONT:latest"
            sh "docker push asmaabdallah518329/$DOCKER_IMAGE_NAME_FRONT:latest"

            // Tagging et push de l'image pour mt-gpro-commun
            sh "docker tag mt-gpro-commun-rest:latest asmaabdallah518329/mt-gpro-commun-rest:latest"
            sh "docker push asmaabdallah518329/mt-gpro-commun-rest:latest"

            // Tagging et push de l'image pour ma-gpro-logistique
            sh "docker tag ma-gpro-logistique-rest:latest asmaabdallah518329/ma-gpro-logistique-rest:latest"
            sh "docker push asmaabdallah518329/ma-gpro-logistique-rest:latest"

            // Tagging et push de l'image pour la data
            sh "docker tag $DOCKER_IMAGE_NAME_DATA asmaabdallah518329/$DOCKER_IMAGE_NAME_DATA:latest"
            sh "docker push asmaabdallah518329/$DOCKER_IMAGE_NAME_DATA:latest"
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
                sh 'docker-compose up -d'
            }
        }

        stage('Déploiement sur Tomcat') {
            steps {
     
                    // Déploiement de ma-gpro-design
                    deploy adapters: [tomcat9(credentialsId: 'Tomcat', path: '', url: 'http://54.198.154.81:8080/')], 
                           contextPath: '/ma-gpro-design-3.5.0.0-SNAPSHOT', 
                           war: 'ma-gpro-design-war/presentation/target/ma-gpro-design-3.5.0.0-SNAPSHOT.war'

              
          
    }
}


    }
}
