pipeline {
    agent any
    environment {
        DB_IP="localhost"
        DB_PORT="5432"
        DB_NAME_DL="dl"
        DB_USER="postgres"
        DB_PASSWORD="postgres"
        KAFKA_HOST="10.1.40.8"
        SERVER_CREDENTIALS= credentials('postgres')
    }
    tools{
        maven 'maven'
    }
    stages {
        stage("build") {
            when {
                expression {
                    BRANCH_NAME == 'main'
                }
            }
            steps {
                maven('maven'){
                    sh 'mvn clean install -DskipTests'
            }
        }
        stage("test") {
            when {
                expression {
                    BRANCH_NAME == 'main'
                }
            }
            
            steps {
                echo 'testing....'
            }
        }
        stage("deploy") {
            steps {
                withCredentials([
                    usernamePassword(credentials: 'postgres', usernameVariable: USER, passwordVariable: PASSWORD)
                ]){
                    sh 'java -jar dl-issue-service.jar --DB_USER=${USER} --DB_PASSWORD=${PASSWORD}'
                }
                
                maven('maven'){
                    sh "mvn spring-boot:run"
            }
        }
    }
    post {
        always {
        }
        success {
        }
        failure {
        }
    }
}
