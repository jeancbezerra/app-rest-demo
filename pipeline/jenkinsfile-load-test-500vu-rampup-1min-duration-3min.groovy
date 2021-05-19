#!groovy

def BOT_REPOSITORY = "https://github.com/jeancbezerra/app-rest-demo.git"
def BOT_SCRIPT_NAME = "bot/bot-jmeter-4.jmx"
def BOT_VIRTUAL_USERS = 100
def BOT_RUMPUP = 60
def BOT_DURATION = 180
def BOT_STARTUPDELAY = 2

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
						url: "${BOT_REPOSITORY}"
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
        success{ 
            archiveArtifacts artifacts: 'load-test.csv'
			cleanWs()
        }    
	failure{
            //archiveArtifacts artifacts: 'load-test.csv'
	    cleanWs()
	}
			
    }
}
