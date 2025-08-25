package org.sokoban.algorithms;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.sokoban.models.Board;

public class Greedy{
    private GreedySearch greedySearch;
    private String filename = "greedy_metrics.txt";
    //Metricas
    private long expanded = 0;

    public Greedy(Board board){
        this.greedySearch = new GreedySearch(board);
    }

    public List<Board> search(){
        long t0 = System.currentTimeMillis();
        List<Board> path = new ArrayList<>();

        while (greedySearch.hasNext()) {
            Board current = greedySearch.next();
            expanded++;

            if (current.isSolution()) {//found
                path = greedySearch.reconstructPath(current);

                long ms = System.currentTimeMillis() - t0;
                System.out.printf("Greedy encontró solución. Nodos expandidos=%d, Tiempo=%d ms%n", expanded, ms);

                try {
                    FileWriter writer = new FileWriter(filename, true);
                    writer.write(String.format("Greedy encontró solución. Nodos expandidos=%d, Tiempo=%d ms%n",
                            expanded, ms));
                    writer.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return path;
            }
        }//not found
        long ms = System.currentTimeMillis() - t0;
        System.out.printf("Greedy no encontró solución. Nodos expandidos=%d, Tiempo=%d ms%n", expanded, ms);
        
        try {
            FileWriter writer = new FileWriter(filename, true);
            writer.write(String.format("Greedy no encontró solución. Nodos expandidos=%d, Tiempo=%d ms%n",
                    expanded, ms));
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return path;
    }
}

class GreedySearch implements SearchAlgorithm<Board> {

    private final TreeSet<Board> frontier = new TreeSet<>(new GreedyComparator());
    private final Set<Board> explored = new HashSet<>();
    private final Map<Board, Board> parentMap = new java.util.HashMap<>();

    public GreedySearch(Board root) {
        frontier.add(root);
        parentMap.put(root, null);
    }

    @Override
    public boolean hasNext() {
        return !frontier.isEmpty();
    }

    @Override
    public Board next() {
        Board current = frontier.pollFirst();
        explored.add(current);

        for (Board child : current.getPossibleBoards()) {
        if (!explored.contains(child) && !frontier.contains(child)) {
            frontier.add(child);
            parentMap.put(child, current);
        }
    }

        return current;
    }

    public List<Board> reconstructPath(Board goal) {
        List<Board> path = new ArrayList<>();
        Board current = goal;
        while (current != null) {
            path.add(0, current);
            current = parentMap.get(current);
        }
        return path;
    }
}

class GreedyComparator implements Comparator<Board> {
    @Override
    public int compare(Board b1, Board b2) {
        int h1 = b1.heuristic();
        int h2 = b2.heuristic();

        if (h1 == h2) return System.identityHashCode(b1) - System.identityHashCode(b2);
        return Integer.compare(h1, h2);
    }
}