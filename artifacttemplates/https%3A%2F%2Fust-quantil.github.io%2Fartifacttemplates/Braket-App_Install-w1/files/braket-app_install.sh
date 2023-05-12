#!/bin/bash

sudo mkdir -p braket_app

IFS=';' read -ra NAMES <<< "$DAs";
for i in "${NAMES[@]}"; do
  IFS=',' read -ra OT_PATH <<< "$i";
  echo "Key: ${OT_PATH[0]}"
  echo "Value: ${OT_PATH[1]}"

  if [[ "${OT_PATH[1]}" == *requirements.txt ]]; then
    echo "Installing dependencies..."
    sudo pip install --no-cache-dir -r $CSAR${OT_PATH[1]}
  fi
  if [[ "${OT_PATH[1]}" == *.py ]]; then
    echo "Copying files..."
    sudo cp $CSAR${OT_PATH[1]} braket_app/$(basename ${OT_PATH[1]})
  fi
done


