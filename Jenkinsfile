pipeline {
    agent any
    environment {
        DOCKER_IMAGE_NAME_FRONT = 'front'
        DOCKER_IMAGE_NAME_DATA = 'data'
        DOCKER_IMAGE_NAME_BACK = 'back'
        BUILD_NUMBER = "${BUILD_NUMBER}"
    }

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
        
       stage('Suppression de conteneur existant') {
    steps {
      sh 'docker stop $(docker ps -aq) && docker rm $(docker ps -aq)'   
      sh 'docker rmi -f $(docker image ls -q)'
  }
}


        stage('Build Docker Images') {
            steps {
                
                dir('/var/lib/jenkins/workspace/commercial_industriel/ma-gpro-design-war') {
                    sh 'docker build -t $DOCKER_IMAGE_NAME_FRONT .'
                }
                dir('/var/lib/jenkins/workspace/commercial_industriel/data') {
                    sh 'docker build -t $DOCKER_IMAGE_NAME_DATA .'
                }
                dir('/var/lib/jenkins/workspace/commercial_industriel') {
                    sh 'docker build -t $DOCKER_IMAGE_NAME_BACK .'
                }
            }
        }

        stage('Run Containers') {
            steps {
                //run container front design 
                 dir('/var/lib/jenkins/workspace/commercial_industriel/ma-gpro-design-war') {
                   sh 'docker run -d --name frontc $DOCKER_IMAGE_NAME_FRONT'
                }

                //run container data
                 dir('/var/lib/jenkins/workspace/commercial_industriel/data') {
                    sh 'docker run -d --name datac $DOCKER_IMAGE_NAME_DATA'
               }
                //run container back 
                   sh 'docker run -d --name backc back'
                
                // creation de volume pour data 
                  sh 'docker volume create --name pgdata'
                  sh 'docker run -d -v pgdata:/pgdata data'
            }
       }


      //  stage('Remove Docker Compose Containers') {
    //steps {
    //    sh 'docker-compose down'
     //   }
   // }


        stage('Déploiement sur Tomcat') {
            steps {
     
                    // Déploiement de ma-gpro-design
                    deploy adapters: [tomcat9(credentialsId: 'Tomcat', path: '', url: 'http://3.85.220.158:8080/')], 
                           contextPath: '/ma-gpro-design-3.5.0.0-SNAPSHOT', 
                           war: 'ma-gpro-design-war/presentation/target/ma-gpro-design-3.5.0.0-SNAPSHOT.war'

              
          
    }
}
    }
}



