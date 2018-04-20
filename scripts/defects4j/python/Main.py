#!/usr/bin/env python
from __future__ import print_function
import os
import subprocess
import tempfile
import json
import collections
import sys
from Config import config

root = config.get('path', 'root')
defects4j_path = config.get('path', 'defects4j')
defects4j_bin_path = os.path.join(defects4j_path, 'framework', 'bin')
defects4j_projects_path = os.path.join(defects4j_path, 'framework', 'projects')
defects4j_checkout_path = config.get('path', 'checkout')
defects4j_fix_checkout_path = config.get('path', 'fix_checkout')
output_path = config.get('path', 'output')

def parse_project_info(project, value):
    path = os.path.join(root, 'scripts', 'defects4j', 'python', 'data', project.lower() + '.json' )
    with open(path) as data_file:
        project_info = json.load(data_file)
        for line in value.splitlines():
            split = line.split(':', 1)
            if len(split) == 2:
                key = split[0].strip().replace(' ', '_').lower()
                value = split[1].strip()
                project_info[key] = value
        project_info['src'] = collections.OrderedDict(sorted(project_info['src'].items(), key=lambda t: int(t[0])))
    return project_info


def get_project_info(project):
    cmd = """export PATH="%s:$PATH"
defects4j info -p %s
""" % (defects4j_bin_path, project)
    output = subprocess.check_output(cmd, shell=True)
    return parse_project_info(project, output)

def create_diff(info, project, bug_id, output):
    path = os.path.join(project.lower(), "%s_%d" % (project.lower(), bug_id), getSource(info, bug_id))
    cmd = """git diff -U0 %s %s > %s""" % \
          (os.path.join(defects4j_checkout_path, path),
           os.path.join(defects4j_fix_checkout_path, path),
           output)
    subprocess.call(cmd, shell=True)

def get_project_features(info, project, bug_id, diff, mode):
    path = os.path.join(project.lower(), "%s_%d" % (project.lower(), bug_id), getSource(info, bug_id))
    jar = os.path.join(root, "target", "patchclustering-0.1-SNAPSHOT-jar-with-dependencies.jar")
    complete_bug_id = project + "_" + str(bug_id)
    cmd = """java -jar %s --bugId %s --buggySourceDirectory %s --fixedSourceDirectory %s --diff %s -m %s""" % \
          (jar,
           complete_bug_id,
           os.path.join(defects4j_checkout_path, path),
           os.path.join(defects4j_fix_checkout_path, path),
           diff,
           mode)
    if output_path:
        cmd += """ -o %s""" % output_path
    return subprocess.check_output(cmd, shell=True).strip()

def getSource(info, id):
    for index, src in info['src'].iteritems():
        if id <= int(index):
            return src['srcjava']
    return ""

def eprint(*args, **kwargs):
    print(*args, file=sys.stderr, **kwargs)

mode = sys.argv[1]
commit_id = {}
for project in sorted(os.listdir(defects4j_projects_path)):
    if project == 'lib':
        continue
    if os.path.isfile(os.path.join(defects4j_projects_path, project)):
        continue
    info = get_project_info(project)
    commit_id[project] = {}
    for bug_id in xrange(1, int(info['number_of_bugs']) + 1):
        eprint("%s %d/%s, %.2f%%" % (project, bug_id, info['number_of_bugs'], bug_id*100/float(info['number_of_bugs'])))
        tmp = tempfile.NamedTemporaryFile()
        try:
            create_diff(info, project, bug_id, tmp.name)
            print(get_project_features(info, project, bug_id, tmp.name, mode))
        finally:
            tmp.close()