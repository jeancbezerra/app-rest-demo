#!groovy

pipeline {	
    agent any
	
	parameters {
		string(name: "APP_SCM_BRANCH", defaultValue: "master", description: "Branch do Repositorio")
		string(name: "APP_SCM_REPOSITORY", defaultValue: "https://github.com/jeancbezerra/app-rest-demo.git", description: "Repositorio do Robo")
		string(name: "BOT_SCRIPT", defaultValue: "bot/bot-jmeter-4.jmx", description: "Nome do Robo jmx")
		string(name: "BOT_THREADS", defaultValue: "2", description: "Quantidade de threads (VUs) utilizadas no Robo")
		string(name: "BOT_RAMPUP", defaultValue: "60", description: "Tempo maximo para subir todas as threads (VUs) durante o teste")
		string(name: "BOT_DURATION", defaultValue: "120", description: "Tempo de duracao do teste em segundos")
		string(name: "BOT_STARTUPDELAY", defaultValue: "2", description: "Tempo de espera para inicializar o teste")        
		string(name: "STAGE_TIMEOUT", defaultValue: "300", description: "Timeout do Job em segundos")		
	}
	
    tools{
        jdk "ADOPTOPENJDK_jdk8u292-b10"
	maven "MAVEN_3_8_1"
    }
    options {
        timestamps()
        buildDiscarder(logRotator(numToKeepStr:'3'))
    }
    stages {
       stage("Application Repository"){
            steps {
		    sh 'git config --global http.sslVerify false'
		    timeout(time: 2, unit: "MINUTES") {
			    git branch: "${APP_SCM_BRANCH}",
		    credentialsId: "github-up-jeancbezerra",
		    url: "${APP_SCM_REPOSITORY}"
				}
            }           
        }		

        stage("Bot Execution"){
            steps {
		timeout(time: "${STAGE_TIMEOUT}", unit: "SECONDS"){
                	sh "/opt/coe/JMETER/apache-jmeter-4.0/bin/jmeter.sh --nongui --testfile ${env.JENKINS_HOME}/workspace/${JOB_NAME}/${BOT_SCRIPT} --jmeterproperty threads=${BOT_THREADS} --jmeterproperty rampup=${BOT_RAMPUP} --jmeterproperty duration=${BOT_DURATION} --jmeterproperty startup=${BOT_STARTUPDELAY} --jmeterproperty aggregate=load-test.csv"
		}
            }
        }

        	    
    }    
	
    post {
        always {
            echo 'One way or another, I have finished'
	    archiveArtifacts artifacts: 'load-test.csv'
	    sleep(time: 2, unit: "SECONDS")
            cleanWs()
        }
        success {
            echo 'I succeeded!'            
            cleanWs()
        }
        unstable {
            echo 'I am unstable :/'
            cleanWs()
        }
        failure {
            echo 'I failed :('
            cleanWs()
        }
        changed {
            echo 'Things were different before...'
            cleanWs()
        }
    }
	
}
