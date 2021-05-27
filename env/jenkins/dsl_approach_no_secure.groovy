import jenkins.model.Jenkins;
import hudson.model.FreeStyleProject;
import hudson.tasks.Shell;
import javaposse.jobdsl.plugin.ExecuteDslScripts

job = Jenkins.instance.createProject(FreeStyleProject, 'job-name2')

job.buildersList.add(new ExecuteDslScripts('''
job('CI') {
  scm {
    git ('https://github.com/MerelyOneMax1986/quick-cicd.git')
  }
  steps {
     shell(readFileFromWorkspace('build.sh'))
  }
}

job('CD') {
  scm {
    git ('https://github.com/MerelyOneMax1986/quick-cicd.git')
  }
  steps {
     shell(readFileFromWorkspace('build.sh'))
  }
}
'''
))

job.save()

/*
build = job.scheduleBuild2(5, new hudson.model.Cause.UserIdCause())

build.get() // Block until the build finishes

generatedJobs = build.getAction(javaposse.jobdsl.plugin.actions.GeneratedJobsBuildAction).getItems()

// FIXME skip .scheduleBuild2() on Folder jobs
generatedJobs.each { j -> j.scheduleBuild2(5, new hudson.model.Cause.UserIdCause()) }
*/