#!groovy

def BOT_REPOSITORY = "https://github.com/jeancbezerra/app-rest-demo.git"
def BOT_SCRIPT_NAME = "bot/bot-jmeter-4.jmx"
def BOT_VIRTUAL_USERS = 500
def BOT_RUMPUP = 60
def BOT_DURATION = 300
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
    }
    stages {
       stage("Application Repository"){
            steps {
			sh 'git config --global http.sslVerify false'
				timeout(time: 2, unit: "MINUTES") {
					git branch: "master",
						credentialsId: "gitlab-yaman",
						url: "${BOT_REPOSITORY}"
				}
            }           
        }
		
		stage("Build Application"){
            steps {
                sh "mvn clean package -DskipTests"
            }
        }

        stage("Bot Execution"){
            steps {
                sh "/opt/coe/JMETER/apache-jmeter-4.0/bin/jmeter.sh --nongui --testfile ${env.JENKINS_HOME}/workspace/${JOB_NAME}/${BOT_SCRIPT_NAME} --jmeterproperty threads=${BOT_VIRTUAL_USERS} --jmeterproperty rampup=${BOT_RUMPUP} --jmeterproperty duration=${BOT_DURATION} --jmeterproperty startup=${BOT_STARTUPDELAY} --jmeterproperty aggregate=load-test.csv"
            }
        }
	    
/*      
        stage("Bot Execution in Remote Servers"){
            steps {
                sh "/opt/coe/JMETER/apache-jmeter-4.0/bin/jmeter.sh -n -t ${env.JENKINS_HOME}/workspace/${JOB_NAME}/${BOT_SCRIPT_NAME} -R172.31.18.127:1099 -Gthreads=${BOT_VIRTUAL_USERS} -Grampup=${BOT_RUMPUP} -Gduration=${BOT_DURATION} -Gstartup=${BOT_STARTUPDELAY} -Gaggregate=load-test-remote.csv"

            }
        }
*/

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
