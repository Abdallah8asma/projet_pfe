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
                dir('/var/lib/jenkins/workspace/projet_PFE/socle') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/projet_PFE/socle-j2ee') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/projet_PFE/socle-j2ee-tiers') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/projet_PFE/scole-j2ee-mt') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/projet_PFE/mt-socle') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/projet_PFE/mt-commun') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/projet_PFE/mt-gpro-commun') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/projet_PFE/ma-gpro-logistique') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/projet_PFE/ma-gpro-design-war') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/projet_PFE/ma-gpro-atelier-war') {
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
                [artifactId: 'mt-socle', groupId: 'com.gpro.consulting.socle.j2ee.mt', path: '/var/lib/jenkins/workspace/projet_PFE/mt-socle/target/mt-socle-3.5.0.0-SNAPSHOT.jar', type: 'jar'],

                [artifactId: 'mt-commun', groupId: 'com.gpro.consulting.socle.j2ee.mt', path: '/var/lib/jenkins/workspace/projet_PFE/mt-commun/target/mt-commun-3.5.0.0-SNAPSHOT.jar', type: 'jar'],

                [artifactId: 'mt-gpro-commun-rest', groupId: 'com.gpro.consulting.tiers.commun.rest', path: '/var/lib/jenkins/workspace/projet_PFE/mt-gpro-commun/mt-gpro-commun-rest/target/mt-gpro-commun-rest-3.5.0.0-SNAPSHOT.war', type: 'war'],

                [artifactId: 'mt-gpro-logistique-rest', groupId: 'com.gpro.consulting.tiers.logistique.rest', path: '/var/lib/jenkins/workspace/projet_PFE/ma-gpro-logistique/ma-gpro-logistique-rest/target/ma-gpro-logistique-rest-3.5.0.0-SNAPSHOT.war', type: 'war'],

                [artifactId: 'ma-gpro-design', groupId: 'com.gpro.consulting.tiers.presentation', path: '/var/lib/jenkins/workspace/projet_PFE/ma-gpro-design-war/presentation/target/ma-gpro-design-3.5.0.0-SNAPSHOT.war', type: 'war'],
                
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
                    nexusUrl: '3.87.178.107:8081',
                    nexusVersion: 'nexus3',
                    protocol: 'http',
                    repository: 'Nexus', 
                    version: version
                )
            }
        }
    }
}



        
       stage('Déploiement sur Tomcat') {
            steps {
     
               deploy adapters: [tomcat9(credentialsId: 'Tomcat', path: '', url: 'http://54.167.248.145:8080/')], contextPath: '/ma-gpro-design-3.5.0.0-SNAPSHOT',  war: 'ma-gpro-design-war/presentation/target/ma-gpro-design-3.5.0.0-SNAPSHOT.war'
 }
}


        }
  
}
