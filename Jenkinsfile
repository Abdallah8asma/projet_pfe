pipeline {
    agent any
    environment {
        DOCKER_IMAGE_NAME_FRONT = 'front'
        DOCKER_IMAGE_NAME_DATA = 'data'
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
                dir('/var/lib/jenkins/workspace/Pfe1/socle') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/Pfe1/socle-j2ee') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/Pfe1/socle-j2ee-tiers') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/Pfe1/scole-j2ee-mt') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/Pfe1/mt-socle') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/Pfe1/mt-commun') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/Pfe1/mt-gpro-commun') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/Pfe1/ma-gpro-logistique') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/Pfe1/ma-gpro-design-war') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/Pfe1/ma-gpro-atelier-war') {
                    sh 'mvn clean install'
                }
            }
        }

        stage('Build Docker Images') {
            steps {
                dir('/var/lib/jenkins/workspace/Pfe1/ma-gpro-design-war') {
                    sh 'docker build -t $DOCKER_IMAGE_NAME_FRONT .'
                }
                dir('/var/lib/jenkins/workspace/Pfe1/data') {
                    sh 'docker build -t $DOCKER_IMAGE_NAME_DATA .'
                }
            }
        }

        stage('Run Containers') {
            steps {
                //run container front 
                dir('/var/lib/jenkins/workspace/Pfe1/ma-gpro-design-war') {
                    sh 'docker run -d --name frontc -p 80:80 $DOCKER_IMAGE_NAME_FRONT'
                }
                //run container data
                dir('/var/lib/jenkins/workspace/Pfe1/data') {
                    sh 'docker run -d --name datac $DOCKER_IMAGE_NAME_DATA'
                }
                //run container back 
                sh 'docker run -d --name backc -p 8087:8087 back'
                // creation de volume pour data 
                sh 'docker volume create --name pgdata'
                sh 'docker run -d -v pgdata:/pgdata data'
            }
        }

        stage('Docker Compose Up') {
            steps {
                sh 'docker-compose up -d'
            }
        }

        stage('Déploiement sur Tomcat') {
            steps {
                sh 'docker cp /var/lib/jenkins/workspace/Pfe1/ma-gpro-design-war/presentation/target/ma-gpro-design-3.5.0.0-SNAPSHOT.war frontc:/opt/tomcat/latest/webapps'
                // déploiement sur Tomcat à l'adresse 52.205.136.205
                deploy adapters: [tomcat9(credentialsId: 'Tomcat', path: '', url: 'http://52.205.136.205:8080/')], contextPath: '/ma-gpro-design', war: '**/*.war'

            }
        }
    }
}


