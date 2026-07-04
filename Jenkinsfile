pipeline {
    agent any

    environment {
        IMAGE_NAME = "99227527/backend_devops"
    }

    stages {
        stage('Checkout') {
            steps {
                // Recupere le code depuis le depot GitHub configure dans le job Jenkins
                checkout scm
            }
        }

        stage('Build') {
            steps {
                dir('backend') {
                    sh 'mvn clean compile'
                }
            }
        }

        stage('Test') {
            steps {
                dir('backend') {
                    sh 'mvn test'
                }
            }
        }

        stage('Package') {
            steps {
                dir('backend') {
                    sh 'mvn package -DskipTests'
                }
            }
        }

        stage('Build image Docker') {
            steps {
                dir('backend') {
                    sh "docker build -t ${IMAGE_NAME}:${BUILD_NUMBER} ."
                }
            }
        }

        stage('Push image') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-creds',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS')]) {
                    sh '''
                        echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin
                        docker push ${IMAGE_NAME}:${BUILD_NUMBER}
                    '''
                }
            }
        }

        stage('Deploy (local)') {
            steps {
                // Deploiement local : on remplace le conteneur en cours par la nouvelle image
                // (adaptation locale du serveur de production distant du scenario du support)
                sh '''
                    docker stop backend_prod || true
                    docker rm backend_prod || true
                    docker run -d --name backend_prod -p 8081:8080 ${IMAGE_NAME}:${BUILD_NUMBER}
                '''
            }
        }
    }

    post {
        success {
            echo "Pipeline reussi - build #${env.BUILD_NUMBER} deploye sur http://localhost:8081/api/hello"
        }
        failure {
            echo "Le pipeline a echoue - voir la console Jenkins pour le detail"
        }
        always {
            cleanWs()
        }
    }
}
