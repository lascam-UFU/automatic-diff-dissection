#!/usr/bin/env python

import ConfigParser
import os

config = ConfigParser.ConfigParser()

config.add_section('path')
config.set('path', 'root', os.path.join(os.path.dirname(__file__), '..', '..', '..'))
config.set('path', 'output', os.path.join(config.get('path', 'root'), "features"))
config.set('path', 'defects4j', os.path.expanduser("~/defects4j"))
config.set('path', 'checkout', os.path.expanduser("~/projects"))
config.set('path', 'fix_checkout', os.path.expanduser("~/projects_fix"))



path_config_file = os.path.join(config.get('path', 'root'), 'scripts', 'defects4j', 'config.cfg')

if os.path.isfile(path_config_file):
    with open(path_config_file, 'r') as configfile:
        config.readfp(configfile)
        for option in config.options('path'):
            value = config.get('path', option)
            if '~' in value:
                config.set('path', option, os.path.expanduser(value))
else:
    with open(path_config_file, 'wb') as configfile:
        config.write(configfile)
