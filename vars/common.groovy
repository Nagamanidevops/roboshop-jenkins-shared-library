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

