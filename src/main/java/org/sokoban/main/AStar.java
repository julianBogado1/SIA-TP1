package org.sokoban.main;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import org.sokoban.models.Board;

public class AStar {
    private final PriorityQueue<BoardNode> frontier;
    private final Set<Board> visited;
    private final Map<Board, Board> parent;
    private final Map<Board, Integer> gScore;
    private final String outputFile = "src/main/resources/AStarH2_solution.txt";
    private long expanded = 0;
    private int maxDepth = 0;

    public AStar(String heuristicType) {
        if (heuristicType.equals("h1")) {
            frontier = new PriorityQueue<>(new Heuristic());
            System.out.println("h1");
        } else {
            System.out.println("h2");
            frontier = new PriorityQueue<>(new AdmisibleHeuristic());
        }
        visited = new HashSet<>();
        parent = new HashMap<>();
        gScore = new HashMap<>();
    }

    public static void main(String[] args) {
        AStar solver = new AStar("h2");
        long t0 = System.currentTimeMillis();
        List<Board> solution = solver.solve();
        long elapsed = System.currentTimeMillis() - t0;
        boolean found = solution != null;

        try (PrintWriter writer = new PrintWriter(new FileWriter(solver.outputFile))) {
            writer.printf("%s se encontró solución. ", found ? "Sí" : "No");
            writer.printf("Nodos expandidos: %d. ", solver.expanded);
            writer.printf("# Nodos solucion: %d. ", solution.size());
            writer.printf("Frontier: %d. ", solver.frontier.size());
            writer.printf("Tiempo de ejecución: %d ms. ", elapsed);
            writer.println();

            if(found){
                writer.println("=== SOLUCIÓN ===");
                for (Board b : solution) {
                    writer.println(b);
                }
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Board> solve() {
        Board start = new Board();
        System.out.println("Initial Board:\n" + start);
        BoardNode startNode = new BoardNode(start, 0);
        frontier.add(startNode);
        gScore.put(start, 0);
        parent.put(start, null);

        int iterations = 0;
        while (!frontier.isEmpty() /*&& iterations++ < 10000000*/) {
            BoardNode currentNode = frontier.poll();
            Board current = currentNode.board;

            if (current.isSolution()) {
                return buildSolution(current);
            }

            if (visited.contains(current)) continue;
            visited.add(current);
            expanded++;

            for (Board neighbor : current.getPossibleBoards()) {
                int possibleG = gScore.get(current) + 1;

                if (!gScore.containsKey(neighbor) || possibleG < gScore.get(neighbor)) {
                    gScore.put(neighbor, possibleG);
                    parent.put(neighbor, current);
                    frontier.add(new BoardNode(neighbor, possibleG));
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

    private static class BoardNode {
        Board board;
        int g;
        int f;

        BoardNode(Board board, int g) {
            this.board = board;
            this.g = g;
            //this.f = g + board.heuristic(); // f = g + h
            this.f = g + board.admisibleHeuristic();
        }

        public int getF() {
            return f;
        }
    }

    private static class Heuristic implements Comparator<BoardNode> {
        @Override
        public int compare(BoardNode o1, BoardNode o2) {
            return Integer.compare(o1.getF(), o2.getF());
        }
    }
    
    private static class AdmisibleHeuristic implements Comparator<BoardNode> {
        @Override
        public int compare(BoardNode o1, BoardNode o2) {
            return Integer.compare(o1.getF(), o2.getF());
        }
    }
}

