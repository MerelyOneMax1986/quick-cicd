import jenkins.model.Jenkins;
import hudson.model.FreeStyleProject;
import hudson.tasks.Shell;

job = Jenkins.instance.createProject(FreeStyleProject, 'CI')
job.buildersList.add(new Shell('echo hello world'))
job.save()

job = Jenkins.instance.createProject(FreeStyleProject, 'CD')
job.buildersList.add(new Shell('echo hello world'))
job.save()
