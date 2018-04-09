#!/usr/bin/python
# -*- coding: utf-8 -*-
import os
import sys
import subprocess

reload(sys)
sys.setdefaultencoding('utf8')

root_path = os.getcwd()


def init_manager():
    manager_path = "git_manager"
    abs_manager_path = os.path.abspath(os.path.join(root_path, manager_path))
    if not os.path.exists(abs_manager_path):
        subprocess.call(["git submodule add --force https://github.com/MrDreamCacher/GitManager.git %s" % manager_path],
                        stdout=subprocess.PIPE, stderr=subprocess.PIPE, shell=True, cwd=root_path)
    output = subprocess.Popen(["git submodule | grep '%s'" % manager_path], stdout=subprocess.PIPE,
                              stderr=subprocess.PIPE, shell=True, cwd=root_path)
    oc = output.communicate()[0]
    if oc.strip().startswith('-'):
        subprocess.call(['rm', '-rf', manager_path], stdout=subprocess.PIPE, stderr=subprocess.PIPE, cwd=root_path)
        subprocess.call(['git', 'submodule', 'update', '--init', manager_path], stdout=subprocess.PIPE,
                        stderr=subprocess.PIPE, cwd=root_path)
    subprocess.call(['git', 'checkout', 'master'], stdout=subprocess.PIPE, stderr=subprocess.PIPE,
                    cwd=os.path.join(root_path, manager_path))
    subprocess.call(["git pull -f origin master:master"], cwd=os.path.join(root_path, manager_path),
                    stdout=subprocess.PIPE, stderr=subprocess.PIPE, shell=True)
init_manager()

error_color = "\033[31m"
normal_color = "\033[0m"

if __name__ == '__main__':
    path = os.path.join(root_path, 'git_manager', 'git_manager')
    if not os.path.exists(path):
        print(error_color)
        sys.stderr.write("找不到 git_manager 脚本 %s\n" % path)
        print(normal_color)
        exit(1)
    argv = sys.argv[1:]
    argv.insert(0, path)
    exit(subprocess.call(argv))
