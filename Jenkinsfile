pipeline {
    agent any
    stages {
        stage("build") {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }
        stage("test") {
            steps {
                echo 'testing....'
            }
        }
        stage("deploy") {
            steps {
                sh "mvn spring-boot:run"
            }
        }
    }
}