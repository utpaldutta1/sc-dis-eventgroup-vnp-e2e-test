#!/bin/sh -l

set -eu

eval `ssh-agent -s`

mkdir /root/.ssh/
echo "${DEPLOY_KEY}" > /root/.ssh/id_rsa

chmod 400 /root/.ssh/id_rsa
ssh-add /root/.ssh/id_rsa

ssh-keyscan github.com > /root/.ssh/known_hosts 2>&1

cd /action

git clone git@github.com:JSainsburyPLC/ssdlc.git --branch "${TAG}" 2>&1

docker build -t ssdlc ssdlc/ssdlc
