#!/bin/bash
apt-get update -qq
apt-get install git -qq
apt-get -qqy install unzip;
python3 -m pip install git+https://github.com/UST-QuAntiL/qhana-plugin-runner.git#egg=qhana_plugin_runner --quiet 
python3 -m pip install PyMySQL

IFS=';' read -ra NAMES <<< "$DAs";
for i in "${NAMES[@]}"; do
    IFS=',' read -ra entry <<< "$i";
  # find the zip file
    if [[ "${entry[1]}" == *.zip ]];
    then
        # unzip the application to
        sudo unzip $CSAR${entry[1]} -d plugins
    fi
done

echo "installed PluginRunner with plugins"