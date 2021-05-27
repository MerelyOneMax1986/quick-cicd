# syntax=docker/dockerfile:1
FROM python:3.9-slim-buster

WORKDIR /app

RUN pip3 install Flask flask-restful python-consul
RUN pip3 freeze > requirements.txt

COPY . .

CMD [ "python3", "-m" , "flask", "run", "--host=0.0.0.0"]
