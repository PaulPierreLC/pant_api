node {
      stage('SCM') {
        checkout scm
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
