
#!/usr/bin/env python
import sys
import subprocess
from os import environ
from pathlib import Path
from typing import ChainMap
from zipfile import ZipFile
from shutil import copyfile

def parseParameterFromArgV(argv, parameter):
    result = None
    for arg in argv:
        if str(parameter) + '=' in arg:
            result = arg[arg.index(str(parameter) + "=") + len(parameter)+1:]
    return result

def printOutput(dict):
    for key in dict:
        print(str(key) + '=' + str(dict[key]))

def install_requirements():
    subprocess.run(['apt-get', 'update', '-qq'])
    subprocess.run(['apt-get', 'install', 'git', '-qq'])

def install_deployment_artifacts():
    das = environ.get('DAs', '').split(';')
    for da in das:
        print('DA:', da)
        result = da.split(',')
        if result[1].endswith('.zip'):
            if result[0] == 'QHana-PluginRunner-Src-DA':
                install_plugin_runner(result[1])
            else:
                install_plugin(result[1])

def extract_zip(source, target):
    target.mkdir(parents=True, exist_ok=True)
    with source.open(mode='rb') as zip_file:
        ZipFile(zip_file).extractall(target)

def install_plugin_runner(path):
    zip_da = Path(path)
    if not zip_da.exists():
        return
    target = Path('src')
    extract_zip(zip_da, target) 
    subprocess.run(['python3', '-m', 'pip', 'install', 'PyMySQL', 'poetry', 'invoke', str(target.resolve())])
    copyfile(target/'tasks.py', '.')
    copyfile(target/'pyproject.toml', '.')
    copyfile(target/'poetry.lock', '.')   

def install_plugin(path):
    zip_da = Path(path)
    if not zip_da.exists():
        return
    extract_zip(zip_da, Path('plugins'))

def post_install():
    extra_env = {
        'FLASK_APP': 'qhana_plugin_runner',
        'FLASK_ENV':'production',
        'PLUGIN_FOLDERS': 'plugins:git-plugins'
    }

    subprocess.run(['python3', '-m', 'invoke', 'load-git-plugins'], env=ChainMap(environ, extra_env))
    subprocess.run(['python3', '-m', 'flask', 'install'], env=ChainMap(environ, extra_env))
        

def main(argv):
    print(argv)
    install_requirements()
    install_deployment_artifacts()
    post_install()



if __name__ == "__main__":
   main(sys.argv[1:])