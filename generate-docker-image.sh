#!/bin/bash
export JAVA_HOME=/home/andrei/Applications/java/jdk-16.0.1
mvn -DskipTests clean package
if [ 0 -ne $? ]; then
  echo "build failed"
  exit 1
fi

DOCKER_IMAGE_NAME=andreicimpoca/csvrecord:1.0.0
docker rmi -f "${DOCKER_IMAGE_NAME}"
docker build -t "${DOCKER_IMAGE_NAME}" .
#docker push "${DOCKER_IMAGE_NAME}"