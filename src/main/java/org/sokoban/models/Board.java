package org.sokoban.models;

public class Board {
    private int width = 7;
    private int height = 6;

    private int[][] preAnalysis; // number of blocked rows in[1] and number of blocked columns in[0]

//    targets
//    boxes
//    boxesOnTargets == targets

    // Leyenda: # = WALL, T = TARGET, B = BOX, P = PLAYER, ' ' = EMPTY
    /*
     * # # # # # # #
     * # T #   B T #
     * #   #       #
     * # B #   #   #
     * #       P   #
     * # # # # # # #
     */

    // default map
    private Cell[][] cells = {
        { new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL) },
        { new Cell(State.WALL), new Cell(State.TARGET), new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.BOX), new Cell(State.TARGET), new Cell(State.WALL) },
        { new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.WALL) },
        { new Cell(State.WALL), new Cell(State.BOX), new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.WALL) },
        { new Cell(State.WALL), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.EMPTY), new Cell(State.PLAYER), new Cell(State.EMPTY), new Cell(State.WALL) },
        { new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL) }
    };

    public Board() {
        executePreAnalysis();
    }

    public Board(int width, int height, Cell[][] level) {
        this.width = width;
        this.height = height;
        initializeCells(level);
        executePreAnalysis();
    }

    public void executePreAnalysis(){
        preAnalysis = new int[2][Math.max(width, height)];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Cell cell = getCell(j, i);
                if (cell.getState() == State.BOX) {
                    preAnalysis[0][j]++;
                    preAnalysis[1][i]++;
                }
            }
        }
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
}
