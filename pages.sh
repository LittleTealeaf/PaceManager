#!/bin/sh

# Build github pages
chmod +x ./gradlew
./gradlew javadoc

# Move docs over to the pages subdirectory
rm -r ./pages/javadoc
cp -r ./build/docs/javadoc ./pages/javadoc

cd pages
./build.sh
cd ..
