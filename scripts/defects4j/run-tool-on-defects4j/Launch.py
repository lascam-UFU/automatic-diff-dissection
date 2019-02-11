#!/usr/bin/env python
from __future__ import print_function
import os
import subprocess
import sys
import requests
import time
import signal
from Config import config

root = config.get('path', 'root')
defects4j_checkout_path = config.get('path', 'checkout')
output_path = config.get('path', 'output')


def start_detector_service():
    jar = os.path.join(root, "target", "automatic-diff-dissection-1.0-jar-with-dependencies.jar")
    cmd = "java -cp %s add.main.Server" % (jar)
    # , stdout=subprocess.PIPE
    pro = subprocess.Popen(cmd, shell=True, preexec_fn=os.setsid)
    time.sleep(2)
    return pro


def stop_detector_service(pro):
    os.killpg(os.getpgid(pro.pid), signal.SIGTERM)
    time.sleep(5)

def get_project_features(project, bug_id):
    eprint("%s" % bug_id)
    data = {
        "bugId": bug_id,
        "buggySourceDirectory": os.path.join(defects4j_checkout_path, project, bug_id, "buggy-version"),
        "diffPath": os.path.join(defects4j_checkout_path, project, bug_id, "path.diff")
    }
    response = requests.post("http://localhost:9888", json=data, allow_redirects=False)
    with open(os.path.join(output_path, "%s_all.json" % bug_id), "w+") as fd:
        fd.write(response.content)

def eprint(*args, **kwargs):
    print(*args, file=sys.stderr, **kwargs)



tasks = []
for project in sorted(os.listdir(defects4j_checkout_path)):
    for bug in sorted(os.listdir(os.path.join(defects4j_checkout_path, project))):
        tasks += [(project, bug)]

serviceId = None
try:
    serviceId = start_detector_service()
    if not os.path.exists(output_path):
        os.makedirs(output_path)
    for (project, bug) in tasks:
        get_project_features(project, bug)
finally:
    stop_detector_service(serviceId)