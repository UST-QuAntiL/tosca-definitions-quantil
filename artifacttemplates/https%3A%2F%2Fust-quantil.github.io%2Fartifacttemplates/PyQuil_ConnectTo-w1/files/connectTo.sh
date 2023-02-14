#!/bin/bash

sudo mkdir -p pyquil_app

echo "QPU_NAME=${QPU_NAME}" >> pyquil_app/.env
echo "QVM_HOSTNAME"=${TARGET_ContainerIP} >> pyquil_app/.env
echo "QVM_PORT"=${QVM_PORT} >> pyquil_app/.env