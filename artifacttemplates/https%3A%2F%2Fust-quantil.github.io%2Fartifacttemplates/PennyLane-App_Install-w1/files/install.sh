#!/bin/bash

sudo mkdir -p pennylane_app

#find csar root
csarRoot=$(find ~ -maxdepth 1 -path "*.csar");

IFS=';' read -ra NAMES <<< "$DAs";
for i in "${NAMES[@]}"; do
	echo "KeyValue-Pair: "
    	echo $i
	IFS=',' read -ra OT_PATH <<< "$i";    
	echo "Key: "
    	echo ${OT_PATH[0]}
    	echo "Value: "
    	echo ${OT_PATH[1]}
	if [[ "${OT_PATH[1]}" == *requirements.txt ]]; then
		echo "Installing dependencies..."
		sudo pip install --no-cache-dir -r $csarRoot${OT_PATH[1]}
	fi
	if [[ "${OT_PATH[1]}" == *.py ]]; then
		echo "Copying files..."
		sudo cp $csarRoot${OT_PATH[1]} pennylane_app/$(basename ${OT_PATH[1]})
	fi
done


