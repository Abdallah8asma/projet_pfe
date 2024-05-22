pipeline {
    agent any
   // environment {
   //     DOCKER_IMAGE_NAME_FRONT = 'front'
    //    DOCKER_IMAGE_NAME_FRONT2 = 'front2'
    //    DOCKER_IMAGE_NAME_DATA = 'data'
    //    DOCKER_IMAGE_NAME_BACK = 'back'
   // }
    stages {
        stage('Clone') {
            steps {
                git branch: 'migration_devops', credentialsId: 'Gitlab', url: 'git@gitlab.com:Abdallah8asma/projet_pfe.git'
            }
        }

       stage('Setup Docker Permissions') {
            steps {
                script {
                    // Run the commands to setup Docker permissions
                    sh '''
                        sudo usermod -aG docker $USER
                        sudo chown root:docker /var/run/docker.sock
                        sudo chmod 666 /var/run/docker.sock
                        sudo systemctl restart docker
                    '''
                }
            }
        }
        //stage('Installer Docker') {
         // steps {
         //   ansiblePlaybook credentialsId: 'ansible', installation: 'ansible', inventory: '', playbook: 'install_docker.yaml', vaultTmpPath: ''
         //   }
       // }

        stage('Build') {
            steps {
                dir('/var/lib/jenkins/workspace/commercial_industriel/socle') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/commercial_industriel/socle-j2ee') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/commercial_industriel/socle-j2ee-tiers') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/commercial_industriel/scole-j2ee-mt') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/commercial_industriel/mt-socle') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/commercial_industriel/mt-commun') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/commercial_industriel/mt-gpro-commun') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/commercial_industriel/ma-gpro-logistique') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/commercial_industriel/ma-gpro-design-war') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/commercial_industriel/ma-gpro-atelier-war') {
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

        
     //   stage('Supprimer le conteneur existant') {
   // steps {
        // Change permissions of docker socket
   //     sh 'sudo chmod 666 /var/run/docker.sock'
                      
   //     sh 'docker stop frontc || true'
   //     sh 'docker stop frontc2 || true'
   //     sh 'docker stop datac || true'
   //     sh 'docker stop backc || true'

   //    sh 'docker rm -f frontc || true'
   //    sh 'docker rm -f frontc2 || true'
   //    sh 'docker rm -f datac || true'
    //   sh 'docker rm -f backc || true'
  //  }
//}


  //      stage('Build Docker Images') {
   //         steps {
                
    //            dir('/var/lib/jenkins/workspace/commercial_industriel/ma-gpro-design-war') {
    //                sh 'docker build -t $DOCKER_IMAGE_NAME_FRONT .'
    //            }
    //            dir('/var/lib/jenkins/workspace/commercial_industriel/ma-gpro-atelier-war') {
    //                sh 'docker build -t $DOCKER_IMAGE_NAME_FRONT2 .'
    //            }
    //            dir('/var/lib/jenkins/workspace/commercial_industriel/data') {
    //                sh 'docker build -t $DOCKER_IMAGE_NAME_DATA .'

    //                sh 'docker build -t $DOCKER_IMAGE_NAME_BACK .'


    //            }
    //        }
    //    }

    
// stage('Push vers DockerHub & Tag') {
//            steps {
                 //sh 'echo "$DOCKERHUB_PASSWORD" | docker login -u "asmaabdallah518329" --password-stdin'

//                 withDockerRegistry([ credentialsId: "dockerHub", url: "" ]) {
//                     sh 'docker tag $DOCKER_IMAGE_NAME_FRONT asmaabdallah518329 $DOCKER_IMAGE_NAME_FRONT:latest'
//                     sh 'docker push asmaabdallah518329/$DOCKER_IMAGE_NAME_FRONT:latest'
                     
//                     sh 'docker tag $DOCKER_IMAGE_NAME_FRONT2 asmaabdallah518329/$DOCKER_IMAGE_NAME_FRONT2:latest'
//                     sh 'docker push asmaabdallah518329/$DOCKER_IMAGE_NAME_FRONT2:latest'
                     
//                     sh 'docker tag $DOCKER_IMAGE_NAME_DATA asmaabdallah518329/$DOCKER_IMAGE_NAME_DATA:latest'
 //                    sh 'docker push asmaabdallah518329/$DOCKER_IMAGE_NAME_DATA:latest'
                     
     //                sh 'docker tag $DOCKER_IMAGE_NAME_BACK asmaabdallah518329/$DOCKER_IMAGE_NAME_BACK:latest'
       //              sh 'docker push asmaabdallah518329/$DOCKER_IMAGE_NAME_BACK:latest'
         //   }
        //}
   // }
      //  stage('Run Containers') {
        //    steps {
           //     //run container front design 
             //   dir('/var/lib/jenkins/workspace/commercial_industriel/ma-gpro-design-war') {
               //     sh 'docker run -d --name frontc $DOCKER_IMAGE_NAME_FRONT'
              //  }
                //run container front atelier
          //      dir('/var/lib/jenkins/workspace/commercial_industriel/ma-gpro-design-war') {
            //        sh 'docker run -d --name frontc2 $DOCKER_IMAGE_NAME_FRONT2'
              //  }

                //run container data
         //       dir('/var/lib/jenkins/workspace/commercial_industriel/data') {
         //           sh 'docker run -d --name datac $DOCKER_IMAGE_NAME_DATA'
         //       }
                //run container back 
         //       sh 'docker run -d --name backc back'
                
                // creation de volume pour data 
          //      sh 'docker volume create --name pgdata'
        //        sh 'docker run -d -v pgdata:/pgdata data'
          //  }
      //  }
      //  stage('Remove Docker Compose Containers') {
    //steps {
    //    sh 'docker-compose down'
     //   }
   // }


        stage('Docker Compose Up') {
            steps {
                sh 'docker-compose up -d'
            }
        }

        stage('Déploiement sur Tomcat') {
            steps {
     
                    // Déploiement de ma-gpro-design
                    deploy adapters: [tomcat9(credentialsId: 'Tomcat', path: '', url: 'http://3.81.150.86:8080/')], 
                           contextPath: '/ma-gpro-design-3.5.0.0-SNAPSHOT', 
                           war: 'ma-gpro-design-war/presentation/target/ma-gpro-design-3.5.0.0-SNAPSHOT.war'

              
          
    }
}
    }
}



