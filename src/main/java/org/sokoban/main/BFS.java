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
    public Queue<Board> frontier = new LinkedList<>();
    public Queue<Board> solution = new LinkedList<>();
    public Set<Board> visited = new HashSet<>();
    public Map<Board, Board> parent = new HashMap<>();
    private final String outputFile = "src/main/resources/BFS_solution.txt";
    public long expanded = 0;
    public int maxDepth = 0;

    public static void main(String[] args) {
        BFS solver = new BFS();
        long t0 = System.currentTimeMillis();
        Queue<Board> answer = solver.bfs(new Board(args[0]));
        long elapsed = System.currentTimeMillis() - t0;
        boolean found = answer != null;

        try (PrintWriter writer = new PrintWriter(new FileWriter(solver.outputFile))) {
            writer.printf("%s se encontró solución. ", found ? "Sí" : "No");
            writer.printf("Nodos expandidos: %d. ", solver.expanded);
            writer.printf("# Nodos solucion: %d. ", solver.solution.size());
            writer.printf("Frontier: %d. ", solver.frontier.size());
            writer.printf("Tiempo de ejecución: %d ms. ", elapsed);
            writer.println();
            
            System.out.printf("%s se encontró solución. ", found ? "Sí" : "No");
            System.out.printf("Nodos expandidos: %d. ", solver.expanded);
            System.out.printf("# Nodos solucion: %d. ", solver.solution.size());
            System.out.printf("Frontier: %d. ", solver.frontier.size());
            System.out.printf("Tiempo de ejecución: %d ms. ", elapsed);

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

    public Queue<Board> bfs(Board board) {
        Board root = board;
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


