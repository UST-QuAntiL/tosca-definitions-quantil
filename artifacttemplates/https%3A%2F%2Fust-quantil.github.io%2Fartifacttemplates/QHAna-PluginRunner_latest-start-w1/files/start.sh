#!/bin/bash

python3 -m gunicorn --daemon --workers 4 --bind 0.0.0.0:$Port "$AppModule"
echo "started gunicorn demon on $Port with application $AppModule"

python3 -m celery --app qhana_plugin_runner.celery_worker:CELERY worker --loglevel INFO --autoscale $MinWorkers,$MaxWorkers --detach
echo "started celery with $MinWorkers, $MaxWorkers"
echo "started QHAna application!"