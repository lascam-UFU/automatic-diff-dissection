#!/usr/bin/env python
from __future__ import print_function
import os
import subprocess
import tempfile
import json
import collections
import os.path
from Config import config
import sys

def eprint(*args, **kwargs):
    print(*args, file=sys.stderr, **kwargs)

root = config.get('path', 'root')
oldVersionPath = "/mnt/secondary/commons-math"
newVersionPath = "/mnt/secondary/commons-math-bis"

def create_diff(commit, newCommit, sourcePath, output):
    cmd = """cd %s; git diff %s %s -- %s > %s""" % \
          (oldVersionPath,
           commit,
           newCommit,
           sourcePath,
           output)
    subprocess.call(cmd, shell=True)

def get_project_features(project, commit_id, diff):
    jar = os.path.join(root, "target", "patchclustering-0.1-SNAPSHOT-jar-with-dependencies.jar")
    cmd = """java -jar %s -p %s -i %s -s %s -x %s -d %s""" % \
          (jar,
           project,
           commit_id[0:8],
           os.path.join(oldVersionPath),
           os.path.join(newVersionPath),
           diff)
    return subprocess.check_output(cmd, shell=True).strip()

def get_commits():
    cmd = """cd %s;git rev-list --reverse --no-merges origin/master""" % oldVersionPath
    return subprocess.check_output(cmd, shell=True).strip().split("\n")

def go_to_commit(path, commit):
    cmd = """cd %s;git checkout %s --quiet""" % (path, commit)
    subprocess.call(cmd, shell=True)

def getSource(root):
    path = os.path.join("src", "main", "java")
    if os.path.exists(os.path.join(root, path)):
        return path
    path = os.path.join("src", "java")
    if os.path.exists(os.path.join(root, path)):
        return path
    path = os.path.join("src")
    if os.path.exists(os.path.join(root, path)):
        return path
    return None

commits = get_commits()
nb_commit = len(commits)
for idx, commit in enumerate(commits):
    if idx + 1 == nb_commit:
        break
    eprint("%s (%d/%d, %.2f%%)" % (commit, idx, nb_commit, idx*100/float(nb_commit)))
    next_commit = commits[idx + 1]
    go_to_commit(oldVersionPath, commit)
    go_to_commit(newVersionPath, next_commit)
    old_source = getSource(oldVersionPath)
    new_source = getSource(newVersionPath)
    if old_source is None or new_source is None:
        continue
    if old_source is None:
        old_source = new_source
    tmp = tempfile.NamedTemporaryFile()
    try:
        create_diff(commit, next_commit, old_source, tmp.name)
        print(get_project_features("Math", commit, tmp.name))
    finally:
        tmp.close()