package org.sokoban.main;

import java.util.Comparator;

import org.sokoban.algorithms.GreedySearch;
import org.sokoban.algorithms.SearchAlgorithm;
import org.sokoban.models.Board;

public class Main {
    public static void main(String[] args) {
        Board root = new Board();
        
        SearchAlgorithm<Board> algorithm = new GreedySearch(root);

        while (algorithm.hasNext()) {
            Board current = algorithm.next();

            if (current.isSolution()) {
                System.out.println("¡Solución encontrada!");
                System.out.println(current);
                return;
            }
        }

        System.out.println("No se encontró solución.");
    }
}

//algoritmos
class BFS<T> implements Comparator<T> {
    @Override
    public int compare(T o1, T o2) {
        // Implement the comparison logic for A* algorithm
        // This could be based on the cost of reaching the node, heuristic, etc.
        // For now, we will return 0 to avoid compilation error
        return 0;
    }
}