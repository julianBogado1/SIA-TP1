package org.sokoban.main;

import org.sokoban.models.Board;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class DFS {
    // Estructuras de búsqueda
    private final Set<Board> visited = new HashSet<>();
    private final Map<Board, Board> parent = new HashMap<>();
    private final Queue<Board> solution = new LinkedList<>();

    // Métricas generales
    private long expanded = 0;
    private int maxDepth = 0;
    private Board lastBoard;

    // Frontera en tiempo real y snapshots por nodo
    private int frontierCurrent = 0;
    private final Map<Board, Integer> frontierBefore = new HashMap<>();
    private final Map<Board, Integer> frontierAfter  = new HashMap<>();

    // Salida
    private final String outputFile = "SIA-TP1/src/main/resources/DFS_solution.txt";

    public static void main(String[] args) {
        DFS solver = new DFS();

        long t0 = System.currentTimeMillis();
        boolean found = solver.search();
        long elapsed = System.currentTimeMillis() - t0;

        // Preparar archivo y crear directorios si no existen
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

    /** Ejecuta DFS recursivo desde un Board por defecto. */
    public boolean search() {
        Board root = new Board();
        System.out.println("Initial Board:\n" + root);

        // Inicialización
        parent.put(root, null);
        visited.clear();
        solution.clear();
        expanded = 0;
        maxDepth = 0;

        // La frontera arranca con la raíz sin expandir
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

        // Generar hijos NO visitados
        List<Board> children = current.getPossibleBoards();
        List<Board> nexts = new ArrayList<>(children.size());
        for (Board nb : children) {
            if (!visited.contains(nb)) {
                parent.put(nb, current);
                nexts.add(nb);
            }
        }

        // Los hijos pasan a formar parte de la frontera
        frontierCurrent += nexts.size();

        // Snapshot de frontera DESPUÉS de generar los hijos de 'current'
        frontierAfter.put(current, frontierCurrent);

        // DFS: profundizar hijo por hijo
        for (Board child : nexts) {
            Board result = recursiveDFS(child, depth + 1);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

    /** Reconstruye el camino solución usando el mapa de padres. */
    private void buildSolution(Board goal) {
        Deque<Board> path = new ArrayDeque<>();
        for (Board at = goal; at != null; at = parent.get(at)) {
            path.push(at);
        }
        solution.addAll(path);
    }
}
