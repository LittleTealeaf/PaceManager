import os
import shutil

path_soruce = os.path.join('..','build','docs','javadoc')
path_target = os.path.join('.','public','javadoc')


if os.path.exists(path_soruce):
    if os.path.exists(path_target):
        shutil.rmtree(path_target)
    shutil.copytree(path_soruce,path_target)