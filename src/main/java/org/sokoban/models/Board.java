package org.sokoban.models;

import java.util.Arrays;

public class Board {
    private int width;
    private int height;
    private int playerX;
    private int playerY;

    private Cell[][] cells;

    // cells = {  {c00, c01, c02},
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

    private static Board fromBoard(Board old){
        Board board = new Board(old.width, old.height);
        Cell[][] cells = Arrays.copyOf(old.cells, board.width*board.height);

        //Copy cells
        for(int i=0; i<board.width; i++){
            for(int j=0; j<board.height; j++){
                board.setCell(i, j, cells[i][j]);
            }
        }
        return board;
    }

    public Board move(Direction direction){
        Board nextBoard = fromBoard(this);
        int newX = playerX + direction.dx();
        int newY = playerY + direction.dy();
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
        if(cells[playerY][playerX].getState() == State.PLAYER_ON_TARGET) setCell(playerX, playerY, new Cell(State.TARGET));
        else setCell(playerX, playerY, new Cell(State.EMPTY));
        playerX = x;
        playerY = y;
        if(cells[playerY][playerX].getState() == State.TARGET || cells[playerY][playerX].getState() == State.BOX_ON_TARGET) setCell(playerX, playerY, new Cell(State.PLAYER_ON_TARGET));
        else setCell(playerX, playerY, new Cell(State.PLAYER));
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
