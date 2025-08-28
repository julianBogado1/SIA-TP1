package org.sokoban.main;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

import org.sokoban.models.Board;

public class Greedy {
    // private final TreeSet<Board> frontier = new TreeSet<>(new GreedyComparator());
    private final TreeSet<Board> frontier = new TreeSet<>(new AdmisibleHeuristic());
    private final Set<Board> visited = new HashSet<>();
    private final Map<Board, Board> parent = new HashMap<>();
    private final Queue<Board> solution = new LinkedList<>();
    private final String outputFile = "src/main/resources/Greedy_solution.txt";

    public static void main(String[] args) {
        Greedy solver = new Greedy();
        long t0 = System.currentTimeMillis();
        Queue<Board> answer = solver.search();
        long elapsed = System.currentTimeMillis() - t0;
        boolean found = answer != null;
        long expanded = solver.visited.size();

        try (PrintWriter writer = new PrintWriter(new FileWriter(solver.outputFile))) {
            writer.printf("%s se encontró solución. ", found ? "Sí" : "No");
            writer.printf("Nodos expandidos: %d. ", expanded);
            writer.printf("Frontier: %d. ", solver.visited.size());
            writer.printf("Tiempo de ejecución: %d ms. ", elapsed);
            writer.println();

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

    private boolean nextStep(Board current) {
        if (current.isSolution()) {
            buildSolution(current);
            return true;
        }

        for (Board move : current.getPossibleBoards()) {
            if (!visited.contains(move) && !frontier.contains(move)) {
                visited.add(move);
                parent.put(move, current);
                frontier.add(move);
            }
        }

        return false;
    }

    private Queue<Board> search() {
        Board root = new Board();
        System.out.println("Initial Board:\n" + root);
        frontier.add(root);
        visited.add(root);
        parent.put(root, null);
        long iterations = 0;
        long t0 = System.currentTimeMillis();

        while (!frontier.isEmpty() && iterations++<10000000) {
            Board current = frontier.pollFirst();

            if (nextStep(current)) {
                long ms = System.currentTimeMillis() - t0;
                System.out.printf("Greedy encontró solución. Nodos expandidos=%d, Tiempo=%d ms%n", iterations, ms);
                return solution;
            }
        }

        long ms = System.currentTimeMillis() - t0;
        System.out.printf("Greedy no encontró solución. Nodos expandidos=%d, Tiempo=%d ms%n", iterations, ms);
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

        if (h1 == h2) return System.identityHashCode(b1) - System.identityHashCode(b2);
        return Integer.compare(h1, h2);
    }
}
class AdmisibleHeuristic implements Comparator<Board> {
    @Override
    public int compare(Board b1, Board b2) {
        return Integer.compare(b1.admisibleHeuristic(), b2.admisibleHeuristic());
    }
} 