pipeline {
    agent any
    environment {
        DOCKER_IMAGE_NAME_FRONT = 'front'
        DOCKER_IMAGE_NAME_DATA = 'data'
        DOCKER_IMAGE_NAME_BACK = 'back'
    }
    stages {
        stage('Clone') {
            steps {
                git branch: 'migration_devops', credentialsId: 'Gitlab', url: 'git@gitlab.com:Abdallah8asma/projet_pfe.git'
            }
        }

        //stage('Installer Docker') {
          //  steps {
            //    ansiblePlaybook credentialsId: 'ansible', installation: 'ansible', inventory: '', playbook: 'install_docker.yaml', vaultTmpPath: ''
            //}
        //}

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
        stage('Supprimer le conteneur existant') {
    steps {
        // Change permissions of docker socket
        sh 'sudo chmod 666 /var/run/docker.sock'
        sh 'docker stop -f frontc || true'
        sh 'docker stop -f datac || true'
        sh 'docker stop -f backc || true'
        sh 'docker rm -f frontc || true'
        sh 'docker rm -f datac || true'
        sh 'docker rm -f backc || true'
    }
}


        stage('Build Docker Images') {
            steps {
                
                dir('/var/lib/jenkins/workspace/commercial_industriel/ma-gpro-design-war') {
                    sh 'docker build -t $DOCKER_IMAGE_NAME_FRONT .'
                }
                dir('/var/lib/jenkins/workspace/commercial_industriel/data') {
                    sh 'docker build -t $DOCKER_IMAGE_NAME_DATA .'

                    sh 'docker build -t $DOCKER_IMAGE_NAME_BACK .'


                }
            }
        }


        stage('Run Containers') {
            steps {
                //run container front 
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
        stage('Remove Docker Compose Containers') {
    steps {
        dir('/var/lib/jenkins/workspace/commercial_industriel') {
            sh 'docker-compose down'
        }
    }
}


        stage('Docker Compose Up') {
            steps {
                sh 'docker-compose up -d'
            }
        }

        stage('Déploiement sur Tomcat') {
            steps {
        // Copier le fichier WAR dans le conteneur frontend
        sh 'docker cp /var/lib/jenkins/workspace/commercial_industriel/ma-gpro-design-war/presentation/target/ma-gpro-design-3.5.0.0-SNAPSHOT.war frontc:/opt/tomcat/latest/webapps'

     withCredentials([usernamePassword(credentialsId: 'Tomcat', usernameVariable: 'admin', passwordVariable: 'admin')]) {
            sh "docker exec frontc curl -v --upload-file /opt/tomcat/latest/webapps/ma-gpro-design-3.5.0.0-SNAPSHOT.war 'http://3.81.24.225:8080/manager/text/deploy?path=/ma-gpro-design-3.5.0.0-SNAPSHOT&update=true' -u $TOMCAT_USERNAME:$TOMCAT_PASSWORD"
        }
    
}

        }


                // déploiement sur Tomcat 
                //deploy adapters: [tomcat9(credentialsId: 'Tomcat', path: '', url: 'http://107.20.69.49:8080/')], contextPath: '/ma-gpro-design-3.5.0.0-SNAPSHOT', war: '**/*.war'

          
    }
}


