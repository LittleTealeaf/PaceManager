import yaml
import sys, os

# List of branch names to add dependabot to
branches = ["master","1.0.0","bug-fixes","1.1.0","1.1.0.1"]

__location__ = os.path.realpath(
    os.path.join(os.getcwd(), os.path.dirname(__file__)))

with open(os.path.join(__location__,'dependabot.yml')) as file:
    data = yaml.load(file,Loader=yaml.FullLoader)
    print(data)
    # Create a new updates object
    updates = []
    for b in branches:
        update = {}
        update['package-ecosystem'] = "maven"
        update['directory'] = "/"
        update['target-branch'] = b
        update['schedule'] = {'interval':"daily"}
        updates.append(update)
    data['updates'] = updates

    with open(os.path.join(__location__,'dependabot.yml'),"w") as f:
        yaml.dump(data,f)
