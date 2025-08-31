#!/bin/bash

mvn clean package

mkdir outputs

# for((i=0; i<$1; i++))
# do
  mvn exec:java -Dexec.mainClass="org.sokoban.main.BFS" -Dexec.args="default" > outputs/BFS_output_default.txt
  mvn exec:java -Dexec.mainClass="org.sokoban.main.BFS" -Dexec.args="small" > outputs/BFS_output_default.txt
  mvn exec:java -Dexec.mainClass="org.sokoban.main.BFS" -Dexec.args="medium" > outputs/BFS_output_default.txt
  mvn exec:java -Dexec.mainClass="org.sokoban.main.BFS" -Dexec.args="large" > outputs/BFS_output_default.txt
  mvn exec:java -Dexec.mainClass="org.sokoban.main.BFS" -Dexec.args="medium" > outputs/BFS_output_default.txt
  mvn exec:java -Dexec.mainClass="org.sokoban.main.BFS" -Dexec.args="default" > outputs/BFS_output_default.txt

  mvn exec:java -Dexec.mainClass="org.sokoban.main.DFS" -Dexec.args="small"> outputs/DFS_output"$i".txt
  mvn exec:java -Dexec.mainClass="org.sokoban.main.IDDFS" -Dexec.args="h1"> outputs/IDDFS_output"$i".txt

  mvn exec:java -Dexec.mainClass="org.sokoban.main.Greedy" -Dexec.args="h1" > outputs/Greedy_h1_output"$i".txt
  mvn exec:java -Dexec.mainClass="org.sokoban.main.Greedy" -Dexec.args="h2" > outputs/Greedy_h2_output"$i".txt
  mvn exec:java -Dexec.mainClass="org.sokoban.main.AStar" -Dexec.args="h1" > outputs/AStar_h1_output"$i".txt
  mvn exec:java -Dexec.mainClass="org.sokoban.main.AStar" -Dexec.args="h2" > outputs/AStar_h2_output"$i".txt
# done