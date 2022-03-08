#!/bin/sh

# Build github pages
chmod +x ./gradlew
./gradlew javadoc

# Move docs over to 
mv ./build/docs/javadoc ./pages/javadoc