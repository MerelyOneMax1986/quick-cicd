# Prerequisites

```
# Compare version of Docker locally with version you wanna install inside Jenkins

# Let's us consider Debian as your local machine OS

# First of all, let's go though manual Docker installaion for Debian at the official site https://docs.docker.com/engine/install/debian/
docker run -it jenkins/jenkins bash

# Follow the instructions

# Finally, check what we have on Jenkins image
apt-cache madison docker-ce

# Now, let's pick the right one on your local machine

# Let's go though manual Docker installaion for Debian at the official site https://docs.docker.com/engine/install/debian/

# Let it be 20.10.6 as Docker version both on Jenkins and the local machine

# Up environment from stage 2 
```

# Up environment via one-click

```
sh setup.sh

```
