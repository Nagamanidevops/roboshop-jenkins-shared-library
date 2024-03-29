def compile() {
  if (app_lang == "nodejs") {
    sh 'npm install'
  }

  if (app_lang == "maven") {
    sh "mvn package && cp target/${component}-1.0.jar ${component}.jar"
  }
  
   sh  " docker build -t 998626143474.dkr.ecr.us-east-1.amazonaws.com/${component}:${TAG_NAME} . "

}

def unittests() {

  if (app_lang == "nodejs") {
    // Developer is missing unit test cases in our project, He need to add them as best practice, We are skipping to proceed further
          sh 'npm test || true'

  }

  if (app_lang == "maven") {
    sh 'mvn test'
  }

  if (app_lang == "python") {
    sh 'python3 -m unittest'
  }
}

// def email(email_note) {
//   mail bcc: '', body: "Job Failed - ${JOB_BASE_NAME}\nJenkins URL - ${JOB_URL}", cc: '', from: 'nagamanichava18@gmail.com', replyTo: '', subject: "Jenkins Job Failed - ${JOB_BASE_NAME}", to: 'nagamanichava18t@gmail.com'
// }

def artifactPush() {
  
  sh "aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 998626143474.dkr.ecr.us-east-1.amazonaws.com"
  sh "docker push 998626143474.dkr.ecr.us-east-1.amazonaws.com/${component}:${TAG_NAME}"
  // sh "echo ${TAG_NAME} >VERSION"

  // if (app_lang == "nodejs") {
  //   sh "zip -r ${component}-${TAG_NAME}.zip node_modules server.js VERSION ${extraFiles}"
  // }

  // if (app_lang == "nginx" || app_lang == "python") {
  //   sh "zip -r ${component}-${TAG_NAME}.zip * -x Jenkinsfile ${extraFiles}"
  // }

  // if (app_lang == "maven") {
  //   sh "zip -r ${component}-${TAG_NAME}.zip * ${component}.jar VERSION ${extraFiles}"
  // }

  // NEXUS_PASS = sh ( script: 'aws ssm get-parameters --region us-east-1 --names nexus.pass  --with-decryption --query Parameters[0].Value | sed \'s/"//g\'', returnStdout: true).trim()
  // NEXUS_USER = sh ( script: 'aws ssm get-parameters --region us-east-1 --names nexus.user  --with-decryption --query Parameters[0].Value | sed \'s/"//g\'', returnStdout: true).trim()
  // wrap([$class: 'MaskPasswordsBuildWrapper', varPasswordPairs: [[password: "${NEXUS_PASS}", var: 'SECRET']]]) {
  //   sh "curl -v -u ${NEXUS_USER}:${NEXUS_PASS} --upload-file ${component}-${TAG_NAME}.zip http://172.31.15.120:8081/repository/${component}/${component}-${TAG_NAME}.zip"
  // }

}



