package org.models;

import java.util.*;

public class Puzzle {
    Node<PuzzleState> root = new Node<>(PuzzleState.generateRandomPuzzle(), null, null );
    Set<Node<PuzzleState>> frontier = new TreeSet<>(new AStar<>());

}

//los diferentes algoritmos se implementan como un comparator para el frontier set
class AStar<T> implements Comparator<T>{
    @Override
    public int compare(T o1, T o2) {
        // Implement the comparison logic for A* algorithm
        // This could be based on the cost of reaching the node, heuristic, etc.
        // For now, we will return 0 to avoid compilation error
        return 0;
    }
}