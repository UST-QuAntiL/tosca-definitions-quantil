#!/bin/bash

sudo apt-get update -qq
sudo DEBIAN_FRONTEND=noninteractive apt-get install -y python3-pip -qq
​
pip3 install --upgrade pip
pip3 install --upgrade setuptools
​
pip3 install pennylane
pip3 install pennylane-sf pennylane-qiskit pennylane-cirq pennylane-forest pennylane-qsharp