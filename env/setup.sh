docker network create env-network

# Run consul server
docker rm -f cserver && docker run --net env-network \
    -d \
    -p 8500:8500 \
    -p 8600:8600/udp \
    --name=cserver \
    consul agent -server -ui -node=server-1 -bootstrap-expect=1 -client=0.0.0.0

# Run consul client
docker rm -f cclient && docker run --net env-network \
   -d \
   --name=cclient \
   consul agent -node=client-1 -join=$(docker exec -it cserver hostname -i | tr -d '\r\n')

# Build custon Jenkins installation
docker build --tag jenkins-docker ./jenkins/

# Run custom image with 2 preinstalled jobs and plugins
docker rm -f jenkins && docker run --net env-network \
    -d \
    -p 8080:8080 \
    -p 50000:50000 \
    -v /var/jenkins_home \
    --env JENKINS_ADMIN_ID=admin --env JENKINS_ADMIN_PASSWORD=password \
    --env JAVA_OPTS="-Djenkins.install.runSetupWizard=false" \
    -v /var/run/docker.sock:/var/run/docker.sock \
    --name jenkins \
    jenkins-docker
