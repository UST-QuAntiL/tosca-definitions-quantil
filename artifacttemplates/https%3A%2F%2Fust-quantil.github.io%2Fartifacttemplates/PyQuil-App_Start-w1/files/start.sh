#!/bin/bash

echo 'Port:'$Port
cd /
which python3
which nohup
echo $PATH
echo 'Environment variables:'
printenv
python3 -c "print('Executing adapter now...')"
#python3 -c "from pyquil_app/adapter import main\nmain()\n"
nohup python3 pyquil_app/adapter.py $Port > /var/log/pyquil_app_starts.log 2>&1 &
echo 'started pyquil_app/adapter'
