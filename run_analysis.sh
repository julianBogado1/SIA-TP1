#!/bin/bash
set -e  # Salir inmediatamente si un comando falla

mvn clean package

mkdir -p outputs

EXPERIMENT_TYPES=("size" "boxes" "walls")

for index in "${!EXPERIMENT_TYPES[@]}"
do
    EXP_TYPE=${EXPERIMENT_TYPES[$index]}

    echo "Ejecutando experimento: $EXP_TYPE con mapas en $MAP_DIR"

    for((i=0; i<$1; i++))
    do
    mvn exec:java -Dexec.mainClass="org.sokoban.main.BFS" > outputs/BFS_output"$i".txt
    mvn exec:java -Dexec.mainClass="org.sokoban.main.DFS" > outputs/DFS_output"$i".txt
    mvn exec:java -Dexec.mainClass="org.sokoban.main.IDDFS" > outputs/IDDFS_output"$i".txt

    mvn exec:java -Dexec.mainClass="org.sokoban.main.Greedy" -Dexec.args="h1" > outputs/Greedy_h1_output"$i".txt
    mvn exec:java -Dexec.mainClass="org.sokoban.main.Greedy" -Dexec.args="h2" > outputs/Greedy_h2_output"$i".txt
    mvn exec:java -Dexec.mainClass="org.sokoban.main.AStar" -Dexec.args="h1" > outputs/AStar_h1_output"$i".txt
    mvn exec:java -Dexec.mainClass="org.sokoban.main.AStar" -Dexec.args="h2" > outputs/AStar_h2_output"$i".txt
    done

    # Activamos el entorno virtual de python
    source venv/bin/activate

    python3 cost_analysis.py "$EXP_TYPE"

    # Desactivamos el entorno virtual
    deactivate

done