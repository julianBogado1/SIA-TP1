#!/bin/bash

mvn clean package
mvn exec:java -Dexec.mainClass="org.sokoban.main.$1"