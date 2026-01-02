pipeline {
    agent none

    stages {
        stage('Run unit tests') {
            agent {
                kubernetes{
                    label 'docker agent'
                    yaml """
apiVersion: v1
kind: Pod
metadata:
  name: unit-test-agent
  namespace: todok8s
spec:
  containers:
  - name: java-test
    image: maven:3.9-eclipse-temurin-21
    command:
    - cat 
    tty: true 
""" //cat and tty to keep the container running if not it will just run a test command then close
                }
            }
            steps {
                checkout scm
                container('java-test') {
                    script {
                            sh '''
        echo "=== Debug ==="
        ls -la src/test/resources/        
        echo "=== Tests ==="
        mvn clean test
    '''
                    }
                }
            }
        }

        stage ('Build image') {
            agent {
                kubernetes {
                    label 'docker-agent'
                    yaml """
apiVersion: v1
kind: Pod
metadata:
  name: docker-building
  namespace: todok8s
spec:
  containers:
  - name: docker
    image: docker:latest
    command:
    - cat
    tty: true
    volumeMounts:
    - name: docker-sock
      mountPath: /var/run/docker.sock
    - name: docker-hub-secret
      mountPath: /tmp/.docker
  volumes:
    - name: docker-sock
      hostPath:
        path: /var/run/docker.sock
    - name: docker-hub-secret
      secret: 
        secretName: docker-hub-secret
        items:
        - key: .dockerconfigjson
          path: config.json
"""
                }
            }
            steps {
                checkout scm
                container('docker') {
                    script {
                        sh '''
                        mkdir -p /root/.docker
                        cp /tmp/.docker/config.json /root/.docker/config.json
                        docker build -t kyul1234/todo4k8s:latest .
                        docker push kyul1234/todo4k8s:latest
                        '''
                    }
                }
            }
         
                }

    stage ('deploy to docker hub') {
        agent {
            kubernetes {
                label 'docker agent'
                yaml """
apiVersion: v1
kind: Pod
metadata:
  name: deploy-to-dockerhub
  namespace: todok8s
spec:
  serviceAccountName: todo
  containers:
  - name: kubectl
    image: alpine/k8s:1.32.11
    command:
    - cat
    tty: true
    """
            }
        }
        steps {
            checkout scm
            container ('kubectl') {
                script {
                    sh '''
                    cd manifests/
                    kubectl apply -f mysql-secret.yml
                    kubectl apply -f mysql-config.yml
                    kubectl apply -f mysql-pvc.yml
                    kubectl apply -f mysql.yml
                    kubectl apply -f backend.yml
                    '''
                }
            }
        }
    }

            }
        }

