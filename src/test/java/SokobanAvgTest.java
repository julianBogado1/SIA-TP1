

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Queue;
import java.util.HashMap;

import org.sokoban.models.*;
import org.sokoban.main.*;

public class SokobanAvgTest {

    // MEDIUM WITH WALLS2
    /*
    ########
    #.     #
    #$ #   #
    # # #$ #
    #  .   #
    #. $## #
    #    @ #
    ########
    */

    // MEDIUM WITH WALLS3
    /*
    ########
    #.     #
    #$     #
    # ###$ #
    #  .   #
    #. $#  #
    #   #@ #
    ########
    */

    // MEDIUM WITH WALLS4
    /*
    ########
    #.     #
    #$     #
    # ###$ #
    # #.   #
    #. $   #
    #   #@ #
    ########
    */

    // MEDIUM WITH WALLS5
    /*
    ########
    #.   # #
    #$#    #
    # # #$ #
    #  .   #
    #. $## #
    #   #@ #
    ########
    */

    // MEDIUM WITH WALLS6
    /*
    ########
    #.  $  #
    #$     #
    # ###$ #
    #  .#  #
    #.     #
    #   #@ #
    ########
    */

    // DEFAULT
    /*
    #########
    #.#$   #
    # # # # #
    #$ # # #
    #   @   #
    #.### ###
    # $    $#
    #     # #
    #########
    */

    // SMALL
    /*
    ######
    #.$  #
    #    #
    #  @ #
    ######
    */

    // MEDIUM
    /*
    ########
    #.$    #
    #      #
    #      #
    #   @  #
    ########
    */

    // LARGE
    /*
    ########
    #.$    #
    #      #
    #      #
    #      #
    #     ##
    #   @  #
    ########
    */

    // MEDIUM - 3 boxes
    /*
    ########
    #.   $.#
    #$     #
    #   $  #
    # .    #
    #. $   #
    #   @  #
    ########
    */

    // MEDIUM WITH WALLS
    /*
    ########
    #.     #
    #$     #
    # ###$ #
    #  .   #
    #. $#  #
    #   #@ #
    ########
    */

