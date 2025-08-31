package org.sokoban.main;

import org.sokoban.models.Board;
import org.sokoban.models.ResultClass;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class DFS {
    private final Set<Board> visited = new HashSet<>();
    private final Map<Board, Board> parent = new HashMap<>();
    private final Queue<Board> solution = new LinkedList<>();

    private long expanded = 0;
    private int maxDepth = 0;
    private Board lastBoard;

    private int frontierCurrent = 0;
    private final Map<Board, Integer> frontierBefore = new HashMap<>();
    private final Map<Board, Integer> frontierAfter  = new HashMap<>();

    private final String outputFile = "SIA-TP1/src/main/resources/DFS_solution.txt";

    public static void main(String[] args) {
        DFS solver = new DFS();

        long t0 = System.currentTimeMillis();

        //map passed in argv
        boolean found = solver.search(new Board(args[0]));
        long elapsed = System.currentTimeMillis() - t0;

        File out = new File(solver.outputFile);
        File parentDir = out.getParentFile();
        if (parentDir != null) parentDir.mkdirs();

        try (PrintWriter writer = new PrintWriter(new FileWriter(out))) {
            writer.printf("%s se encontró solución. ", found ? "Sí" : "No");
            writer.printf("# Nodos solucion: %d. ", solver.solution.size());
            writer.printf("# Nodos expandidos: %d. ", solver.expanded);
            writer.printf("# Nodos frontera: %d. ", solver.frontierAfter.get(solver.lastBoard));
            writer.printf("Profundidad máxima: %d. ", solver.maxDepth);
            writer.printf("Tiempo de ejecución: %d ms.%n", elapsed);

            System.out.printf("%s se encontró solución. ", found ? "Sí" : "No");
            System.out.printf("# Nodos solucion: %d. ", solver.solution.size());
            System.out.printf("# Nodos expandidos: %d. ", solver.expanded);
            System.out.printf("# Nodos frontera: %d. ", solver.frontierAfter.get(solver.lastBoard));
            System.out.printf("Profundidad máxima: %d. ", solver.maxDepth);
            System.out.printf("Tiempo de ejecución: %d ms.%n", elapsed);
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

    public ResultClass getResultClass(Board board){
        DFS solver = new DFS();

        long startTime = System.currentTimeMillis();

        //map passed in argv
        boolean found = solver.search(board);
        long elapsed = System.currentTimeMillis() - startTime;
        return new ResultClass(
                found,
                (int) solver.expanded,
                solver.solution.size(),
                solver.frontierAfter.get(solver.lastBoard) != null ? solver.frontierAfter.get(solver.lastBoard) : 0,
                solver.maxDepth,
                elapsed
        );
    }

    public boolean search(Board board) {
        Board root = board;
        System.out.println("Initial Board:\n" + root);

        parent.put(root, null);
        visited.clear();
        solution.clear();
        expanded = 0;
        maxDepth = 0;

        frontierCurrent = 1;

        Board goal = recursiveDFS(root, 0);
        if (goal != null) {
            buildSolution(goal);
            return true;
        }
        return false;
    }

    private Board recursiveDFS(Board current, int depth) {
        frontierBefore.put(current, frontierCurrent);

        frontierCurrent--;

        if (!visited.add(current)) return null;
        expanded++;
        maxDepth = Math.max(maxDepth, depth);

        if (current.isSolution()) {
            frontierAfter.put(current, frontierCurrent);
            lastBoard = current;
            return current;
        }

        List<Board> children = current.getPossibleBoards();
        List<Board> nexts = new ArrayList<>(children.size());
        for (Board nb : children) {
            if (!visited.contains(nb)) {
                parent.put(nb, current);
                nexts.add(nb);
            }
        }

        frontierCurrent += nexts.size();

        frontierAfter.put(current, frontierCurrent);

        for (Board child : nexts) {
            Board result = recursiveDFS(child, depth + 1);
            if (result != null) {
                return result;
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
