package org.sokoban.algorithms;

import org.sokoban.models.Board;
import java.util.*;
import java.io.FileWriter;

public class DFS {
    // Métricas opcionales
    private long expanded = 0;
    private int maxDepth = 0;

    public List<Board> search(Board start) {
        long t0 = System.currentTimeMillis();
        Set<Board> visited = new HashSet<>();
        List<Board> path = new ArrayList<>();

        boolean ok = dfs(start, visited, path, 0);

        long ms = System.currentTimeMillis() - t0;
        
        System.out.printf("DFS %s. Nodos expandidos=%d, ProfMax=%d, Tiempo=%d ms%n",
                ok ? "encontró solución" : "no encontró solución", expanded, maxDepth, ms);

        try{

            FileWriter writer = new FileWriter("dfs_metrics.txt", true);
            writer.write(String.format("DFS %s. Nodos expandidos=%d, ProfMax=%d, Tiempo=%d ms%n",
                    ok ? "encontró solución" : "no encontró solución", expanded, maxDepth, ms));
            writer.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return ok ? path : Collections.emptyList();
    }

    private boolean dfs(Board b, Set<Board> visited, List<Board> path, int depth) {
        if (!visited.add(b)) return false;
        expanded++;
        if (depth > maxDepth) maxDepth = depth;

        if (b.isSolution()) {
            path.add(b);
            return true;
        }

        for (Board nb : b.getPossibleBoards()) {
            if (dfs(nb, visited, path, depth + 1)) {
                path.add(0, b);
                return true;
            }
        }
        return false;
    }
}

