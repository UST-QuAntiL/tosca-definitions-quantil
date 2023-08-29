#!/bin/bash

export FLASK_APP=qiskit-service.py
export FLASK_ENV=development
export FLASK_DEBUG=0
echo 'Redis Url:'$REDIS_URL
echo 'Database Url:'$DATABASE_URL
chmod +x ~/qiskit_service_app/startup.sh
(cd ~/qiskit_service_app/ && nohup /bin/bash ~/qiskit_service_app/startup.sh &)
sleep 5
exit 1