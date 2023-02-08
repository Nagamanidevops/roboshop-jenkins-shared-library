def compile() {
    

    if ( app_lang == "nodejs" ) {
        sh 'npm install'
        sh 'env'
    }
    
    if ( app_lang == "maven" ) {
        sh 'mvn package'
    }
}

def unittest(){
     if ( app_lang == "nodejs" ) {
         
            sh 'npm test || true'
         }
    
    if ( app_lang == "maven" ) {
        sh 'mvn test || true'
    }
       if ( app_lang == "python" ) {
        sh 'python3 -m unittest || true'
    }
    
   
    
}
def email(echo_note){
mail bcc: '', body: "Job Failed - ${JOB_BASE_NAME}\nJenkins URL - ${JOB_URL}", cc: '', from: 'nagamanichava18@gmail.com', replyTo: '', subject: "Jenkins Job Failed - ${JOB_BASE_NAME}", to: 'nagamanichava18@gmail.com'

}

def artifactpush()
{
    sh "echo ${TAG_NAME} >version"
    
    if ( app_lang == "nodejs" ) {
      sh "zip -r ${component}-${TAG_NAME}.zip node_module server.js"
    }
    
    NEXUS_PASS = sh ( script: 'aws ssm get-parameters --region us-east-1 --names nexus.pass  --with-decryption --query Parameters[0].Value | sed \'s/"//g\'', returnStdout: true).trim()
        NEXUS_USER = sh ( script: 'aws ssm get-parameters --region us-east-1 --names nexus.user  --with-decryption --query Parameters[0].Value | sed \'s/"//g\'', returnStdout: true).trim()
        wrap([$class: 'MaskPasswordsBuildWrapper', varPasswordPairs: [[password: "${NEXUS_PASS}", var: 'SECRET']]]) {
           sh "curl -v -u ${NEXUS_USER}:${NEXUS_PASS} --upload-file ${component}-${TAG_NAME}.zip http://172.31.2.190:8081/repository/${component}/${component}-${TAG_NAME}.zip"

        }
    
}

