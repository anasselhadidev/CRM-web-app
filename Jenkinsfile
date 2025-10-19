pipeline {
    agent any 
    tools {
        maven 'Maven-3.9'
    }
    stages {
        stage('1. Checkout Source Code') {
            steps {
                // Étape 1 : Cloner le repo
                echo 'Clonage du code source depuis GitHub...'
                git branch: 'develop', url: 'https://github.com/anasselhadidev/CRM-web-app.git'
            }
        }
        stage('2. Build Project') {
            steps {
                // Étape 2 : Compiler le projet
                echo 'Compilation du projet avec Maven...'
                // La commande 'mvn install' compile et exécute les tests
                sh 'mvn clean install'
            }
        }
        // ... et le reste du script
    }
    post {
        always {
            echo 'Pipeline terminé.'
        }
    }
}