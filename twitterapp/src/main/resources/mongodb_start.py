import subprocess
from pathlib import Path
from multiprocessing import Process, Manager
import time
from sys import argv

script, pathToMongoDbBin, pathToLogAndDataFolders = argv


def mongoDB_run(i, return_list):
    with Path('output' + str(i) + '.txt').expanduser().open('wb', 0) as file:
        process = subprocess.Popen(pathToMongoDbBin + r'mongod.exe --port '
                                    + str(27016 + i) + r' --logpath ' + pathToLogAndDataFolders + 
                                    r'log\\n' + str(i) + r'\\mongod.log --dbpath ' + 
                                    pathToLogAndDataFolders + r'data\\n' 
                                    + str(i) + r' --replSet rs0',
                                    stdout=file, shell=True)
    return_list.append(process.pid)
    

if __name__ == '__main__':
    manager = Manager()
    return_list = manager.list()
    processes = []
    for i in range(3):
        processes.append(Process(target=mongoDB_run, args=(i + 1, return_list), daemon=True))
    for p in processes:
        p.start()
    time.sleep(3)
    print('PIDs of started processes with MongoDB:', return_list)
    file = open("PIDs.txt", 'w')
    file.write(return_list.__str__())
    file.close()