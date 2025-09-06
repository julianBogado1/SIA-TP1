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


import org.sokoban.models.*;

public class AStar {
    private final PriorityQueue<BoardNode> frontier;
    private final Set<Board> visited;
    private final Map<Board, Board> parent;
    private final Map<Board, Integer> gScore;
    private String outputFile;
    private long expanded = 0;
    private int maxDepth = 0;

    public AStar(String heuristicType) {
        if (heuristicType.equals("h2")) {
            frontier = new PriorityQueue<>(new AdmisibleHeuristic());
            outputFile = "src/main/resources/AStarH2_solution.txt";
            System.out.println("h2");
        } else if(heuristicType.equals("h3")){
            System.out.println("h3");
            frontier = new PriorityQueue<>(new EuclideanHeuristic());
            outputFile = "src/main/resources/AStarH3_solution.txt";
        }
        else{
            System.out.println("h1");
            frontier = new PriorityQueue<>(new Heuristic());
            outputFile = "src/main/resources/AStarH1_solution.txt";
        }
        visited = new HashSet<>();
        parent = new HashMap<>();
        gScore = new HashMap<>();
    }

    public static void main(String[] args) {
        AStar solver = new AStar(args[0]);
        long t0 = System.currentTimeMillis();
        List<Board> solution = solver.solve(new Board(), args[0]); //receives heuristic type
        long elapsed = System.currentTimeMillis() - t0;
        boolean found = solution != null;

        try (PrintWriter writer = new PrintWriter(new FileWriter(solver.outputFile))) {
            writer.printf("%s se encontró solución. ", found ? "Sí" : "No");
            writer.printf("Nodos expandidos: %d. ", solver.expanded);
            writer.printf("# Nodos solucion: %d. ", solution.size());
            writer.printf("Frontier: %d. ", solver.frontier.size());
            writer.printf("Tiempo de ejecución: %d ms. ", elapsed);
            writer.println();

            System.out.printf("%s se encontró solución. ", found ? "Sí" : "No");
            System.out.printf("Nodos expandidos: %d. ", solver.expanded);
            System.out.printf("# Nodos solucion: %d. ", solution.size());
            System.out.printf("Frontier: %d. ", solver.frontier.size());
            System.out.printf("Tiempo de ejecución: %d ms. ", elapsed);

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

    public ResultClass getResultClass(Board board, String heuristicType) {
        long t0 = System.currentTimeMillis();
        List<Board> solution = solve(board, heuristicType);
        long elapsed = System.currentTimeMillis() - t0;
        boolean found = solution != null;
        int solutionSize = found ? solution.size() : 0;
        return new ResultClass(found, (int) expanded, solutionSize, frontier.size(), maxDepth, elapsed);
    }

    public List<Board> solve(Board board, String heuristicType) {
        Board start = board;
        System.out.println("Initial Board:\n" + start);
        BoardNode startNode = new BoardNode(start, 0, heuristicType);
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
                    frontier.add(new BoardNode(neighbor, possibleG, heuristicType));
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

        BoardNode(Board board, int g, String heuristicType) {
            this.board = board;
            this.g = g;
            if(heuristicType.equals("h2")){
                this.f = g + board.admisibleHeuristic(); // f = g + h
            } else if(heuristicType.equals("h3")){
                this.f = g + board.euclideanDistance();
            }
            else{
                this.f = g + board.admisibleHeuristic();
            }
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
    private static class EuclideanHeuristic implements Comparator<BoardNode> {
        @Override
        public int compare(BoardNode o1, BoardNode o2) {
            return Integer.compare(o1.getF(), o2.getF());
        }
    }
}

