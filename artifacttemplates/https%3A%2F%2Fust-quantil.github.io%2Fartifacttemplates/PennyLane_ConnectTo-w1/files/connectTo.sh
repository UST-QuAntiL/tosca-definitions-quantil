#!/bin/bash

sudo mkdir -p pennylane_app

echo "DEVICE=${Device}" >> pennylane_app/.env
echo "PROVIDER=${PROVIDER}" >> pennylane_app/.env
echo "AER_BACKEND_NAME=${AER_BACKEND_NAME}" >> pennylane_app/.env
echo "IBMQ_TOKEN=${IBMQ_TOKEN}" >> pennylane_app/.env
echo "IBMQ_BACKEND_NAME=${IBMQ_BACKEND_NAME}" >> pennylane_app/.env
echo "IBMQ_HUB=${IBMQ_HUB}" >> pennylane_app/.env
echo "IBMQ_GROUP=${IBMQ_GROUP}" >> pennylane_app/.env
echo "IBMQ_PROJECT=${IBMQ_PROJECT}" >> pennylane_app/.env
