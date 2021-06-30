#!/bin/bash

echo 'Port:'$Port
nohup python3 pennylane_app/adapter.py $Port > pennylane_app/output.log &
