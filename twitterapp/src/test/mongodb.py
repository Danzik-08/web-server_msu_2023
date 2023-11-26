import subprocess
from pathlib import Path
from multiprocessing import Process, Manager
import psutil
from sys import argv

script, pathToMongoDbBin, pathToLogAndDataFolders = argv

def kill(proc_pid):
    process = psutil.Process(proc_pid)
    for proc in process.children(recursive=True):
        proc.kill()
    process.kill()

def mongoDB_run(i, return_dict):
    with Path('output' + str(i) + '.txt').expanduser().open('wb', 0) as file:
        process = subprocess.Popen(pathToMongoDbBin + r'mongod.exe --port '
                                    + str(27016 + i) + r' --logpath ' + pathToLogAndDataFolders + 
                                    r'log\\n' + str(i) + r'\\mongod.log --dbpath ' + 
                                    pathToLogAndDataFolders + r'data\\n' 
                                    + str(i) + r' --replSet rs0',
                                    stdout=file, shell=True)
    return_dict[i] = process.pid
    

if __name__ == '__main__':
    manager = Manager()
    return_dict = manager.dict()
    p1 = Process(target=mongoDB_run, args=(1, return_dict), daemon=True)
    p2 = Process(target=mongoDB_run, args=(2, return_dict), daemon=True)
    p3 = Process(target=mongoDB_run, args=(3, return_dict), daemon=True)
    p1.start()
    p2.start()
    p3.start()
    stop = input("Input any data for stoppping of MongoDBs: ")
    print(return_dict)
    for i in return_dict:
        kill(return_dict[i])