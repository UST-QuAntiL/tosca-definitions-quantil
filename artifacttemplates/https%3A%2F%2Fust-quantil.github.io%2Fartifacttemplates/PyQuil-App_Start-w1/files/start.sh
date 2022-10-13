#!/bin/bash

echo 'Port:'$Port
nohup python3 pyquil_app/adapter.py $Port &
echo 'started pyquil_app/adapter'
