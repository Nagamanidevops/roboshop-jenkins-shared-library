def cal(){
    pipeline{
        
        
        agent{
            node{
                label "workstation"
        
        }
        parameters {
        string(name: 'INFRA_ENV', defaultValue: '', description: 'enter environment like dev or prod?')

        }
        
        stages{
            stage('terraform init'){
                steps{
                    sh "terraaform init -backend-config= env-${INFRA_ENV}/state.tfvars"
                }
            }
        }
        
        
    }
}