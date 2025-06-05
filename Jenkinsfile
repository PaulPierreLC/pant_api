node {
      stage('SCM') {
        checkout scm
      }
      stage('SonarQube Analysis') {
        def mvn = tool 'maven';
        withSonarQubeEnv() {
          sh "${mvn}/bin/mvn clean verify sonar:sonar -Dsonar.projectKey=pantoplate-sonar -DskipTests"
        }
      }
      stage("Quality gate") {
        waitForQualityGate abortPipeline: true
      }
      stage('Build'){
        def mvn = tool 'maven';
        sh "${mvn}/bin/mvn clean install -DskipTests"
      }

      stage('Test'){
        def mvn = tool 'maven';
        sh "${mvn}/bin/mvn test"
      }
      stage('Save artifacts'){
        archiveArtifacts artifacts: 'target/*.jar', onlyIfSuccessful: true
      }

}
