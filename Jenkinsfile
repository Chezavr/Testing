pipeline {
    agent any

    parameters {
        string(name: 'tomcat_stage', defaultValue: '/Users/chezavr/Downloads/apache-tomcat-8.5.34-staging', description: 'Staging Server')
        string(name: 'tomcat_prod', defaultValue: '/Users/chezavr/Downloads/apache-tomcat-8.5.34-prod', description: 'Production Server')
        string(name: 'relative_target_path', defaultValue: '**/target', description: 'Relative path to target folder')
    }

    triggers {
        pollSCM('* * * * *')
    }

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
            post {
                success {
                    echo 'Now Archiving...'
                    archiveArtifacts artifacts: "${params.relative_target_path}/*.jar"
                }
            }
        }
    }
}