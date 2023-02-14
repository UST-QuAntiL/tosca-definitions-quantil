#!/bin/bash

echo 'Port:'$Port
export AWS_ACCESS_KEY_ID=$AWS_ACCESS_KEY_ID
export AWS_SECRET_ACCESS_KEY=$AWS_SECRET_ACCESS_KEY
export AWS_REGION=$AWS_REGION

nohup python3 braket_app/adapter.py $Port > braket_app/output.log &