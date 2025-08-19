package org.sokoban.main;

import org.sokoban.models.Board;
import org.sokoban.models.Node;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        // Create a new PuzzleState instance

        //inicializar arbol
        //crear Fr set

        Node<Board> root = new Node<>(new Board(), null, null );
        Set<Node<Board>> frontier = new TreeSet<>(new AStar<>());

        //{ 1, 2, 3, 6 } -> 1 -> 2
        //


    }
}

//algoritmos
class AStar<T> implements Comparator<T> {
    @Override
    public int compare(T o1, T o2) {
        // Implement the comparison logic for A* algorithm
        // This could be based on the cost of reaching the node, heuristic, etc.
        // For now, we will return 0 to avoid compilation error
        return 0;
    }
}