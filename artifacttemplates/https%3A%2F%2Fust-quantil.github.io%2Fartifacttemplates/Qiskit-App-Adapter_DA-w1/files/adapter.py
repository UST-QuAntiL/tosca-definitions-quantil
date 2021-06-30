from flask import request, redirect, url_for
from flask_api.exceptions import APIException, NotFound
from flask_api import FlaskAPI
from concurrent.futures import ThreadPoolExecutor
import uuid
from typing import Optional
from qiskit import Aer, IBMQ
from qiskit.aqua import QuantumInstance
from qiskit_app_algo import run_algo
import os
import sys
from dotenv import load_dotenv
load_dotenv()

app = FlaskAPI(__name__)
pool = ThreadPoolExecutor(max_workers=4)
jobs = {}

job_callback = None

# Qiskit-App Main

def main():
    print('Starting Qiskit-App HTTP-Endpoint')
    
    backend = get_backend()

    def qiskit_job(job, args):
        print('Invoking Qiskit App Algo')
        try:
            quantum_instance = QuantumInstance(backend, get_shots_param(args)) #Additional parameters could be added
            result = run_algo(quantum_instance, args)
            print('result:', result)
            job.success(result)
        except Exception as e: 
            print("Unexpected error invoking the Qiskit App Algo:", e)
            job.failed()
			
    start_endpoint(qiskit_job)
	
	
def get_shots_param(args):
    if 'shots' in args:
        return int(args["shots"])
    else:
        return 1
    

# Create Qiskit-App HTTP Endpoint

class Status():
    FINISHED = 'finished'
    RUNNING = 'running'

class Job():
    def __init__(self, id):
        self.id = id
        self.status = Status.RUNNING
        self.result = None

    def success(self, result):
        self.status = Status.FINISHED
        self.result = {'status': 'success', 'output': result}
    
    def failed(self):
        self.status = Status.FINISHED
        self.result = {'status': 'failed'}
    
    def to_dict(self):
        return {'id':self.id, 'status':self.status, 'result':self.result}
    
@app.after_request #CORS
def after_request(response):
    header = response.headers
    header['Access-Control-Allow-Origin'] = '*'
    return response
	
@app.route('/jobs', methods=['POST'])
def create_job():
    try:
        job_uuid = str(uuid.uuid4())
        job = Job(job_uuid)
        jobs[job_uuid] = job
        args = request.args
        pool.submit(job_callback, job, args)
        return job.to_dict(), 202
    except (KeyError, TypeError, ValueError):
        raise APIException(detail ='Invalid value.')

@app.route('/jobs/<string:id>', methods=['GET'])
def get_job(id):
    if id in jobs:
        if jobs[id].status == Status.FINISHED:
            return redirect(url_for('get_result', id=id), code=303)
        else:
            return jobs[id].to_dict()
    else:
        raise NotFound(detail = 'Job not found.')

@app.route('/jobs/<string:id>/result', methods=['GET'])
def get_result(id):
    if id in jobs:
        if jobs[id].result:
            return jobs[id].result
        else:
            return 'Job not finished', 418
    else:
        raise NotFound(detail = 'Job not found')
		
def start_endpoint(new_job_callback):
    global job_callback
    job_callback = new_job_callback
    app.run(host='0.0.0.0', port=int(sys.argv[1]), debug=True)
	
		
# Create Qiskit-App Config

def get_config(key, default=None) -> Optional[str]:
    tmp = os.getenv(key, default)
    if tmp == '':
        return default
    return tmp


def ibmq_provider():
    IBMQ.enable_account(get_config('IBMQ_TOKEN'))
    hub = get_config('IBMQ_HUB')
    group = get_config('IBMQ_GROUP')
    project = get_config('IBMQ_PROJECT')

    provider = IBMQ.get_provider(hub=hub, group=group, project=project)
    return provider


def ibmq_backend(provider):
    qc_name = get_config('IBMQ_BACKEND_NAME', 'ibmq_qasm_simulator')
    backend = provider.get_backend(qc_name)
    return backend


def aer_backend():
    backend_name = get_config('AER_BACKEND_NAME', 'qasm_simulator')
    backend = Aer.get_backend(backend_name)
    return backend

def get_backend():
    provider_name = get_config('PROVIDER', 'AER')
    if provider_name == 'IBMQ':
        provider = ibmq_provider()
        backend = ibmq_backend(provider)
    elif provider_name == 'AER':
        backend = aer_backend()
    else:
        raise "Provider not found: " + provider_name
    return backend

	
if __name__ == '__main__':
    main()
	