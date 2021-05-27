# How to use

## Setup env
Please, go to "env" folder and follow Readme.md


## CI/CD
Please, open Jenkins URL on localhost:8080, just for your convinient there is no need to authentificate, however, you can setup your first admin user yourself while issuing Docker command regarding Jenkins

1. Run CI job
2. Run CD job

## Register your Docker app in Consul
docker exec cclient /bin/sh -c "echo '{\"service\": {\"name\": \"app\", \"tags\": [\"go\"], \"port\": 5000}}' >> /consul/config/app.json"
docker exec cclient consul reload
dig @127.0.0.1 -p 8600 app.service.consul

## Try your Docker app API

curl @127.0.0.1 -p 8600 app.service.consul/get_value/first-key <br />
5 <br /> 
curl @127.0.0.1 -p 8600 app.service.consul/set_value?key=first-key&value=first-value <br />
Success <br />
