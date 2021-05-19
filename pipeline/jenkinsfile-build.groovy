#!groovy

def APP_REPOSITORY = "https://github.com/jeancbezerra/app-rest-demo.git"

pipeline {

    agent any
    
    tools{
        jdk "ADOPTOPENJDK_jdk8u292-b10"
	maven "MAVEN_3_8_1"
    }
    options {
        timestamps()
        buildDiscarder(logRotator(numToKeepStr:'3'))
	timeout(time: 4, unit: 'MINUTES')
    }

    stages {
       stage("Application Repository"){
            steps {
		sh 'git config --global http.sslVerify false'
		timeout(time: 2, unit: "MINUTES") {
			git branch: "master",
			credentialsId: "github-up-jeancbezerra",
			url: "${APP_REPOSITORY}"
		}
            }
        }
		
        stage("Build Application"){
            steps {
                sh "mvn clean package -DskipTests"
            }
        }

        post {
            success{
                archiveArtifacts artifacts: 'app-rest-demo.war'
                cleanWs()
            }failure{
                cleanWs()
            }
        }
    }	
}
