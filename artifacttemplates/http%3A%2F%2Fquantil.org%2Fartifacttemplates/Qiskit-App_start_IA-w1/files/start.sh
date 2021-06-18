#!/bin/bash

echo 'Port:'$Port
nohup python3 qiskit_app/adapter.py $Port > qiskit_app/output.log &
