
    def job = "${env.JOB_NAME}".tokenize('/')
    print job
    def appName = job[0]
    print appName
    def tag = "v25"


    pipeline{
    agent any
    tools {
        maven "M3"
    }
    environment {
        DOCKER_REPO = 'ranjith413'
        IMAGE_NAME = "${appName}"
        IMAGE_TAG = "${tag}-${env.BUILD_ID}"
    }
    stages {
        stage("checkout") {
            steps {
                sh 'ls -ltr'
                echo 'hello devops!'
            }

        }

        stage("maven-build"){
            agent {
                docker { 
                    image 'maven:3.9-eclipse-temurin-17-alpine'
                    args '-v $HOME/.m2:/root/.m2'
                    reuseNode true
                    }
            }
            steps {
                sh 'mvn clean package'
            }
        }

    stage("Image-build"){
        steps{
            sh 'docker build -t ${DOCKER_REPO}/${IMAGE_NAME}:${IMAGE_TAG} .'
        }
    }
    stage("Publish Image"){
        steps {
            script{
                withCredentials([usernamePassword(credentialsId: 'docker-creds', passwordVariable: 'DOCKER_PASSWD', usernameVariable: 'DOCKER_UNAME')]) {
                    sh '''
                    echo ${DOCKER_PASSWD} | docker login -u ${DOCKER_UNAME} --password-stdin
                    docker push ${DOCKER_REPO}/${IMAGE_NAME}:${IMAGE_TAG}
                    docker logout
                    '''
                }
            }
        }
    }

    }
    post {
        always {
            cleanWs()
        }
    }
}
