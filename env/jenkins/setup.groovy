// imports
import hudson.plugins.git.*
import hudson.plugins.git.extensions.*
import hudson.plugins.git.extensions.impl.*
import jenkins.model.Jenkins

def createJob (jobName, gitRepo, branch) {
    def jenkinsFile = "devops/${jobName}/Jenkinsfile"
    // parameters
    def jobParameters = [
    name:          jobName.toUpperCase(),
    description:   'This is ' + jobName,
    repository:    gitRepo,
    branch:        branch
    //credentialId:  'jenkins-key'
    ]

    // define repo configuration
    def branchConfig                =   [new BranchSpec(jobParameters.branch)]
    def userConfig                  =   [new UserRemoteConfig(jobParameters.repository, null, null, jobParameters.credentialId)]
    def cleanBeforeCheckOutConfig   =   new CleanBeforeCheckout()
    def sparseCheckoutPathConfig    =   new SparseCheckoutPaths([new SparseCheckoutPath(jenkinsFile)])
    def cloneConfig                 =   new CloneOption(true, true, null, 3)
    def extensionsConfig            =   [cleanBeforeCheckOutConfig,sparseCheckoutPathConfig,cloneConfig]
    def scm                         =   new GitSCM(userConfig, branchConfig, false, [], null, null, extensionsConfig)

    // define SCM flow
    def flowDefinition = new org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition(scm, jenkinsFile)

    // set lightweight checkout
    flowDefinition.setLightweight(false)

    // get Jenkins instance
    Jenkins jenkins = Jenkins.getInstance()

    // create the job
    def job = new org.jenkinsci.plugins.workflow.job.WorkflowJob(jenkins,jobParameters.name)

    // define job type
    job.definition = flowDefinition

    // set job description
    job.setDescription(jobParameters.description)

    // save to disk
    jenkins.save()
    jenkins.reload()
}

def gitRepo = 'https://github.com/MerelyOneMax1986/quick-cicd.git'

createJob('ci', gitRepo, 'main')
createJob('cd', gitRepo, 'main')