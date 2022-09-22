#!/bin/sh

echo "sdkman_auto_answer=true" >> $HOME/.sdkman/etc/config
sdk install java 17.0.4-tem
sdk install sbt
sbt "Test / compile"
