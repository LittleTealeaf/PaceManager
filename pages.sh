#!/bin/sh

# Move docs over to the pages subdirectory
rm -r ./pages/javadoc
cp -r ./build/docs/javadoc ./pages/javadoc