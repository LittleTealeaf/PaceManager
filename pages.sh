#!/bin/sh

# Move docs over to the pages subdirectory
rm -r ./pages/public/javadoc
cp -r ./build/docs/javadoc ./pages/public/javadoc