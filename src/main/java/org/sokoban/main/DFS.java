package org.sokoban.main;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.sokoban.models.Board;

public class DFS {
    private final Set<Board> visited = new HashSet<>();
    private final Map<Board, Board> parent = new HashMap<>();
    private final Queue<Board> solution = new LinkedList<>();
    private final String outputFile = "src/main/resources/DFS_solution.txt";
    private long expanded = 0;
    private int maxDepth = 0;

    public static void main(String[] args) {
        DFS solver = new DFS();
        long t0 = System.currentTimeMillis();
        boolean found = solver.search();
        long elapsed = System.currentTimeMillis() - t0;
        long expanded = solver.expanded;
        int maxDepth = solver.maxDepth;

        try (PrintWriter writer = new PrintWriter(new FileWriter(solver.outputFile))) {
            writer.printf("%s se encontró solución. ", found ? "Sí" : "No");
            writer.printf("Nodos expandidos: %d. ", expanded);
            writer.printf("Profundidad máxima: %d. ", maxDepth);
            writer.printf("Tiempo de ejecución: %d ms. ", elapsed);
            writer.println();

            if (found) {
                writer.println("=== SOLUCIÓN ===");
                for (Board b : solver.solution) {
                    writer.println(b.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean search() {
        Board root = new Board();
        System.out.println("Initial Board:\n" + root);
        parent.put(root, null);

        Board goal = recursiveDFS(root, 0);
        if (goal != null) {
            buildSolution(goal);
            return true;
        }
        return false;
    }

    private Board recursiveDFS(Board current, int depth) {
        if (!visited.add(current)) return null;
        expanded++;
        maxDepth = Math.max(maxDepth, depth);

        if (current.isSolution()) {
            return current;
        }

        for (Board neighbor : current.getPossibleBoards()) {
            if (!visited.contains(neighbor)) {
                parent.put(neighbor, current);
                Board result = recursiveDFS(neighbor, depth + 1);
                if (result != null) {
                    return result;
                }
            }
        }

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
