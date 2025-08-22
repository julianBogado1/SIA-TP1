package org.sokoban.main;

import org.sokoban.models.Board;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class BFS {
    Queue<Board> frontier = new LinkedList<>();
    Queue<Board> solution = new LinkedList<>();
    Set<Board> visited = new HashSet<>();
    Map<Board, Board> parent = new HashMap<>();

    public static void main(String[] args) {
        BFS solver = new BFS();
        Queue<Board> answer = solver.bfs();

        if (answer == null) {
            System.out.println("No solution found");
            return;
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter("src/main/resources/BFS_solution.txt"))) {
            for (Board b : answer) {
                writer.println(b.toString());
            }
            System.out.println("Solution written to BFS_solution.txt");
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
        for (Board at = goal; at != null; at = parent.get(at)) {
            path.push(at);
        }
        solution.addAll(path);
    }
}


