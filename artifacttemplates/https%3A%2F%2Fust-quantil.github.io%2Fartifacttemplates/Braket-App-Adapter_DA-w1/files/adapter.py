from flask import request, redirect, url_for
from flask_api.exceptions import APIException, NotFound
from flask_api import FlaskAPI
from concurrent.futures import ThreadPoolExecutor
import uuid
from typing import Optional
import boto3
from braket_app_algo import run_algo
import os
import sys
from dotenv import load_dotenv
load_dotenv()

app = FlaskAPI(__name__)
pool = ThreadPoolExecutor(max_workers=4)
jobs = {}

job_callback = None

# Braket-App Main

def main():
    print('Starting Braket-App HTTP-Endpoint')
    
    def braket_job(job, args):
        print('Invoking Braket App Algo')
        try:
            result = run_algo(args)
            print('result:', result)
            job.success(result)
        except Exception as e: 
            print("Unexpected error invoking the Braket App Algo:", e)
            job.failed()

    start_endpoint(braket_job)


def get_shots_param(args):
    if 'shots' in args:
        return int(args["shots"])
    else:
        return 1
    

# Create Braket-App HTTP Endpoint

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


# Create Braket-App Config

def get_config(key, default=None) -> Optional[str]:
    tmp = os.getenv(key, default)
    if tmp == '':
        return default
    return tmp


if __name__ == '__main__':
    main()