    // MEDIUM - five boxes
    /*
    ########
    #.$.   #
    #      #
    #.     #
    #   $  #
    # $ $. #
    #.$ @  #
    ########
    */
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
                { new Cell(State.WALL), new Cell(State.BOX), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.BOX), new Cell(State.EMPTY), new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.TARGET), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell(State.TARGET), new Cell(State.EMPTY), new Cell(State.BOX), new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.WALL), new Cell(State.PLAYER), new Cell(State.EMPTY), new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL) }
            };

            // MEDIUM WITH WALLS4
            private static Cell[][] mediumWithWalls4Cells = {
                { new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell(State.TARGET), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell(State.BOX), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.BOX), new Cell(State.EMPTY), new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.WALL), new Cell(State.TARGET), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell(State.TARGET), new Cell(State.EMPTY), new Cell(State.BOX), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.WALL), new Cell(State.PLAYER), new Cell(State.EMPTY), new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL) }
            };

            // MEDIUM WITH WALLS5
            private static Cell[][] mediumWithWalls5Cells = {
                { new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell(State.TARGET), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell(State.BOX), new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.WALL), new Cell(State.BOX), new Cell(State.EMPTY), new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.TARGET), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell(State.TARGET), new Cell(State.EMPTY), new Cell(State.BOX), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.WALL), new Cell(State.PLAYER), new Cell(State.EMPTY), new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL) }
            };

            // MEDIUM WITH WALLS6
            private static Cell[][] mediumWithWalls6Cells = {
                { new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell(State.TARGET), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.BOX), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell(State.BOX), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.BOX), new Cell(State.EMPTY), new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.TARGET), new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell(State.TARGET), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.WALL), new Cell(State.PLAYER), new Cell(State.EMPTY), new Cell(State.WALL) },
                { new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL) }
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
        { new Cell(State.WALL), new Cell(State.TARGET), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.WALL) },
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

    private static final Map<String, Board> maps = new HashMap<>();
    static {
        //  maps.put("default", new Board(9, 9, cells, 4, 4));
        // maps.put("small", new Board(6, 5, smallCells, 3, 3));
        // maps.put("medium", new Board(8, 6, mediumCells, 5, 4));
        // maps.put("large", new Board(8, 8, largeCells, 6, 4));
    //     // maps.put("mediumfiveboxes", new Board(8, 8, mediumFiveBoxesCells, 6, 5));
        maps.put("medium3boxes", new Board(8, 8, medium3BoxesCells, 6, 4));
        maps.put("mediumwithwalls", new Board(8, 8, mediumWithWallsCells, 6, 5));
        maps.put("mediumwithwalls2", new Board(8, 8, mediumWithWalls2Cells, 6, 5));
        maps.put("mediumwithwalls3", new Board(8, 8, mediumWithWalls3Cells, 6, 5));
        maps.put("mediumwithwalls4", new Board(8, 8, mediumWithWalls4Cells, 6, 5));
        maps.put("mediumwithwalls5", new Board(8, 8, mediumWithWalls5Cells, 6, 5));
        maps.put("mediumwithwalls6", new Board(8, 8, mediumWithWalls6Cells, 6, 5));
    }
    
    @Test
    public void testAverageBFS() {

        int expandedNodes = 0;
        int solutionNodes = 0;
        int frontierNodes = 0;
        int totalTime = 0;

        for (Map.Entry<String, Board> entry : maps.entrySet()) {
            Board board = entry.getValue();

            BFS solver = new BFS();
            long t0 = System.currentTimeMillis();
            Queue<Board> answer = solver.bfs(board);
            long elapsed = System.currentTimeMillis() - t0;
            boolean found = answer != null;

            if (found) {
                expandedNodes += solver.expanded;
                solutionNodes += solver.solution.size();
                frontierNodes += solver.frontier.size();
                totalTime += elapsed;

                System.out.printf("expanded: %.2f; solution: %.2f; frontier: %.2f%n", (double) solver.expanded, (double) solver.solution.size(), (double) solver.frontier.size());
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

        System.out.printf("BFS - Time for BFS: %.2f ms%n; expanded: %.2f; solution: %.2f; frontier: %.2f%n",
         (double) totalTime / 7, (double) expandedNodes / 7, (double) solutionNodes / 7, (double) frontierNodes / 7);

    }

    @Test
    public void testAverageDFS() {

        int expandedNodes = 0;
        int solutionNodes = 0;
        int frontierNodes = 0;
        int totalTime = 0;

        for (Map.Entry<String, Board> entry : maps.entrySet()) {
            Board board = entry.getValue();

            DFS solver = new DFS();
            ResultClass result = solver.getResultClass(board);

            if (result.isFound()) {
                expandedNodes += result.getNodesExpanded();
                solutionNodes += result.getSolutionSize();
                frontierNodes += result.getFrontierSize();
                totalTime += result.getExecutionTime();
            }
        }

        System.out.printf("DFS - Time for DFS: %.2f ms%n; expanded: %.2f; solution: %.2f; frontier: %.2f%n",
         (double) totalTime / 7, (double) expandedNodes / 7, (double) solutionNodes / 7, (double) frontierNodes / 7);

    }

    @Test
    public void testAverageAStarH1() {

        int expandedNodes = 0;
        int solutionNodes = 0;
        int frontierNodes = 0;
        int totalTime = 0;

        for (Map.Entry<String, Board> entry : maps.entrySet()) {
            Board board = entry.getValue();

            AStar solver = new AStar("h1");
            ResultClass result = solver.getResultClass(board);

            if (result.isFound()) {
                expandedNodes += result.getNodesExpanded();
                solutionNodes += result.getSolutionSize();
                frontierNodes += result.getFrontierSize();
                totalTime += result.getExecutionTime();
            }
        }

        System.out.printf("A* - Time for A*: %.2f ms%n; expanded: %.2f; solution: %.2f; frontier: %.2f%n",
         (double) totalTime / 7, (double) expandedNodes / 7, (double) solutionNodes / 7, (double) frontierNodes / 7);
    }

    @Test
    public void testAverageAStarH2() {

        int expandedNodes = 0;
        int solutionNodes = 0;
        int frontierNodes = 0;
        int totalTime = 0;

        for (Map.Entry<String, Board> entry : maps.entrySet()) {
            Board board = entry.getValue();

            AStar solver = new AStar("h2");
            ResultClass result = solver.getResultClass(board);

            if (result.isFound()) {
                expandedNodes += result.getNodesExpanded();
                solutionNodes += result.getSolutionSize();
                frontierNodes += result.getFrontierSize();
                totalTime += result.getExecutionTime();
            }
        }

        System.out.printf("A* - Time for A*: %.2f ms%n; expanded: %.2f; solution: %.2f; frontier: %.2f%n",
         (double) totalTime / 7, (double) expandedNodes / 7, (double) solutionNodes / 7, (double) frontierNodes / 7);
    }

        @Test
    public void testAverageGreedyH1() {

        int expandedNodes = 0;
        int solutionNodes = 0;
        int frontierNodes = 0;
        int totalTime = 0;

        for (Map.Entry<String, Board> entry : maps.entrySet()) {
            Board board = entry.getValue();

            Greedy solver = new Greedy();
            ResultClass result = solver.getResultClass(board, "h1");

            if (result.isFound()) {
                expandedNodes += result.getNodesExpanded();
                solutionNodes += result.getSolutionSize();
                frontierNodes += result.getFrontierSize();
                totalTime += result.getExecutionTime();
            }
        }

        System.out.printf("Greedy H1 - Time for Greedy: %.2f ms%n; expanded: %.2f; solution: %.2f; frontier: %.2f%n",
         (double) totalTime / 7, (double) expandedNodes / 7, (double) solutionNodes / 7, (double) frontierNodes / 7);
    }

    @Test
    public void testAverageGreedyH2() {

        int expandedNodes = 0;
        int solutionNodes = 0;
        int frontierNodes = 0;
        int totalTime = 0;

        for (Map.Entry<String, Board> entry : maps.entrySet()) {
            Board board = entry.getValue();

            Greedy solver = new Greedy();
            ResultClass result = solver.getResultClass(board, "h2");

            if (result.isFound()) {
                expandedNodes += result.getNodesExpanded();
                solutionNodes += result.getSolutionSize();
                frontierNodes += result.getFrontierSize();
                totalTime += result.getExecutionTime();
            }
        }

        System.out.printf("Greedy H2 - Time for Greedy: %.2f ms%n; expanded: %.2f; solution: %.2f; frontier: %.2f%n",
         (double) totalTime / 7, (double) expandedNodes / 7, (double) solutionNodes / 7, (double) frontierNodes / 7);
    }

    @Test
    public void testAverageIDDFS() {

        int expandedNodes = 0;
        int solutionNodes = 0;
        int frontierNodes = 0;
        int totalTime = 0;

        for (Map.Entry<String, Board> entry : maps.entrySet()) {
            Board board = entry.getValue();

            IDDFS solver = new IDDFS();
            ResultClass result = solver.getResultClass(board);

            if (result.isFound()) {
                expandedNodes += result.getNodesExpanded();
                solutionNodes += result.getSolutionSize();
                frontierNodes += result.getFrontierSize();
                totalTime += result.getExecutionTime();
            }
        }

        System.out.printf("IDDFS - Time for IDDFS: %.2f ms%n; expanded: %.2f; solution: %.2f; frontier: %.2f%n",
         (double) totalTime / 7, (double) expandedNodes / 7, (double) solutionNodes / 7, (double) frontierNodes / 7);
    }

}