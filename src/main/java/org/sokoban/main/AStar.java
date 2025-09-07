package org.sokoban.main;

import org.sokoban.models.Board;
import org.sokoban.models.ResultClass;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class AStar {
    private final PriorityQueue<BoardNode> frontier;
    private final Map<Board, Board> parent;
    private final Map<Board, Integer> gScore;

    private String outputFile;
    private long expanded = 0;
    private int maxDepth = 0;

    public AStar(String heuristicType) {
        // Un único comparator (f, luego g) — la heurística se aplica en BoardNode
        this.frontier = new PriorityQueue<>(new ByFThenG());
        this.parent = new HashMap<>();
        this.gScore = new HashMap<>();

        if ("h2".equals(heuristicType)) {
            outputFile = "src/main/resources/AStarH2_solution.txt";
            System.out.println("h2");
        } else if ("h3".equals(heuristicType)) {
            outputFile = "src/main/resources/AStarH3_solution.txt";
            System.out.println("h3");
        } else {
            outputFile = "src/main/resources/AStarH1_solution.txt";
            System.out.println("h1");
        }
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Uso: java AStar <h1|h2|h3>");
            return;
        }
        String heuristicType = args[0];

        AStar solver = new AStar(heuristicType);
        long t0 = System.currentTimeMillis();
        List<Board> solution = solver.solve(new Board(), heuristicType); // pasa tu estado inicial real
        long elapsed = System.currentTimeMillis() - t0;
        boolean found = (solution != null);
        int solSize = found ? solution.size() : 0;

        try (PrintWriter writer = new PrintWriter(new FileWriter(solver.outputFile))) {
            writer.printf("%s se encontró solución. ", found ? "Sí" : "No");
            writer.printf("Nodos expandidos: %d. ", solver.expanded);
            writer.printf("# Nodos solucion: %d. ", solSize);
            writer.printf("Frontier: %d. ", solver.frontier.size());
            writer.printf("Tiempo de ejecución: %d ms. ", elapsed);
            writer.println();

            System.out.printf("%s se encontró solución. ", found ? "Sí" : "No");
            System.out.printf("Nodos expandidos: %d. ", solver.expanded);
            System.out.printf("# Nodos solucion: %d. ", solSize);
            System.out.printf("Frontier: %d. ", solver.frontier.size());
            System.out.printf("Tiempo de ejecución: %d ms. ", elapsed);

            if (found) {
                writer.println();
                writer.println("=== SOLUCIÓN ===");
                for (Board b : solution) {
                    writer.println(b);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Devuelve métricas en tu ResultClass (sin necesidad de imprimir el camino). */
    public ResultClass getResultClass(Board board, String heuristicType) {
        long t0 = System.currentTimeMillis();
        List<Board> solution = solve(board, heuristicType);
        long elapsed = System.currentTimeMillis() - t0;
        boolean found = (solution != null);
        int solutionSize = found ? solution.size() : 0;
        return new ResultClass(found, (int) expanded, solutionSize, frontier.size(), maxDepth, elapsed);
    }

    /** Devuelve el camino solución (o null si no hay). */
    public List<Board> solve(Board start, String heuristicType) {
        // Reset de estado para búsquedas independientes
        frontier.clear();
        parent.clear();
        gScore.clear();
        expanded = 0;
        maxDepth = 0;

        // Init
        gScore.put(start, 0);
        parent.put(start, null);
        frontier.add(new BoardNode(start, 0, heuristicType));

        while (!frontier.isEmpty()) {
            BoardNode currentNode = frontier.poll();
            Board current = currentNode.board;

            // Descarte perezoso: si no es el mejor g conocido, ignoro este nodo "viejo"
            Integer bestG = gScore.get(current);
            if (bestG == null || currentNode.g > bestG) {
                continue;
            }

            // Profundidad máxima observada en expansión real
            if (currentNode.g > maxDepth) maxDepth = currentNode.g;

            // Objetivo
            if (current.isSolution()) {
                return buildSolution(current);
            }

            // Expandimos
            expanded++;

            // Sucesores
            for (Board neighbor : current.getPossibleBoards()) {
                int tentativeG = bestG + 1; // costo uniforme
                Integer known = gScore.get(neighbor);
                if (known == null || tentativeG < known) {
                    gScore.put(neighbor, tentativeG);
                    parent.put(neighbor, current);
                    frontier.add(new BoardNode(neighbor, tentativeG, heuristicType));
                }
            }
        }
        return null; // sin solución
    }

    private List<Board> buildSolution(Board goal) {
        List<Board> path = new ArrayList<>();
        for (Board at = goal; at != null; at = parent.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }

    // ----------------- Auxiliares -----------------

    private static final class BoardNode {
        final Board board;
        final int g;
        final int f;

        BoardNode(Board board, int g, String heuristicType) {
            this.board = board;
            this.g = g;

            int h;
            switch (heuristicType) {
                case "h2":
                    // Ej.: Manhattan / admisible
                    h = board.admisibleHeuristic();
                    break;
                case "h3":
                    // Si euclideanDistance() es double, evito truncar hacia abajo
                    // para no introducir sesgos raros en la comparación:
                    h = (int) Math.ceil(board.h2ManhattanHungarian());
                    break;
                case "h1":
                default:
                    // Ej.: celdas mal colocadas (admisible para 8-puzzle; para Sokoban dependerá de tu definición)
                    h = board.h1PerBoxMinManhattan();
                    break;
            }
            this.f = g + h;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof BoardNode)) return false;
            BoardNode boardNode = (BoardNode) o;
            return board.equals(boardNode.board);
        }

        @Override
        public int hashCode() {
            return board.hashCode();
        }
    }

    /** Ordena por f y desempata por g para mayor estabilidad. */
    private static final class ByFThenG implements Comparator<BoardNode> {
        @Override
        public int compare(BoardNode a, BoardNode b) {
            int cf = Integer.compare(a.f, b.f);
            if (cf != 0) return cf;
            return Integer.compare(a.g, b.g);
        }
    }
}
