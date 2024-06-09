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
                dir('/var/lib/jenkins/workspace/Pfe_docker/socle') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/Pfe_docker/socle-j2ee') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/Pfe_docker/socle-j2ee-tiers') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/Pfe_docker/scole-j2ee-mt') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/Pfe_docker/mt-socle') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/Pfe_docker/mt-commun') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/Pfe_docker/mt-gpro-commun') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/Pfe_docker/ma-gpro-logistique') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/Pfe_docker/ma-gpro-design-war') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/Pfe_docker/ma-gpro-atelier-war') {
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

stage('stock war file'){
    steps {
        script {
            def version =  "${BUILD_NUMBER}"
            def projects = [
                [artifactId: 'mt-socle', groupId: 'com.gpro.consulting.socle.j2ee.mt', path: '/var/lib/jenkins/workspace/Pfe_docker/mt-socle/target/mt-socle-3.5.0.0-SNAPSHOT.jar', type: 'jar'],

                [artifactId: 'mt-commun', groupId: 'com.gpro.consulting.socle.j2ee.mt', path: '/var/lib/jenkins/workspace/Pfe_docker/mt-commun/target/mt-commun-3.5.0.0-SNAPSHOT.jar', type: 'jar'],

                [artifactId: 'mt-gpro-commun-rest', groupId: 'com.gpro.consulting.tiers.commun.rest', path: '/var/lib/jenkins/workspace/Pfe_docker/mt-gpro-commun/mt-gpro-commun-rest/target/mt-gpro-commun-rest-3.5.0.0-SNAPSHOT.war', type: 'war'],

                [artifactId: 'mt-gpro-logistique-rest', groupId: 'com.gpro.consulting.tiers.logistique.rest', path: '/var/lib/jenkins/workspace/Pfe_docker/ma-gpro-logistique/ma-gpro-logistique-rest/target/ma-gpro-logistique-rest-3.5.0.0-SNAPSHOT.war', type: 'war'],

                [artifactId: 'ma-gpro-design', groupId: 'com.gpro.consulting.tiers.presentation', path: '/var/lib/jenkins/workspace/Pfe_docker/ma-gpro-design-war/presentation/target/ma-gpro-design-3.5.0.0-SNAPSHOT.war', type: 'war'],
                
            ]

            for (proj in projects) {
                nexusArtifactUploader(
                    artifacts: [
                        [
                            artifactId: proj.artifactId,
                            classifier: '',
                            file: proj.path,
                            type: proj.type
                        ]
                    ],
                    credentialsId: 'nexus',
                    groupId: proj.groupId,
                    nexusUrl: '54.90.87.233:8081',
                    nexusVersion: 'nexus3',
                    protocol: 'http',
                    repository: 'Nexus', 
                    version: version
                )
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