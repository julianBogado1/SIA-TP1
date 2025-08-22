package org.sokoban.main;

import org.sokoban.models.Board;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class IDDFS {

    private static final int MAX_DEPTH=1000;

    private final Map<Board, Board> parent = new HashMap<>();

    public static void main(String[] args) {
        IDDFS solver = new IDDFS();
        List<Board> solution = solver.solve();

        if (solution == null) {
            System.out.println("No solution found");
            return;
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter("src/main/resources/IDDFS_solution.txt"))) {
            for (Board b : solution) {
                writer.println(b);
            }
            System.out.println("Solution written to solution.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Board> solve() {
        Board start = new Board();
        int depth = 0;

        while (depth<=MAX_DEPTH) {
            Set<Board> visited = new HashSet<>();
            parent.clear();
            parent.put(start, null);

            Board result = dls(start, depth, visited);
            if (result != null) {
                return buildSolution(result);
            }
            depth++;
        }
        return null;
    }

    private Board dls(Board current, int depthLimit, Set<Board> visited) {

        if (current.isSolution()){
            return current;
        }

        if (depthLimit <= 0){
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

