#!/bin/bash

mvn clean package

mkdir -p outputs  # -p avoids error if it already exists

maps=("default" "small" "medium" "large" "medium3boxes" "mediumwithwalls" "mediumwithwalls" "medium5boxes")

for ((i=0; i<8; i++))
do
  mvn exec:java -Dexec.mainClass="org.sokoban.main.BFS" -Dexec.args="${maps[i]}" > "outputs/BFS_output_${maps[i]}.txt"

  mvn exec:java -Dexec.mainClass="org.sokoban.main.DFS" -Dexec.args="${maps[i]}" > "outputs/DFS_output_${maps[i]}.txt"
  mvn exec:java -Dexec.mainClass="org.sokoban.main.IDDFS" -Dexec.args="${maps[i]}" > "outputs/IDDFS_output_${maps[i]}.txt"

  mvn exec:java -Dexec.mainClass="org.sokoban.main.Greedy" -Dexec.args="h1 ${maps[i]}" > "outputs/Greedy_h1_output_${maps[i]}.txt"
  mvn exec:java -Dexec.mainClass="org.sokoban.main.Greedy" -Dexec.args="h2 ${maps[i]}" > "outputs/Greedy_h2_output_${maps[i]}.txt"
  mvn exec:java -Dexec.mainClass="org.sokoban.main.AStar" -Dexec.args="h1 ${maps[i]}" > "outputs/AStar_h1_output_${maps[i]}.txt"
  mvn exec:java -Dexec.mainClass="org.sokoban.main.AStar" -Dexec.args="h2 ${maps[i]}" > "outputs/AStar_h2_output_${maps[i]}.txt"
done
