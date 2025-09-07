package org.sokoban.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Board {
    private int width = -1;
    private int height = -1;

    private int[][] preAnalysis; // number of blocked rows in[1] and number of blocked columns in[0]

//    targets
//    boxes
//    boxesOnTargets == targets

    private int playerX = -1;
    private int playerY = -1;

    // Leyenda: # = WALL, T = TARGET, B = BOX, P = PLAYER, ' ' = EMPTY
    /*
     * # # # # # # #
     * # T # T B   #
     * #   #   #   #
     * # B #   #   #
     * #       P   #
     * # # # # # # #
     */




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

        

        /*
         * Default map layout
         * # # # # # # # # #
         * # T # T B       #
         * #   #   #   #   #
         * # B #   #   #   #
         * #       P       #
         * # T # #   # # # #
         * #   B         B #
         * #           #   #
         * # # # # # # # # #   
         */

    private  Cell[][] cells = {
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

    // default map
    private static Cell[][] cells2 = {
        { new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL) },
        { new Cell(State.WALL), new Cell(State.TARGET), new Cell(State.WALL), new Cell(State.TARGET), new Cell(State.BOX), new Cell(State.EMPTY), new Cell(State.WALL) },
        { new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.WALL) },
        { new Cell(State.WALL), new Cell(State.BOX), new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.WALL) },
        { new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.PLAYER), new Cell(State.EMPTY), new Cell(State.WALL) },
        { new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL) }
    };


    public Board() {
        this.width = 9;
        this.height = 9;
        this.playerX = 4;
        this.playerY = 4;
        executePreAnalysis();
    }
