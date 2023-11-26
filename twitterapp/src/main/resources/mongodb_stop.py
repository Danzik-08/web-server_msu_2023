import json
import psutil

def kill(proc_pid):
    process = psutil.Process(proc_pid)
    for proc in process.children(recursive=True):
        proc.kill()
    process.kill()

if __name__ == '__main__':
    with open('PIDs.txt') as f:
        data = f.read()
    list = json.loads(data)
    print('\nPIDs of stopped processes:', list)
    for i in list:
        kill(i)