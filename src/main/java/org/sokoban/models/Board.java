package org.sokoban.models;

import java.util.Arrays;

public class Board {
    private int width;
    private int height;
    private int playerX;
    private int playerY;

    private Cell[][] cells;

    // cells = { {c00, c01, c02},
    //            {c10, c11, c12}
    //            {c20, c21, c22} }

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.cells = new Cell[height][width];
        playerX = width/2;
        playerY = height/2;
        initializeCells();
        cells[playerY][playerX] = new Cell(State.PLAYER);

    }

    private void initializeCells() {
        for (int i = 0; i < height-1; i++) {
            for (int j = 0; j < width; j++) {
                if(i==0 || i==height-1 || j==0 || j==width-1) {
                    cells[i][j] = new Cell(State.WALL);
                }
                else{
                    cells[i][j] = new Cell(State.EMPTY); // Initialize all cells to EMPTY state
                }
            }

        }
    }

    private static fromBoard(Board old){
        Board board = new Board(old.width, old.height);
        Cell[][] cells = Arrays.copyOf(old.cells, board.width*board.height);

        //Copy cells
        for(int i=0; i<board.width; i++){
            for(int j=0; j<board.height; j++){
                board.setCell(i, j, cells[i][j]);
            }
        }
    }

    Board nextMove = board.moveLeft();

    public Board moveLeft(){
        if(cells[playerY][playerX-1].getState() == State.WALL){
            //no se mueve
        }
        else if(){

        }

        return ;
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
