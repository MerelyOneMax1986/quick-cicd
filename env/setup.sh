# Run consul server
docker run \
    -d \
    -p 8500:8500 \
    -p 8600:8600/udp \
    --name=cserver \
    consul agent -server -ui -node=server-1 -bootstrap-expect=1 -client=0.0.0.0

# Run consul client
docker run \
   --name=cclient \
   consul agent -node=client-1 -join=172.17.0.2

# Run jenkins
cd env/jenkins 

# Build custon Jenkins installation
docker build . --tag jenkins-docker

docker rm -f jenkins && docker run \
    --name jenkins \
    -p 8080:8080 \
    -p 50000:50000 \
    -v /var/jenkins_home \
    --env JENKINS_ADMIN_ID=admin --env JENKINS_ADMIN_PASSWORD=password \
    --env JAVA_OPTS="-Djenkins.install.runSetupWizard=false" \
    -v /var/run/docker.sock:/var/run/docker.sock \
    jenkins-docker

