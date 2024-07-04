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
                dir('/var/lib/jenkins/workspace/projet_pfe/socle') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/projet_pfe/socle-j2ee') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/projet_pfe/socle-j2ee-tiers') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/projet_pfe/scole-j2ee-mt') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/projet_pfe/mt-socle') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/projet_pfe/mt-commun') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/projet_pfe/mt-gpro-commun') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/projet_pfe/ma-gpro-logistique') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/projet_pfe/ma-gpro-design-war') {
                    sh 'mvn clean install'
                }
                dir('/var/lib/jenkins/workspace/projet_pfe/ma-gpro-atelier-war') {
                    sh 'mvn clean install'
                }
            }
        }
        



stage('Slack notification') {
    steps {
        script {
            def buildStatus = currentBuild.currentResult
            def message = "Pipeline Status: ${buildStatus}\n" +
                          "Job Name: ${env.JOB_NAME}\n" +
                          "Build Number: ${env.BUILD_NUMBER}\n" +
                          "Build URL: ${env.BUILD_URL}"

            if (buildStatus == 'SUCCESS') {
                slackSend(channel: '#devopss', message: "Pipeline Succeeded\n${message}")
            } else {
                slackSend(channel: '#devopss', message: "Pipeline Failed\n${message}")
            }
        }
    }
}

stage('stock war file'){
    steps {
        script {
            def version =  "${BUILD_NUMBER}"
            def projects = [
                [artifactId: 'mt-socle', groupId: 'com.gpro.consulting.socle.j2ee.mt', path: '/var/lib/jenkins/workspace/projet_pfe/mt-socle/target/mt-socle-3.5.0.0-SNAPSHOT.jar', type: 'jar'],

                [artifactId: 'mt-commun', groupId: 'com.gpro.consulting.socle.j2ee.mt', path: '/var/lib/jenkins/workspace/projet_pfe/mt-commun/target/mt-commun-3.5.0.0-SNAPSHOT.jar', type: 'jar'],

                [artifactId: 'mt-gpro-commun-rest', groupId: 'com.gpro.consulting.tiers.commun.rest', path: '/var/lib/jenkins/workspace/projet_pfe/mt-gpro-commun/mt-gpro-commun-rest/target/mt-gpro-commun-rest-3.5.0.0-SNAPSHOT.war', type: 'war'],

                [artifactId: 'mt-gpro-logistique-rest', groupId: 'com.gpro.consulting.tiers.logistique.rest', path: '/var/lib/jenkins/workspace/projet_pfe/ma-gpro-logistique/ma-gpro-logistique-rest/target/ma-gpro-logistique-rest-3.5.0.0-SNAPSHOT.war', type: 'war'],

                [artifactId: 'ma-gpro-design', groupId: 'com.gpro.consulting.tiers.presentation', path: '/var/lib/jenkins/workspace/projet_pfe/ma-gpro-design-war/presentation/target/ma-gpro-design-3.5.0.0-SNAPSHOT.war', type: 'war'],
                
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
                    nexusUrl: '54.167.145.36:8081',
                    nexusVersion: 'nexus3',
                    protocol: 'http',
                    repository: 'devops', 
                    version: version
                )
            }
        }
    }
}

     
 stage('SonarQube Analysis') {
    steps {
        script {
            def projects = [
                [path: '/var/lib/jenkins/workspace/projet-commercial/socle', projectKey: 'socle'],
                [path: '/var/lib/jenkins/workspace/projet-commercial/socle-j2ee', projectKey: 'socle-j2ee'],
                [path: '/var/lib/jenkins/workspace/projet-commercial/socle-j2ee-tiers', projectKey: 'socle-j2ee-tiers'],
                [path: '/var/lib/jenkins/workspace/projet-commercial/scole-j2ee-mt', projectKey: 'scole-j2ee-mt'],
                [path: '/var/lib/jenkins/workspace/projet-commercial/mt-socle', projectKey: 'mt-socle'],
                [path: '/var/lib/jenkins/workspace/projet-commercial/mt-commun', projectKey: 'mt-commun'],
                [path: '/var/lib/jenkins/workspace/projet-commercial/mt-gpro-commun', projectKey: 'mt-gpro-commun'],
                [path: '/var/lib/jenkins/workspace/projet-commercial/ma-gpro-logistique', projectKey: 'ma-gpro-logistique'],
                [path: '/var/lib/jenkins/workspace/projet-commercial/ma-gpro-design-war', projectKey: 'ma-gpro-design-war'],
                [path: '/var/lib/jenkins/workspace/projet-commercial/ma-gpro-atelier-war', projectKey: 'ma-gpro-atelier-war']
            ]
            withSonarQubeEnv('SonarQube') {
                for (proj in projects) {
                    dir(proj.path) {
                        // Execute 'mvn clean package sonar:sonar'
                        sh "mvn clean verify sonar:sonar -Dsonar.projectKey=${proj.projectKey} -Dsonar.host.url=http://54.175.17.55:9000  -Dsonar.login=admin -Dsonar.password=sonar"
                    }
                }
            }
        }
    }
}


        
       stage('Déploiement sur Tomcat') {
            steps {
     
               deploy adapters: [tomcat9(credentialsId: 'Tomcat', path: '', url: 'http://54.163.153.253:8080')], contextPath: '/ma-gpro-design-3.5.0.0-SNAPSHOT', war: 'ma-gpro-design-war/presentation/target/ma-gpro-design-3.5.0.0-SNAPSHOT.war'
 }
}


        }
  
}
