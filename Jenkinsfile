pipeline {
    agent any

    parameters {
        choice(
            name: 'ENV_TYPE',
            choices: ['JENKINS_CHROME', 'JENKINS_FIREFOX'],
            description: 'Choose browser environment'
        )
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
                sh "mvn clean install -Denv.type=${ENV_TYPE}"
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
                       results: [[path: 'allure-results']]
            }
        }

    }

    post {
        always {
            archiveArtifacts artifacts: '**/target/**/*.log', allowEmptyArchive: true
        }
    }
}
