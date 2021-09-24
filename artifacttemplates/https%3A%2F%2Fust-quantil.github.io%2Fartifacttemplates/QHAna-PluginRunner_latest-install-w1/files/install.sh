#!/bin/bash
apt-get update -qq
apt-get install git -qq
python3 -m pip install git+https://github.com/UST-QuAntiL/qhana-plugin-runner.git#egg=qhana_plugin_runner --quiet 
python3 -m pip install PyMySQL