#!/bin/sh

# Build gradle scripts
rm -r ./docs/javadoc
cp -r ../build/docs/javadoc ./docs/javadoc

npm install

python3 -m pip install -r requirements.txt
python3 update_pages.py