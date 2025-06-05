node {
      stage('SCM') {
        checkout scm
      }
      stage('SonarQube Analysis') {
        def mvn = tool 'NOM_DU_MAVEN';
        withSonarQubeEnv() {
          sh "${mvn}/bin/mvn clean verify sonar:sonar -Dsonar.projectKey=NOM_DU_PROJET_SONAR -DskipTests"
        }
      }
      stage("Quality gate") {
        waitForQualityGate abortPipeline: true
      }
      stage('Build'){
        def mvn = tool 'NOM_DU_MAVEN';
        sh "${mvn}/bin/mvn clean install -DskipTests"
      }

      stage('Test'){
        def mvn = tool 'NOM_DU_MAVEN';
        sh "${mvn}/bin/mvn test"
      }
      stage('Save artifacts'){
        archiveArtifacts artifacts: 'target/*.jar', onlyIfSuccessful: true
      }

}
