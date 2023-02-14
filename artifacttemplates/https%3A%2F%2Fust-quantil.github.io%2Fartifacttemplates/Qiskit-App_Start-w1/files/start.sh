#!/bin/bash

echo 'Port:'$Port
nohup python3 qiskit_app/adapter.py $Port &
echo 'started qiskit_app/adapter'
