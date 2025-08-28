package org.sokoban.main;

import org.sokoban.models.Board;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Greedy {

    private final PriorityQueue<Board> frontier = new PriorityQueue<>(new AdmisibleHeuristic());
    // private final PriorityQueue<Board> frontier = new PriorityQueue<>(new GreedyComparator());

    private final Set<Board> visited = new HashSet<>();
    private final Map<Board, Board> parent = new HashMap<>();
    private final Queue<Board> solution = new LinkedList<>();

    private long expanded = 0;

    private final Map<Board, Integer> frontierBefore = new HashMap<>();
    private final Map<Board, Integer> frontierAfter  = new HashMap<>();

    private final String outputFile = "src/main/resources/Greedy_second_solution.txt";

    public static void main(String[] args) {
        Greedy solver = new Greedy();

        long t0 = System.currentTimeMillis();
        Queue<Board> answer = solver.search();
        long elapsed = System.currentTimeMillis() - t0;
        boolean found = (answer != null);

        File out = new File(solver.outputFile);
        File parentDir = out.getParentFile();
        if (parentDir != null) parentDir.mkdirs();

        try (PrintWriter writer = new PrintWriter(new FileWriter(out))) {
            writer.printf("%s se encontró solución. ", found ? "Sí" : "No");
            writer.printf("Nodos Expandidos: %d. ", solver.expanded);
            writer.printf("Nodos Solucion: %d. ", solver.solution.size());
            writer.printf("Nodos Frontera (final): %d. ", solver.frontier.size());
            writer.printf("Tiempo de ejecución: %d ms.%n", elapsed);

            if (found) {
                writer.println("=== SOLUCIÓN ===");
                for (Board b : answer) {
                    writer.println(b.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Queue<Board> search() {
        Board root = new Board();
        System.out.println("Initial Board:\n" + root);

        frontier.clear();
        visited.clear();
        parent.clear();
        solution.clear();
        frontierBefore.clear();
        frontierAfter.clear();
        expanded = 0;

        frontier.add(root);
        visited.add(root);
        parent.put(root, null);

        long iterations = 0;
        long maxIters = 10_000_000L;

        while (!frontier.isEmpty() && iterations++ < maxIters) {
            int before = frontier.size();
            Board current = frontier.poll();
            expanded++;


            frontierBefore.put(current, before);

            if (current.isSolution()) {
                frontierAfter.put(current, frontier.size());
                buildSolution(current);
                System.out.printf("Greedy encontró solución. Nodos expandidos=%d, Tiempo acumulado (aprox.)=%d ms%n",
                        iterations, (System.currentTimeMillis()));
                return solution;
            }

            for (Board move : current.getPossibleBoards()) {
                if (!visited.contains(move) && !frontier.contains(move)) {
                    visited.add(move);
                    parent.put(move, current);
                    frontier.add(move);
                }
            }

            frontierAfter.put(current, frontier.size());
        }

        System.out.printf("Greedy no encontró solución. Nodos expandidos=%d%n", iterations);
        return null;
    }

    private void buildSolution(Board goal) {
        Deque<Board> path = new ArrayDeque<>();
        for (Board at = goal; at != null; at = parent.get(at)) {
            path.push(at);
        }
        solution.addAll(path);
    }
}

class GreedyComparator implements Comparator<Board> {
    @Override
    public int compare(Board b1, Board b2) {
        int h1 = b1.heuristic();
        int h2 = b2.heuristic();

        int cmp = Integer.compare(h1, h2);
        if (cmp != 0) return cmp;

        cmp = Integer.compare(b1.hashCode(), b2.hashCode());
        if (cmp != 0) return cmp;

        return Integer.compare(System.identityHashCode(b1), System.identityHashCode(b2));
    }
}

class AdmisibleHeuristic implements Comparator<Board> {
    @Override
    public int compare(Board b1, Board b2) {
        return Integer.compare(b1.admisibleHeuristic(), b2.admisibleHeuristic());
    }
}
