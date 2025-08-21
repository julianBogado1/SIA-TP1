package org.sokoban.main;

import org.sokoban.models.Board;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class AStar {

    private final PriorityQueue<BoardNode> frontier;
    private final Set<Board> visited;
    private final Map<Board, Board> parent;
    private final Map<Board, Integer> gScore;

    public AStar() {
        frontier = new PriorityQueue<>(new Heuristic());
        visited = new HashSet<>();
        parent = new HashMap<>();
        gScore = new HashMap<>();
    }

    public static void main(String[] args) {
        AStar solver = new AStar();
        List<Board> solution = solver.solve();

        if (solution == null) {
            System.out.println("No solution found");
            return;
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter("src/main/resources/solution.txt"))) {
            for (Board b : solution) {
                writer.println(b);
            }
            System.out.println("Solution written to AStar_solution.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Board> solve() {
        Board start = new Board();
        BoardNode startNode = new BoardNode(start, 0);
        frontier.add(startNode);
        gScore.put(start, 0);
        parent.put(start, null);

        while (!frontier.isEmpty()) {
            BoardNode currentNode = frontier.poll();
            Board current = currentNode.board;

            if (current.isSolution()) {
                return buildSolution(current);
            }

            if (visited.contains(current)) continue;
            visited.add(current);

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
            this.f = g + board.heuristic(); // f = g + h
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
}

