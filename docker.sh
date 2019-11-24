#!/bin/sh

# WildFly-Image nur bauen, wenn es nicht existiert
#if [[ "$(docker inspect wildfly18jdk13x 2> /dev/null)" == "[]" ]]; then
if [[ "$(docker inspect wildfly18jdk13 2> /dev/null)" = "[]" ]]; then
  docker build --tag=wildfly18jdk13 -f src/main/docker/Dockerfile.wildfly . 
fi
docker build --tag=jsf-special -f src/main/docker/Dockerfile .
# evtl run mit '-d' ?
docker run -p 8080:8080 jsf-special
