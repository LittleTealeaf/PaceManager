#!/usr/bin/env sh
cd ..
chmod +x gradlew
./gradlew javadoc
mv ./build/docs/javadoc ./docs