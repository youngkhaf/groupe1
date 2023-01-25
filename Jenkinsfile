pipeline{
    agent any

    tools {
        maven 'MAVEN'
    }

    stages{
        stage('Source') {
            steps{
                git branch: 'main', url:'https://github.com/youngkhaf/groupe1'
            }
        }
        stage('Build') {
             steps{
                  sh 'mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent install'
             }
        }

        stage('SonarQube Analysis') {
              steps{
                   sh 'mvn sonar:sonar'
              }
        }

        stage('Build Docker image') {
                      steps{
                           sh 'docker build -t sirsoir2022/groupe1:latest .'
                      }
        }

        stage('Login to Docker Hub') {
                  steps{
                            sh 'docker login -u "sirsoir2022" -p "sirsoir2022"'
                  }
         }

        stage('Push to Docker Hub') {
                    steps{
                         sh 'docker push sirsoir2022/groupe1:latest'
                    }
        }

         stage('Deploy to Kubernetes') {
                    steps{
                         sh 'kubectl apply -f deploymentservice.yml'
                    }
         }
    } // stages

    post{
        aborted{
            echo "Sending aborted message to Agent"
        }
        failure{
            echo "Sending failure message to Agent"
        }
        success{
            echo "Sending success message to Agent"
        }
    } // post
}