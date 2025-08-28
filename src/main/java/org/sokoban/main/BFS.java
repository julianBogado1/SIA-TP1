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

public class BFS {
    Queue<Board> frontier = new LinkedList<>();
    Queue<Board> solution = new LinkedList<>();
    Set<Board> visited = new HashSet<>();
    Map<Board, Board> parent = new HashMap<>();
    private final String outputFile = "src/main/resources/BFS_solution.txt";
    private long expanded = 0;
    private int maxDepth = 0;

    public static void main(String[] args) {
        BFS solver = new BFS();
        long t0 = System.currentTimeMillis();
        Queue<Board> answer = solver.bfs();
        long elapsed = System.currentTimeMillis() - t0;
        boolean found = answer != null;

        try (PrintWriter writer = new PrintWriter(new FileWriter(solver.outputFile))) {
            writer.printf("%s se encontró solución. ", found ? "Sí" : "No");
            writer.printf("Nodos expandidos: %d. ", solver.expanded);
<<<<<<< HEAD
            writer.printf("Nodos frontera: %d. ", solver.frontier.size());
=======
            writer.printf("Frontier: %d. ", solver.frontier.size());
>>>>>>> 931e7ad7774cab930ec2619345c0bdd64f6d494c
            writer.printf("Tiempo de ejecución: %d ms. ", elapsed);
            writer.println();
            
            if(found){
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
        expanded++;
        if (current.isSolution()) {
            buildSolution(current);
            return true;
        }

        for (Board move : current.getPossibleBoards()) {
            if (!visited.contains(move)) {
                visited.add(move);
                parent.put(move, current);
                frontier.add(move);
            }
        }

        return false;
    }

    private Queue<Board> bfs() {
        Board root = new Board();
        System.out.println("Initial Board:\n" + root);
        frontier.add(root);
        visited.add(root);
        parent.put(root, null);
        int iterations = 0;

        while (!frontier.isEmpty() && iterations++ < 10000000) {
            Board current = frontier.poll();

            if (nextStep(current)) {
                return solution;
            }
        }
        return null;
    }

    private void buildSolution(Board goal) {
        Deque<Board> path = new ArrayDeque<>();
        int depth = 0;
        for (Board at = goal; at != null; at = parent.get(at)) {
            path.push(at);
            depth++;
        }
        maxDepth = depth - 1;
        solution.addAll(path);
    }
}


