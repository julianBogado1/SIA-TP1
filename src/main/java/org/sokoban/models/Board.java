package org.sokoban.models;

public class Board {
    private int width;
    private int height;

//    targets
//    boxes
//    boxesOnTargets == targets

    private Cell[][] cells;

    // cells = { {c00, c01, c02},
    //            {c10, c11, c12}
    //            {c20, c21, c22} }

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.cells = new Cell[height][width];
        initializeCells();
    }

    private void initializeCells() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cells[i][j] = new Cell(State.EMPTY); // Assuming State is an enum with EMPTY, WALL, BOX, etc.
            }
        }
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
