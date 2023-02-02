def compile() {

    if ( app_lang == "nodejs" ) {
        sh 'npm install'
    }
    
    if ( app_lang == "maven" ) {
        sh 'mvn package'
    }
}

def unittest(){
     if ( app_lang == "nodejs" ) {
         
         try {
    sh 'npm test'
       } catch(Exception e) {
          email("email to some one")
        }
        
      
    }
    
    if ( app_lang == "maven" ) {
        sh 'mvn test || true'
    }
       if ( app_lang == "python" ) {
        sh 'python3 -m unittest || true'
    }
    
   
    
}
def email(echo_note){
   println echo_note
    
}

