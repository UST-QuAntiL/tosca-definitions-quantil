#!/bin/bash

sudo apt-get update -qq

#sudo DEBIAN_FRONTEND=noninteractive apt-get install -y python3-pip -qq

#pip3 install --upgrade pip

#pip install pyquil

sudo DEBIAN_FRONTEND=noninteractive apt-get install -y python3-pip wget -qq
wget https://downloads.rigetti.com/qcs-sdk/forest-sdk-2.23.0-linux-deb.tar.bz2
tar xjf forest-sdk-2.23.0-linux-deb.tar.bz2
cd forest-sdk-2.23.0-linux-deb
sudo ./forest-sdk-2.23.0-linux-deb.run

# fix wrong versions referenced by qvm
wget http://archive.ubuntu.com/ubuntu/pool/main/libf/libffi/libffi6_3.2.1-8_amd64.deb
yes | dpkg -i libffi6_3.2.1-8_amd64.deb