/*
    public Board(String map){
        Board board = maps.get(map);
        this.width = board.getWidth();
        this.height = board.getHeight();
        this.playerX = board.getPlayerX();
        this.playerY = board.getPlayerY();
        this.cells = new Cell[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                this.cells[y][x] = new Cell(board.cells[y][x].getState());
            }
        }
        executePreAnalysis();
    }*/

    public Board(int width, int height, Cell[][] level, int playerX, int playerY) {
        this.width = width;
        this.height = height;
        this.playerX = playerX;
        this.playerY = playerY;
        initializeCells(level);
        executePreAnalysis();
    }

    // Constructor for generating a random board
    public Board(int width, int height, int targets) {
        this.width = width;
        this.height = height;
        Cell[][] randomBoard = generateBoard(width, height, targets);
        initializeCells(randomBoard);
        executePreAnalysis();
    }

    public void executePreAnalysis(){
        preAnalysis = new int[2][Math.max(width, height)];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Cell cell = getCell(j, i);
                if (cell.getState() == State.WALL) {
                    preAnalysis[0][j]++;
                    preAnalysis[1][i]++;
                }
            }
        }
    }
    private Board(Board other) {
        this.width   = other.width;
        this.height  = other.height;
        this.playerX = other.playerX;
        this.playerY = other.playerY;

        this.cells = new Cell[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                this.cells[y][x] = new Cell(other.cells[y][x].getState());
            }
        }
        this.preAnalysis = other.preAnalysis;
    }

    private static Board fromBoard(Board old){
        return new Board(old);
    }

    private boolean isInsideBoard(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public Board move(Direction direction){
        Board nextBoard = fromBoard(this);
        int newX = playerX + direction.dx();
        int newY = playerY + direction.dy();
        if (!isInsideBoard(newX, newY)) return nextBoard;
        if(cells[newY][newX].getState() != State.WALL){
            if(cells[newY][newX].getState() == State.BOX || cells[newY][newX].getState() == State.BOX_ON_TARGET){
                if (!nextBoard.moveBox(direction, newX, newY)) return nextBoard;
            }
            nextBoard.setPlayer(newX, newY);
        }
        return nextBoard;
    }

    private boolean moveBox(Direction direction, int x, int y){
        State previous;
        if(cells[y][x].getState() == State.BOX) previous = State.EMPTY;
        else previous = State.TARGET;
        int newX = x + direction.dx();
        int newY = y + direction.dy();
        if (!isInsideBoard(newX, newY)) return false;
        switch (cells[newY][newX].getState()) {
            case EMPTY -> {
                setCell(newX, newY, new Cell(State.BOX));
                setCell(x, y, new Cell(previous));
                return true;
            }
            case TARGET -> {
                setCell(newX, newY, new Cell(State.BOX_ON_TARGET));
                setCell(x, y, new Cell(previous));
                return true;
            }
            default -> {
                return false;
            }
        }
    }

    public void setPlayer(int x, int y){
        if(playerY!=-1 && playerX!=-1){
            if(cells[playerY][playerX].getState() == State.PLAYER_ON_TARGET) setCell(playerX, playerY, new Cell(State.TARGET));
            else setCell(playerX, playerY, new Cell(State.EMPTY));
        }
        playerX = x;
        playerY = y;
        if(cells[playerY][playerX].getState() == State.TARGET || cells[playerY][playerX].getState() == State.BOX_ON_TARGET) setCell(playerX, playerY, new Cell(State.PLAYER_ON_TARGET));
        else setCell(playerX, playerY, new Cell(State.PLAYER));
    }

    public boolean isSolution() {
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                State s = cell.getState();
                if (s == State.TARGET || s == State.PLAYER_ON_TARGET) {
                    return false;
                }
            }
        }
        return true;
    }

    public List<Board> getPossibleBoards(){
        List<Board> possibleBoards = new ArrayList<>();
        for (Direction dir : Direction.values()) {
            Board nextBoard = move(dir);
            if (!this.equals(nextBoard)) {
                possibleBoards.add(nextBoard);
            }
        }
        return possibleBoards;
    }

        /** Corrige el recolector: incluye también BOX_ON_TARGET como objetivo.
     *  Así, #boxes == #targets en tableros válidos y h(goal) = 0. */
    private void getBoxesAndTargets(List<int[]> boxes, List<int[]> targets) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                State s = cells[y][x].getState();
                // Todas las cajas (incluidas sobre objetivo)
                if (s == State.BOX || s == State.BOX_ON_TARGET) {
                    boxes.add(new int[]{x, y});
                }
                // TODOS los objetivos: vacíos, con jugador o con caja encima
                if (s == State.TARGET || s == State.PLAYER_ON_TARGET || s == State.BOX_ON_TARGET) {
                    targets.add(new int[]{x, y});
                }
            }
        }
    }

    // h1: Suma por caja de la distancia Manhattan al objetivo más cercano (no exclusivo) — ADMISIBLE
    public int h1PerBoxMinManhattan() {
        List<int[]> boxes = new ArrayList<>();
        List<int[]> targets = new ArrayList<>();
        getBoxesAndTargets(boxes, targets);

        int total = 0;
        for (int[] box : boxes) {
            int best = Integer.MAX_VALUE;
            for (int[] t : targets) {
                int d = Math.abs(box[0] - t[0]) + Math.abs(box[1] - t[1]);
                if (d < best) best = d;
            }
            total += (best == Integer.MAX_VALUE ? 0 : best);
        }
        return total;
    }

    // ===== Helpers comunes =====
    private static int manhattan(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    /** Cota admisible "débil": por caja, Manhattan al objetivo más cercano (NO exclusivo). */
    private int perBoxMinManhattan(List<int[]> boxes, List<int[]> targets) {
        if (boxes.isEmpty() || targets.isEmpty()) return 0;
        int sum = 0;
        for (int[] b : boxes) {
            int best = Integer.MAX_VALUE;
            for (int[] t : targets) {
                int d = manhattan(b[0], b[1], t[0], t[1]);
                if (d < best) best = d;
            }
            sum += (best == Integer.MAX_VALUE ? 0 : best);
        }
        return sum;
    }

    // ===== h2: Hungarian con Manhattan (ADMISIBLE y más informativa) =====
    public int h2ManhattanHungarian() {
        List<int[]> boxes = new ArrayList<>();
        List<int[]> targets = new ArrayList<>();
        getBoxesAndTargets(boxes, targets);

        // Caso trivial
        if (boxes.isEmpty()) return 0;

        // Robustez: si hay desbalance por cualquier razón, caemos a una cota admisible segura
        if (boxes.size() != targets.size()) {
            // Idealmente N cajas == N objetivos. Si no, devolvemos una cota admisible más débil.
            return perBoxMinManhattan(boxes, targets);
        }

        final int n = boxes.size();
        int[][] cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            int[] b = boxes.get(i);
            for (int j = 0; j < n; j++) {
                int[] t = targets.get(j);
                cost[i][j] = manhattan(b[0], b[1], t[0], t[1]);
            }
        }
        return hungarianMinCost(cost);
    }

    /** Hungarian clásico para matriz cuadrada de costos mínimos. Devuelve suma mínima. */
    private int hungarianMinCost(int[][] a) {
        int n = a.length;
        int[] u = new int[n + 1];
        int[] v = new int[n + 1];
        int[] p = new int[n + 1];
        int[] way = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            p[0] = i;
            int j0 = 0;
            int[] minv = new int[n + 1];
            boolean[] used = new boolean[n + 1];
            Arrays.fill(minv, Integer.MAX_VALUE);

            do {
                used[j0] = true;
                int i0 = p[j0], delta = Integer.MAX_VALUE, j1 = 0;
                for (int j = 1; j <= n; j++) {
                    if (used[j]) continue;
                    int cur = a[i0 - 1][j - 1] - u[i0] - v[j];
                    if (cur < minv[j]) { minv[j] = cur; way[j] = j0; }
                    if (minv[j] < delta) { delta = minv[j]; j1 = j; }
                }
                for (int j = 0; j <= n; j++) {
                    if (used[j]) { u[p[j]] += delta; v[j] -= delta; }
                    else { minv[j] -= delta; }
                }
                j0 = j1;
            } while (p[j0] != 0);

            do {
                int j1 = way[j0];
                p[j0] = p[j1];
                j0 = j1;
            } while (j0 != 0);
        }

        int result = 0;
        for (int j = 1; j <= n; j++) {
            result += a[p[j] - 1][j - 1];
        }
        return result;
    }

    public int admisibleHeuristic(){
        int resp = 0;
        for(int i = 0; i< cells.length; i++) {
            for(int j = 0; j< cells[i].length; j++) {
                if(cells[i][j].getState() == State.TARGET || cells[i][j].getState() == State.PLAYER_ON_TARGET) {
                    resp++;
                }
            }
        }
        return resp;
    }

    private Cell[][] generateBoard(int width, int height, int targets) {
        Cell[][] board = new Cell[height][width];

        // Initialize all cells as empty first
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                board[i][j] = new Cell(State.EMPTY);
            }
        }

        // Create border walls
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                if (i == 0 || i == height - 1 || j == 0 || j == width - 1) {
                    board[i][j] = new Cell(State.WALL);
                }
            }
        }

        // Generate solvable configuration by starting from solved state
        java.util.List<int[]> validPositions = new java.util.ArrayList<>();
        // Use inner area (at least 2 cells away from walls) to prevent boxes near walls
        for(int i = 3; i < height - 3; i++) {
            for(int j = 3; j < width - 3; j++) {
                validPositions.add(new int[]{i, j});
            }
        }

