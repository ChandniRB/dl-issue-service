pipeline {
    agent any
    environment {
        DB_IP="localhost"
        DB_PORT="5432"
        DB_NAME_DL="dl"
        DB_USER="postgres"
        DB_PASSWORD="postgres"
        KAFKA_HOST="10.1.40.8"
    }
    tools{
        maven 'maven'
    }
    stages {
        stage("build") {
            steps {
                maven('maven'){
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
                maven('maven'){
                    sh "mvn spring-boot:run"
            }
        }
    }
}
