
# Job generator
pip3 install jenkins-job-builder

mkdir -p ~/.config/jenkins_jobs
cat <<EOF > ~/.config/jenkins_jobs/jenkins_jobs.ini
[job_builder]
ignore_cache=True
keep_descriptions=False
include_path=.:scripts:~/git/
recursive=False
exclude=.*:manual:./development
allow_duplicates=False
update=all

[jenkins]
user=admin
password=password
url=http://localhost:8080
query_plugins_info=False
##### This is deprecated, use job_builder section instead
#ignore_cache=True

EOF
