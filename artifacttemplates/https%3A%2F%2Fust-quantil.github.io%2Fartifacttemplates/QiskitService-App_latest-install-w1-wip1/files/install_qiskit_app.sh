#!/bin/bash

sudo mkdir -p ~/qiskit_service_app

sudo DEBIAN_FRONTEND=noninteractive apt-get install -y python3-pip -qq
	
pip3 install --upgrade pip

echo 'Port:'$Port
echo "python3 -m flask db upgrade" > ~/qiskit_service_app/startup.sh
echo "gunicorn qiskit-service:app -b 0.0.0.0:5013 -w 4 --timeout 500 --log-level info" >> ~/qiskit_service_app/startup.sh

csarRoot=$(find ~/.. -maxdepth 1 -path "*.csar");

echo "CSAR Root: "$csarRoot

IFS=';' read -ra NAMES <<< "$DAs";
for i in "${NAMES[@]}"; do
	echo "KeyValue-Pair: "
    	echo $i
	IFS=',' read -ra OT_PATH <<< "$i";    
	echo "Key: "
    	echo ${OT_PATH[0]}
    	echo "Value: "
    	echo ${OT_PATH[1]}
	if [[ "${OT_PATH[1]}" == *.tar.gz ]]; then
		echo "Extracting Python Service..."
		sudo tar -xf $csarRoot${OT_PATH[1]} -C ~/qiskit_service_app
		echo "Installing dependencies..."
		pip install --no-cache-dir -r ~/qiskit_service_app/requirements.txt
	fi
	if [[ "${OT_PATH[1]}" == *requirements.txt ]]; then
		echo "Installing dependencies..."
		sudo pip install --no-cache-dir -r $CSAR${OT_PATH[1]}
	fi
	if [[ "${OT_PATH[1]}" == *.py ]]; then
		echo "Copying files..."
		sudo cp $CSAR${OT_PATH[1]} qiskit_service_app/$(basename ${OT_PATH[1]})
	fi
done


