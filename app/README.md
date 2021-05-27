# Local development
```
# Download and setup pyenv and then follow instructions
curl https://pyenv.run | bash

# Initialize pyenv in shell
exec "$SHELL" # Or just restart your terminal

# We choosed latest stable version of Python, let's install it
pyenv install -v 3.9.5

# Create pyenv for the project
pyenv virtualenv 3.9.5 consul-app

# Activate the project in pyenv
pyenv local consul-app

# Install dependencies 
pip3 install Flask flask-restful python-consul
pip3 freeze > requirements.txt
touch app.py
```

# Build
```
docker build --tag python-docker .
docker run --publish 5000:5000 python-docker
```