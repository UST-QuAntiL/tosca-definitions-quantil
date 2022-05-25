#!/usr/bin/env python
from pathlib import Path
from os import environ

port = environ.get("Port", "5000")
app_module = environ.get("AppModule", "qhana_plugin_runner:create_app()")
min_workers = environ.get("MinWorkers", "1")
max_workers = environ.get("MaxWorkers", "2")

script_content = f'''#!/bin/bash
export FLASK_APP=qhana_plugin_runner
export FLASK_ENV=production
export PLUGIN_FOLDERS=plugins

python3 -m flask create-db

python3 -m gunicorn --daemon --workers 4 --bind 0.0.0.0:{port} "{app_module}"
echo "started gunicorn demon on {port} with application {app_module}"

python3 -m celery --app qhana_plugin_runner.celery_worker:CELERY worker --loglevel INFO --autoscale {min_workers},{max_workers} --detach
echo "started celery with {min_workers}, {max_workers}"
echo "started QHAna application!"
sleep 10
'''

with Path('start_script.sh').open(mode='wt') as start_script:
    start_script.write(script_content)
