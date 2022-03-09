#!/bin/sh

# Build github pages
chmod +x ./gradlew
./gradlew javadoc

# Move docs over to 
rm -r ./pages/javadoc
cp -r ./build/docs/javadoc ./pages/javadoc