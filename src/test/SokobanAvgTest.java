package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Queue;

import org.sokoban.models.Board;
import org.sokoban.main.BFS;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class SokobanAvgTest {



            // SMALL
            // # # # # # #
            // # T B     #
            // #         #
            // #     P   #
            // # # # # # #
            // (already implemented as smallCells)

            // MEDIUM
            // # # # # # # # #
            // # T B         #
            // #             #
            // #             #
            // #         P   #
            // # # # # # # # #
            // (already implemented as mediumCells)

            // LARGE
            // # # # # # # # #
            // # T B         #
            // #             #
            // #             #
            // #             #
            // #             # 
            // #         P   #
            // # # # # # # # #
            // (already implemented as largeCells)

            // MEDIUM - 3 boxes
            // # # # # # # # #
            // # T       B T #
            // # B           #
            // #         B   #
            // #     T       #
            // # T   B       # 
            // #         P   #
            // # # # # # # # #
            // (already implemented as medium3BoxesCells)

            // MEDIUM WITH WALLS
            // # # # # # # # #
            // # T           #
            // # B           #
            // #   # # # B   #
            // #     T       #
            // # T   B # #   # 
            // #       # P   #
            // # # # # # # # #
            // (already implemented as mediumWithWallsCells)

            // MEDIUM WITH WALLS2
            private static Cell[][] mediumWithWalls2Cells = {
                { new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell(State.TARGET), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell(State.BOX), new Cell(State.EMPTY), new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.BOX), new Cell(State.EMPTY), new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.TARGET), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell(State.TARGET), new Cell(State.EMPTY), new Cell(State.BOX), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.PLAYER), new Cell(State.EMPTY), new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL) }
            };

            // MEDIUM WITH WALLS3
            private static Cell[][] mediumWithWalls3Cells = {
                { new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell(State.TARGET), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell(State.BOX), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell.EMPTY, new Cell(State.EMPTY), new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell.BOX, new Cell(State.WALL), new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.TARGET), new Cell(State.EMPTY), new Cell.EMPTY, new Cell(State.EMPTY), new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell(State.TARGET), new Cell(State.EMPTY), new Cell(State.BOX), new Cell(State.WALL), new Cell.EMPTY, new Cell(State.EMPTY), new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.WALL), new Cell.PLAYER, new Cell.EMPTY, new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell.WALL, new Cell.WALL, new Cell(State.WALL) }
            };

            // MEDIUM WITH WALLS4
            private static Cell[][] mediumWithWalls4Cells = {
                { new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell(State.TARGET), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell(State.BOX), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell.EMPTY, new Cell(State.EMPTY), new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell.BOX, new Cell.EMPTY, new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.WALL), new Cell(State.TARGET), new Cell.EMPTY, new Cell.EMPTY, new Cell.EMPTY, new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell(State.TARGET), new Cell.EMPTY, new Cell.BOX, new Cell.EMPTY, new Cell.EMPTY, new Cell.EMPTY, new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell.EMPTY, new Cell.EMPTY, new Cell.EMPTY, new Cell.WALL, new Cell.PLAYER, new Cell.EMPTY, new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell.WALL, new Cell.WALL, new Cell.WALL, new Cell.WALL, new Cell.WALL, new Cell.WALL, new Cell(State.WALL) }
            };

            // MEDIUM WITH WALLS5
            private static Cell[][] mediumWithWalls5Cells = {
                { new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell(State.TARGET), new Cell.EMPTY, new Cell.EMPTY, new Cell.EMPTY, new Cell.WALL, new Cell.EMPTY, new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell.BOX, new Cell.WALL, new Cell.EMPTY, new Cell.EMPTY, new Cell.EMPTY, new Cell.EMPTY, new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell.EMPTY, new Cell.WALL, new Cell.EMPTY, new Cell.WALL, new Cell.BOX, new Cell.EMPTY, new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell.EMPTY, new Cell.EMPTY, new Cell.TARGET, new Cell.EMPTY, new Cell.EMPTY, new Cell.EMPTY, new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell.TARGET, new Cell.EMPTY, new Cell.BOX, new Cell.WALL, new Cell.WALL, new Cell.EMPTY, new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell.EMPTY, new Cell.EMPTY, new Cell.EMPTY, new Cell.WALL, new Cell.PLAYER, new Cell.EMPTY, new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell.WALL, new Cell.WALL, new Cell.WALL, new Cell.WALL, new Cell.WALL, new Cell.WALL, new Cell(State.WALL) }
            };

            // MEDIUM WITH WALLS6
            private static Cell[][] mediumWithWalls6Cells = {
                { new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell(State.TARGET), new Cell.EMPTY, new Cell.EMPTY, new Cell.BOX, new Cell.EMPTY, new Cell.EMPTY, new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell.BOX, new Cell.EMPTY, new Cell.EMPTY, new Cell.EMPTY, new Cell.EMPTY, new Cell.EMPTY, new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell.EMPTY, new Cell.WALL, new Cell.WALL, new Cell.WALL, new Cell.BOX, new Cell.EMPTY, new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell.EMPTY, new Cell.EMPTY, new Cell.TARGET, new Cell.WALL, new Cell.EMPTY, new Cell.EMPTY, new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell.TARGET, new Cell.EMPTY, new Cell.EMPTY, new Cell.EMPTY, new Cell.EMPTY, new Cell.EMPTY, new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell.EMPTY, new Cell.EMPTY, new Cell.EMPTY, new Cell.WALL, new Cell.PLAYER, new Cell.EMPTY, new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell.WALL, new Cell.WALL, new Cell.WALL, new Cell.WALL, new Cell.WALL, new Cell.WALL, new Cell(State.WALL) }
            };

    private static Cell[][] cells = {
        { new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL) },
        { new Cell(State.WALL), new Cell(State.TARGET), new Cell(State.WALL), new Cell(State.TARGET), new Cell(State.BOX), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.WALL) },
        { new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.WALL) },
        { new Cell(State.WALL), new Cell(State.BOX), new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.WALL) },
        { new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.PLAYER), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.WALL) },
        { new Cell(State.WALL), new Cell(State.TARGET), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL) },
        { new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.BOX), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.BOX), new Cell(State.WALL) },
        { new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.WALL) },
        { new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL) }
    };

            // SMALL
    private static Cell[][] smallCells = {
        { new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL) },
        { new Cell(State.WALL), new Cell(State.TARGET), new Cell(State.BOX), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.WALL) },
        { new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.WALL) },
        { new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.PLAYER), new Cell(State.EMPTY), new Cell(State.WALL) },
        { new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL) }
    };

    // MEDIUM
    private static Cell[][] mediumCells = {
        { new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL) },
        { new Cell(State.WALL), new Cell(State.TARGET), new Cell(State.BOX), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.WALL) },
        { new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.WALL) },
        { new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.WALL) },
        { new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.PLAYER), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.WALL) },
        { new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL) }
    };

    // LARGE
    private static Cell[][] largeCells = {
        { new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL) },
        { new Cell(State.WALL), new Cell(State.TARGET), new Cell(State.BOX), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.WALL) },
        { new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.WALL) },
        { new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.WALL) },
        { new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.WALL) },
        { new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.WALL), new Cell(State.WALL) },
        { new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.PLAYER), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.WALL) },
        { new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL) }
    };

    // MEDIUM - 3 boxes
    private static Cell[][] medium3BoxesCells = {
        { new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL) },
        { new Cell(State.WALL), new Cell(State.TARGET), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.BOX), new Cell(State.TARGET), new Cell(State.WALL) },
        { new Cell(State.WALL), new Cell(State.BOX), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.WALL) },
        { new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.BOX), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.WALL) },
        { new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.TARGET), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.WALL) },
        { new Cell(State.WALL), new Cell(State.TARGET), new Cell(State.EMPTY), new Cell(State.BOX), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.WALL) },
        { new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.PLAYER), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.WALL) },
        { new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL) }
    };

    // MEDIUM WITH WALLS
    private static Cell[][] mediumWithWallsCells = {
        { new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL) },
        { new Cell(State.WALL), new Cell(State.TARGET), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.WALL) },
        { new Cell(State.WALL), new Cell(State.BOX), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.WALL) },
        { new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.BOX), new Cell(State.EMPTY), new Cell(State.WALL) },
        { new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.TARGET), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.WALL) },
        { new Cell(State.WALL), new Cell(State.TARGET), new Cell(State.EMPTY), new Cell(State.BOX), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.WALL) },
        { new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.WALL), new Cell(State.PLAYER), new Cell(State.EMPTY), new Cell(State.WALL) },
        { new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL) }
    };

    // MEDIUM - five boxes
    private static Cell[][] mediumFiveBoxesCells = {
        { new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL) },
        { new Cell(State.WALL), new Cell(State.TARGET), new Cell(State.BOX), new Cell(State.TARGET), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.WALL) },
        { new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.WALL) },
        { new Cell(State.WALL), new Cell(State.TARGET), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.WALL) },
        { new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.BOX), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.WALL) },
        { new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.BOX), new Cell(State.EMPTY), new Cell(State.BOX), new Cell(State.TARGET), new Cell(State.EMPTY), new Cell(State.WALL) },
        { new Cell(State.WALL), new Cell(State.TARGET), new Cell(State.BOX), new Cell(State.EMPTY), new Cell(State.PLAYER), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.WALL) },
        { new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL) }
    };

    private static final Map<String, Board> maps = new HashMap<>()
    /* 
        .put("default", new Board(9, 9, cells, 4, 4))
        .put("small", new Board(6, 5, smallCells, 3, 3))
        .put("medium", new Board(8, 6, mediumCells, 5, 4))
        .put("large", new Board(8, 8, largeCells, 6, 4))
        .put("mediumfiveboxes", new Board(8, 8, mediumFiveBoxesCells, 6, 5))
        */
        .put("medium3boxes", new Board(8, 8, medium3BoxesCells, 6, 4))
        .put("mediumwithwalls", new Board(8, 8, mediumWithWallsCells, 6, 5))
        .put("mediumwithwalls2", new Board(8, 8, mediumWithWalls2Cells, 6, 5))
        .put("mediumwithwalls3", new Board(8, 8, mediumWithWalls3Cells, 6, 5))
        .put("mediumwithwalls4", new Board(8, 8, mediumWithWalls4Cells, 6, 5))
        .put("mediumwithwalls5", new Board(8, 8, mediumWithWalls5Cells, 6, 5))
        .put("mediumwithwalls6", new Board(8, 8, mediumWithWalls6Cells, 6, 5))
        ;
    
    @Test
    public void testAverageBFS() {
        // Implement test logic here

        int expandedNodes = 0;
        int solutionNodes = 0;
        int frontierNodes = 0;
        int totalTime = 0;

        for (Map.Entry<String, Board> entry : maps.entrySet()) {
            String mapName = entry.getKey();
            Board board = entry.getValue();

            BFS solver = new BFS();
            long t0 = System.currentTimeMillis();
            Queue<Board> answer = solver.bfs(new Board(args[0]));
            long elapsed = System.currentTimeMillis() - t0;
            boolean found = answer != null;

            if (found) {
                expandedNodes += solver.expanded;
                solutionNodes += solver.solution.size();
                frontierNodes += solver.frontier.size();
                totalTime += elapsed;
            }
            /*
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
            } catch (IOException e) {
                e.printStackTrace();
            }*/

        }

        System.out.printf("BFS - Time for %s: %.2f ms%n; expanded: %.2f; solution: %.2f; frontier: %.2f%n",
         (double) totalTime / 7, (double) expandedNodes / 7, (double) solutionNodes / 7, (double) frontierNodes / 7);

    }
}
