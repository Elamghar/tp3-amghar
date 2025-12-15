pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'JDK17'
    }

    stages {

        stage('Checkout') {
            steps {
                git 'https://github.com/Elamghar/tp3-amghar.git'
            }
        }

        stage('Build & Tests') {
            steps {
                sh 'mvn clean test'
            }
        }
    }

    post {
        always {
            junit 'target/surefire-reports/*.xml'
        }

        success {
            echo '✅ Build et tests réussis'
        }

        failure {
            echo '❌ Échec du build ou des tests'
        }
    }
}
