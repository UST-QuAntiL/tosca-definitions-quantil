print('first line of pyquil adapter.py')

from flask import request, redirect, url_for
from flask_api.exceptions import APIException, NotFound
from flask_api import FlaskAPI
from concurrent.futures import ThreadPoolExecutor
import uuid
from typing import Optional
from pyquil_app_algo import run_algo
import os
import sys
from dotenv import load_dotenv
from pyquil import get_qc

load_dotenv()

app = FlaskAPI(__name__)
pool = ThreadPoolExecutor(max_workers=4)
jobs = {}

job_callback = None


# PyQuil-App Main
def main():
    print('Starting PyQuil-App HTTP-Endpoint')
    start_endpoint(pyquil_job)


def pyquil_job(job, args):
    print('Invoking PyQuil App Algo')
    try:
        device = get_device()

        result = run_algo(device, args)
        print('result:', result)
        job.success(result)
    except Exception as e:
        print("Unexpected error invoking the PyQuil App Algo:", e)
        job.failed()


def get_shots_param(args):
    print('get_shots')
    if 'shots' in args:
        return int(args["shots"])
    else:
        return 1


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
        return {'id': self.id, 'status': self.status, 'result': self.result}


@app.after_request  # CORS
def after_request(response):
    print('after_request')
    header = response.headers
    header['Access-Control-Allow-Origin'] = '*'
    return response


@app.route('/jobs', methods=['POST'])
def create_job():
    print('create_job')
    try:
        job_uuid = str(uuid.uuid4())
        job = Job(job_uuid)
        jobs[job_uuid] = job
        args = request.args
        pool.submit(job_callback, job, args)
        result = job.to_dict()
        result['location'] = url_for('get_job', id=job.id)
        return result, 202
    except (KeyError, TypeError, ValueError):
        raise APIException(detail='Invalid value.')


@app.route('/jobs/<string:id>', methods=['GET'])
def get_job(id):
    print('get_job of %s' % id)
    if id in jobs:
        if jobs[id].status == Status.FINISHED:
            return redirect(url_for('get_result', id=id), code=303)
        else:
            return jobs[id].to_dict()
    else:
        raise NotFound(detail='Job not found.')


@app.route('/jobs/<string:id>/result', methods=['GET'])
def get_result(id):
    print('get_result of %s ' % id)
    if id in jobs:
        if jobs[id].result:
            return jobs[id].result
        else:
            return 'Job not finished', 418
    else:
        raise NotFound(detail='Job not found')


def start_endpoint(new_job_callback):
    print('start_endpoint')
    global job_callback
    job_callback = new_job_callback
    app.run(host='0.0.0.0', port=int(sys.argv[1]), debug=True)


# Create PyQuil-App Config

def get_config(key, default=None) -> Optional[str]:
    print('get_config(%s)' % key)
    tmp = os.getenv(key, default)
    if tmp == '':
        return default
    return tmp


def get_device():
    # Set up a connection to a quantum computer.
    # To make pyquil's get_qc() method use a non-standard host or port, environment variable QCS_SETTINGS_APPLICATIONS_PYQUIL_QVM_URL has to be set.
    print("get_device from environment variables...")
    qpu_name = os.getenv('QPU_NAME', '10q-qvm')
    qvm_hostname = os.getenv('QVM_HOSTNAME', 'localhost')
    qvm_port = os.getenv('QVM_PORT', '5000')
    print("QVM_HOSTNAME: %s" % qvm_hostname)
    print("QVM_PORT: %s" % qvm_port)
    print("QPU_NAME: %s" % qpu_name)
    qvm_connection = f"http://{qvm_hostname}:{qvm_port}"
    os.environ["QCS_SETTINGS_APPLICATIONS_PYQUIL_QVM_URL"] = qvm_connection
    print("Established connection to QVM")
    print("Environment variable QCS_SETTINGS_APPLICATIONS_PYQUIL_QVM_URL is set to %s" % qvm_connection)
    return get_qc(qpu_name)


def main2():
    # set environment variables
    print("Set environment variables")
    os.environ["QPU_NAME"] = "10q-qvm"
    os.environ["QVM_HOSTNAME"] = "localhost"
    os.environ["QVM_PORT"] = "5000"

    # create job
    job_uuid = str(uuid.uuid4())
    print("Create job with uuid %s" % job_uuid)
    job = Job(job_uuid)

    # program arguments
    args = {
        'clauses': '[[-1, -1, -1], [1, -1, 1], [1, 1, -1], [1, -1, -1], [-1, 1, 1]]',
        'shots': '100'
    }
    print("Program arguments: %s" % args)

    # run job
    pyquil_job(job, args)
    print("Job status: %s" % job.status)


if __name__ == '__main__':
    print('__main__')
    main()
