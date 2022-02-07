#!/usr/bin/env sh
chmod +x gradlew
./gradlew javadoc
rm -r ./docs
mv -u ./build/docs/javadoc ./docs