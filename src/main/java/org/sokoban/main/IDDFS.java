package org.sokoban.main;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Set;

import org.sokoban.models.Board;

public class IDDFS {

    private static final int MAX_DEPTH=1000;

    private final Map<Board, Board> parent = new HashMap<>();
    private final Queue<Board> frontier = new LinkedList<>();
    private final String outputFile = "src/main/resources/IDDFS_solution.txt";
    private long expanded = 0;
    private int maxDepth = 0;

    public static void main(String[] args) {
        IDDFS solver = new IDDFS();
        long t0 = System.currentTimeMillis();
        List<Board> solution = solver.solve(new Board(args[0]));
        long elapsed = System.currentTimeMillis() - t0;
        boolean found = solution!=null;
        long expanded = solver.expanded;
        int maxDepth = solver.maxDepth;

        if (solution == null) {
            System.out.println("No solution found");
            return;
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(solver.outputFile))) {
            writer.printf("%s se encontró solución. ", found ? "Sí" : "No");
            writer.printf("Nodos expandidos: %d. ", expanded);
            writer.printf("Profundidad máxima: %d. ", maxDepth);
            writer.printf("Frontier: %d. ", solver.frontier.size());
            writer.printf("Tiempo de ejecución: %d ms. ", elapsed);
            writer.println();

            System.out.printf("%s se encontró solución. ", found ? "Sí" : "No");
            System.out.printf("Nodos expandidos: %d. ", expanded);
            System.out.printf("Profundidad máxima: %d. ", maxDepth);
            System.out.printf("Frontier: %d. ", solver.frontier.size());
            System.out.printf("Tiempo de ejecución: %d ms. ", elapsed);

            if (found) {
                writer.println("=== SOLUCIÓN ===");
                for (Board b : solution) {
                    writer.println(b.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Board> solve(Board board) {
        Board start = board;
        System.out.println("Initial Board:\n" + start);
        int depth = 0;

        while (depth<=MAX_DEPTH) {
            Set<Board> visited = new HashSet<>();
            parent.clear();
            frontier.clear();
            parent.put(start, null);

            Board result = dls(start, depth, visited);
            if (result != null) {
                maxDepth = depth;
                return buildSolution(result);
            }
            depth++;
        }
        return null;
    }

    private Board dls(Board current, int depthLimit, Set<Board> visited) {
        expanded++;

        if (current.isSolution()){
            return current;
        }

        if (depthLimit <= 0){
            frontier.add(current);
            return null;
        }

        visited.add(current);

        for (Board neighbor : current.getPossibleBoards()) {
            if (!visited.contains(neighbor)) {
                parent.put(neighbor, current);
                Board result = dls(neighbor, depthLimit - 1, visited);
                if (result != null){
                    return result;
                }
            }
        }

        return null;
    }

    private List<Board> buildSolution(Board goal) {
        List<Board> path = new ArrayList<>();
        for (Board at = goal; at != null; at = parent.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }
}

