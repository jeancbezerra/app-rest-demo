#!groovy




pipeline {	
    agent any
	
parameters {
        string(name: "BRANCH", defaultValue: "master", description: "Branch do Repositorio")
        string(name: "REPOSITORY", defaultValue: "https://github.com/jeancbezerra/app-rest-demo.git", description: "Repositorio do Robo")
        string(name: "BOT_SCRIPT", defaultValue: "bot/bot-jmeter-4.jmx", description: "Nome do Robo jmx")
        string(name: "BOT_NUMBER_THREADS", defaultValue: "2", description: "Quantidade de threads (VUs) utilizadas no Robo")
        string(name: "BOT_LOAD_RAMPUP", defaultValue: "60", description: "Tempo maximo para subir todas as threads (VUs) durante o teste")
        string(name: "BOT_LOAD_DURATION", defaultValue: "120", description: "Tempo de duracao do teste")
        string(name: "BOT_EXECUTION_TIMEOUT", defaultValue: "300", description: "Timeout do teste em minutos")
        string(name: "BOT_STARTUPDELAY", defaultValue: "2", description: "Tempo de espera para inicializar o teste")        
}
	
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
			    git branch: "${BRANCH}",
		    credentialsId: "github-up-jeancbezerra",
		    url: "${REPOSITORY}"
				}
            }           
        }		

        stage("Bot Execution"){
            steps {
                sh "/opt/coe/JMETER/apache-jmeter-4.0/bin/jmeter.sh --nongui --testfile ${env.JENKINS_HOME}/workspace/${JOB_NAME}/${BOT_SCRIPT_NAME} --jmeterproperty threads=${BOT_VIRTUAL_USERS} --jmeterproperty rampup=${BOT_RUMPUP} --jmeterproperty duration=${BOT_DURATION} --jmeterproperty startup=${BOT_STARTUPDELAY} --jmeterproperty aggregate=load-test.csv"
            }
        }

        	    
    }    
	
    post {
        always {
            echo 'One way or another, I have finished'
            deleteDir() /* clean up our workspace */
            cleanWs()
        }
        success {
            echo 'I succeeded!'
            archiveArtifacts artifacts: 'load-test.csv'
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
