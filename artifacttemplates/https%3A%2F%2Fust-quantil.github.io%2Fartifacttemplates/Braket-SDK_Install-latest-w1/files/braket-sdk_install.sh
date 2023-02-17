#!/bin/bash

sudo apt-get update -qq

sudo DEBIAN_FRONTEND=noninteractive apt-get install -y python3-pip -qq

pip3 install --upgrade pip

echo 'Braket version: '$Version

if [ -z $Version ]; then
	pip install amazon-braket-sdk
	else
	pip install amazon-braket-sdk==$Version
fi
