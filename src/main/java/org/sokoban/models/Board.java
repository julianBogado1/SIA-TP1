package org.sokoban.models;

import java.util.Arrays;

public class Board {
    private int width = 7;
    private int height = 6;

    private int[][] preAnalysis; // number of blocked rows in[1] and number of blocked columns in[0]

//    targets
//    boxes
//    boxesOnTargets == targets

    private int playerX;
    private int playerY;

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
        this.playerX = 4;
        this.playerY = 4;
        executePreAnalysis();
    }

    public Board(int width, int height, Cell[][] level, int playerX, int playerY) {
        this.width = width;
        this.height = height;
        setPlayer(playerX, playerY);
        initializeCells(level);
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

    private static Board fromBoard(Board old){
        Board board = new Board();
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

    public boolean isGoal(){
        return cells[1][1].getState() == State.BOX_ON_TARGET &&
                cells[1][2].getState() == State.BOX_ON_TARGET;
    }
}
