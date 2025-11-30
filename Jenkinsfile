pipeline {
    agent any

    tools {
        // Uses Jenkins-managed tools
        maven 'maven3911'
        // Git install usually named 'Default' or 'git'; safest to just declare Git
        git 'Default'
    }

    parameters {
        choice(
            name: 'ENV_TYPE',
            choices: ['JENKINS_CHROME', 'JENKINS_FIREFOX'],
            description: 'Choose browser environment'
        )
    }

    environment {
        ALLURE_HOME = tool('allure2531')   // Jenkins Allure installation
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/aosipenko/QA-LIGHT-OCT-2025.git'
            }
        }

        stage('Maven Build') {
                    steps {
                        // Capture failure but continue pipeline
                        catchError(buildResult: 'FAILURE', stageResult: 'FAILURE') {
                            sh "mvn clean install -Denv.type=${params.ENV_TYPE}"
                        }
                    }
                }

        stage('Cucumber Report') {
            steps {
                cucumber fileIncludePattern: '**/*Cucumber.json'
            }
        }

        stage('Allure Report') {
            steps {
                allure includeProperties: false,
                       jdk: '',
                       results: [[path: 'allure-results']],
                       // explicitly use the configured allure tool
                       commandline: "${env.ALLURE_HOME}/bin/allure"
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: '**/target/**/*.log', allowEmptyArchive: true
        }
    }
}
