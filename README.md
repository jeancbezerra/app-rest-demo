# app-rest-demo

## Get unique card from uuid

http://localhost:8080/app-rest-demo/api/v1/card/22739c36-c621-11ea-87d0-0242ac130003

## Simulate Not Found

http://localhost:8080/app-rest-demo/api/v1/card/nf/227

## All cards from user

http://localhost:8080/app-rest-demo/api/v1/card/user/a618522c-c624-11ea-87d0-0242ac130003

## Register new card

http://localhost:8080/app-rest-demo/api/v1/card/new

Payload example:
```json
{    
    "nickname": "Cielo",
    "number": "1234123412341237",
    "flag": "Elo",
    "localPhoneSAC": "08005912117",
    "internationPhoneSAC": "16367227111",
    "emailSAC": "meajuda@cielo.com.br",
    "bankCard": true,
    "bankName": "NuBank",
	"userUUID": "a618522c-c624-11ea-87d0-0242ac130003"
}
```

## Jenkins Pipeline
```groovy
pipeline {
	agent any
		tools{
			maven "MAVEN_3_6_3"
			jdk "OPENJDK_JDK_11"
		}
    		options {
			timeout(time: 15, unit: 'MINUTES')
			buildDiscarder(logRotator(numToKeepStr: '10'))
		}
	stages{
	    
	     stage("Clone Repository from GitHub"){
	        steps {
				bat 'git config --global http.sslVerify false'
				timeout(time: 5, unit: "MINUTES") {
					git branch: "master",
						credentialsId: "github-up-jeancbezerra-with-token",
						url: "https://github.com/jeancbezerra/app-rest-demo.git"
				}
			}
	    }
	    
	    stage("Build with Maven"){
	        steps {
	            timeout(time: 5, unit: "MINUTES") {
				    bat 'mvn clean package'
	            }
			}
	    }
	    
	    stage("Scanner with SonarQube"){
	        steps {
				timeout(time: 5, unit: "MINUTES") {
                    bat "mvn verify org.sonarsource.scanner.maven:sonar-maven-plugin:sonar"
				}
			}
	    }
	    
	    
	}	
	
}
```