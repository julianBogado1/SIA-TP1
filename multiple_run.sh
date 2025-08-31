#!/bin/bash

mvn clean package

mvn exec:java -Dexec.mainClass="org.sokoban.main.BFS" > BFS_output.txt
mvn exec:java -Dexec.mainClass="org.sokoban.main.DFS" > DFS_output.txt
mvn exec:java -Dexec.mainClass="org.sokoban.main.IDDFS" > IDDFS_output.txt

mvn exec:java -Dexec.mainClass="org.sokoban.main.Greedy" -Dexec.args="h1" > Greedy_h1_output.txt
mvn exec:java -Dexec.mainClass="org.sokoban.main.Greedy" -Dexec.args="h2" > Greedy_h2_output.txt
mvn exec:java -Dexec.mainClass="org.sokoban.main.AStar" -Dexec.args="h1" > AStar_h1_output.txt
mvn exec:java -Dexec.mainClass="org.sokoban.main.AStar" -Dexec.args="h2" > AStar_h2_output.txt