pipeline
{
    agent any
    stages{
        stage('Clone') {
          
            steps{
              git branch: 'migration_devops', credentialsId: 'Gitlab', url: 'git@gitlab.com:samer.gproconsulting/commercial-industriel.git'

        }
        }
        
        stage('Build') {

            steps{
                sh 'mvn clean install package'
            
        }}

        stage('Deploy'){
            steps{
                deploy adapters: [tomcat9(credentialsId: 'tomcat', path: '', url: 'http://18.232.60.110:8080/')], contextPath: null, war: '**/*.war'
    }

  }

          

}

}


