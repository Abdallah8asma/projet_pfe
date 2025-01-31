pipeline {
    agent any

       stages {
        stage('Clone') {
            steps {
                git branch: 'main', credentialsId: 'Gitlab', url: 'git@github.com:Abdallah8asma/projet_pfe.git'
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


}


        }
  