//        if(validPositions.size() < targets + 1) {
//            // Fallback for small boards
//            return generateSimpleBoard(width, height, targets);
//        }

        java.util.Collections.shuffle(validPositions);

        // Start by placing targets first
        for(int t = 0; t < targets && t < validPositions.size(); t++) {
            int[] pos = validPositions.get(t);
            board[pos[0]][pos[1]] = new Cell(State.TARGET);
        }

        // Place player away from the targets
        int playerIndex = targets;
        if(playerIndex < validPositions.size()) {
            int[] playerPos = validPositions.get(playerIndex);
            board[playerPos[0]][playerPos[1]] = new Cell(State.PLAYER);
            this.playerX = playerPos[1];
            this.playerY = playerPos[0];
        }

        // Now place boxes in different positions from targets
        java.util.Random random = new java.util.Random();
        int boxesPlaced = 0;
        int attempts = 0;
        int maxAttempts = validPositions.size() * 2;

        while(boxesPlaced < targets && attempts < maxAttempts) {
            attempts++;
            int randomIndex = random.nextInt(validPositions.size());
            int[] pos = validPositions.get(randomIndex);

            // Only place box if position is empty and valid for boxes
            if(board[pos[0]][pos[1]].getState() == State.EMPTY &&
               isValidBoxPosition(board, pos[0], pos[1], height, width)) {
                board[pos[0]][pos[1]] = new Cell(State.BOX);
                boxesPlaced++;
            }
        }

        // Add minimal interior walls to increase difficulty, but ensure they don't block boxes
        int wallsToAdd = Math.min(1, validPositions.size() / 15); // Even fewer walls
        for(int w = 0; w < wallsToAdd; w++) {
            int wallIndex = targets + 1 + w;
            if(wallIndex < validPositions.size()) {
                int[] wallPos = validPositions.get(wallIndex);
                if(board[wallPos[0]][wallPos[1]].getState() == State.EMPTY &&
                   !wouldBlockBoxes(board, wallPos[0], wallPos[1], height, width)) {
                    board[wallPos[0]][wallPos[1]] = new Cell(State.WALL);
                }
            }
        }

        return board;
    }

    // Helper method to check if a position is valid for a box (not adjacent to walls)
    private boolean isValidBoxPosition(Cell[][] board, int row, int col, int height, int width) {
        // Check bounds
        if(row <= 1 || row >= height-2 || col <= 1 || col >= width-2) {
            return false;
        }

        // Check if position is empty
        if(board[row][col].getState() != State.EMPTY) {
            return false;
        }

        // Check that none of the adjacent cells are walls (except border)
        int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

        for(int i = 0; i < 8; i++) {
            int adjRow = row + dr[i];
            int adjCol = col + dc[i];

            if(adjRow >= 0 && adjRow < height && adjCol >= 0 && adjCol < width) {
                // Don't allow boxes next to interior walls
                if(board[adjRow][adjCol].getState() == State.WALL &&
                   !(adjRow == 0 || adjRow == height-1 || adjCol == 0 || adjCol == width-1)) {
                    return false;
                }
            }
        }

        return true;
    }

    // Helper method to check if placing a wall would block existing boxes
    private boolean wouldBlockBoxes(Cell[][] board, int row, int col, int height, int width) {
        // Check if placing a wall here would make any adjacent box unmovable
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        for(int i = 0; i < 4; i++) {
            int adjRow = row + dr[i];
            int adjCol = col + dc[i];

            if(adjRow >= 0 && adjRow < height && adjCol >= 0 && adjCol < width) {
                State adjState = board[adjRow][adjCol].getState();
                if(adjState == State.BOX || adjState == State.BOX_ON_TARGET) {
                    // Check if this box would become unmovable
                    int movableDirections = 0;
                    for(int j = 0; j < 4; j++) {
                        int boxMoveRow = adjRow + dr[j];
                        int boxMoveCol = adjCol + dc[j];

                        if(boxMoveRow >= 0 && boxMoveRow < height && boxMoveCol >= 0 && boxMoveCol < width) {
                            State moveState = board[boxMoveRow][boxMoveCol].getState();
                            // Simulate placing the wall
                            if(boxMoveRow == row && boxMoveCol == col) {
                                continue; // This would be a wall
                            }
                            if(moveState == State.EMPTY || moveState == State.TARGET) {
                                movableDirections++;
                            }
                        }
                    }
                    if(movableDirections == 0) {
                        return true; // Would block this box
                    }
                }
            }
        }

        return false;
    }

    public void initializeCells(Cell[][] level) {
        cells = level;
    }

    public Cell getCell(int x, int y) {
        return cells[y][x];
    }

    public void setCell(int x, int y, Cell cell) {
        cells[y][x] = cell;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getPlayerX() {
        return playerX;
    }
    public int getPlayerY() {
        return playerY;
    }

    public Cell[][] getCells() {
        return cells;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Board other = (Board) obj;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (this.cells[y][x].getState() != other.cells[y][x].getState()) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 17;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                result = 31 * result + cells[y][x].getState().hashCode();
            }
        }

        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                sb.append(cells[y][x].getState().getSymbol());
            }
            sb.append('\n');
        }
        return sb.toString();
    }

}

class Initializer{
    public static void main(String[] args) {
        Board board = new Board(8, 8, 5);
        System.out.println(board);
    }
}
