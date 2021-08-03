#!/bin/bash
python3 -m celery --app qhana_plugin_runner.celery_worker:CELERY worker --loglevel INFO --autoscale $MinWorkers,$MaxWorkers --detach
