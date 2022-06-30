
#!/usr/bin/env python
import sys
import subprocess
from os import environ
from pathlib import Path
from typing import ChainMap
from zipfile import ZipFile
from shutil import copyfile
from shlex import join as cmd_join
from subprocess import DEVNULL

def argv_to_dict(argv):
    return dict(arg.split('=', maxsplit=1) for arg in argv if '=' in arg)

def parseParameterFromArgV(argv, parameter):
    result = None
    for arg in argv:
        if str(parameter) + '=' in arg:
            result = arg[arg.index(str(parameter) + "=") + len(parameter)+1:]
    return result

def printOutput(dict):
    for key in dict:
        print(str(key) + '=' + str(dict[key]))

def install_requirements(environ):
    cmd = ['apt-get', 'update', '-qq']
    print(cmd_join(cmd))
    subprocess.run(cmd)
    cmd = ['apt-get', 'install', 'git', '-qq']
    print(cmd_join(cmd))
    subprocess.run(cmd)


def install_deployment_artifacts(environ):
    das = (da.strip() for da in environ.get('DAs', '').split(';') if da.strip())
    plugin_runner_found = False
    for da in das:
        print('DA:', da)
        result = da.split(',', maxsplit=1)
        da_path = Path('/') / environ.get('CSAR', '').strip('/') / result[1].lstrip('/')
        if da_path.suffix == '.zip':
            if result[0] == 'QHAna-PluginRunner_DA':
                plugin_runner_found = True
                print('install plugin runner DA', *result)
                install_plugin_runner(da_path)
            else:
                print('install plugin DA', *result)
                install_plugin(da_path)
    if not plugin_runner_found:
        print('ERROR: No plugin runner DA!!!')

def extract_zip(source, target):
    target.mkdir(parents=True, exist_ok=True)
    with source.open(mode='rb') as zip_file:
        ZipFile(zip_file).extractall(target)

def install_plugin_runner(zip_da):
    if not zip_da.exists():
        print('ERROR: DA not found at ', zip_da)
        return
    target = Path('src')
    extract_zip(zip_da, target) 
    cmd = ['python3', '-m', 'pip', 'install', 'PyMySQL', 'poetry', 'invoke', 'mysql-connector-python', 'SQLAlchemy==1.4.31', str(target.resolve())]
    print(cmd_join(cmd))
    subprocess.run(cmd)
    for f in ['tasks.py', 'pyproject.toml', 'poetry.lock']:
        copyfile(target/f, Path('.')/f)


def install_plugin(zip_da):
    if not zip_da.exists():
        print('ERROR: DA not found at ', zip_da)
        return
    extract_zip(zip_da, Path('plugins'))

def post_install(environ):
    extra_env = {
        'FLASK_APP': 'qhana_plugin_runner',
        'FLASK_ENV':'production',
        'PLUGIN_FOLDERS': 'plugins:git-plugins'
    }

    cmd = ['python3', '-m', 'invoke', 'load-git-plugins']
    print(cmd_join(cmd), 'env:', extra_env)
    subprocess.run(cmd, env=ChainMap(extra_env, environ))
    cmd = ['python3', '-m', 'flask', 'install']
    print(cmd_join(cmd), 'env:', extra_env)
    subprocess.run(cmd, env=ChainMap(extra_env, environ))
   
        

def main(argv):
    print('pwd', Path('.').resolve())
    print(argv)
    env = ChainMap(argv_to_dict(argv), environ)
    install_requirements(env)
    install_deployment_artifacts(env)
    post_install(env)



if __name__ == "__main__":
   main(sys.argv[1:])