#!/usr/bin/env python
from __future__ import print_function
import os
import subprocess
import sys
from Config import config

root = config.get('path', 'root')
defects4j_checkout_path = config.get('path', 'checkout')
output_path = config.get('path', 'output')

def get_project_features(project, bug_id, mode):
    jar = os.path.join(root, "target", "patchclustering-0.1-SNAPSHOT-jar-with-dependencies.jar")
    cmd = """java -jar %s --bugId %s --buggySourceDirectory %s --diff %s -m %s""" % \
          (jar,
           bug_id,
           os.path.join(defects4j_checkout_path, project, bug_id, "buggy-version"),
           os.path.join(defects4j_checkout_path, project, bug_id, "path.diff"),
           mode)
    if output_path:
        cmd += """ -o %s""" % output_path
    try:
        return subprocess.check_output(cmd, shell=True).strip()
    except subprocess.CalledProcessError as e:
        return "command '{}' return with error (code {}): {}".format(e.cmd, e.returncode, e.output)

def eprint(*args, **kwargs):
    print(*args, file=sys.stderr, **kwargs)

mode = sys.argv[1]
for project in sorted(os.listdir(defects4j_checkout_path)):
    for bug in sorted(os.listdir(os.path.join(defects4j_checkout_path, project))):
        eprint("%s" % bug)
        print(get_project_features(project, bug, mode))