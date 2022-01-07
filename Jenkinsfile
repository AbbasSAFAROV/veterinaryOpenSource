pipeline {
    agent any
    options {
        skipStagesAfterUnstable()
    }
    stages {
        stage('Clone repository'){
            steps {
                checkout scm
            }
        }
        stage('Build'){
            steps {
                sh 'mvn -B DskipTests clean package'
            }
        }

    }
}


/**
pipeline {
    agent any

    stages {
        stage("build project") {
            steps {
               // git 'https://github.com/denizturkmen/SpringBootMysqlCrud.git'
                https://github.com/AbbasSAFAROV/veterinaryOpenSource.git
                echo "Java VERSION"
                sh 'java -version'
                echo "Maven VERSION"
                sh 'mvn -version'
                echo 'building project...'
                sh "mvn compile"
                sh "mvn package"
                //sh "mvn test"
                sh "mvn clean install"
            }
        }
    }
}
**/