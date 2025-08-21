package org.sokoban.models;

import java.util.ArrayList;
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
        this.width = 7;
        this.height = 6;
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
        board.width = old.width;
        board.height = old.height;
        board.playerX = old.playerX;
        board.playerY = old.playerY;

        board.cells = new Cell[board.height][board.width];

        for(int y = 0; y < board.height; y++) {
            for(int x = 0; x < board.width; x++) {
                State state = old.getCell(x, y).getState();
                board.setCell(x, y, new Cell(state));
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

    public int heuristic() {
        List<int[]> boxes = new ArrayList<>();
        List<int[]> targets = new ArrayList<>();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                State state = cells[y][x].getState();
                if (state == State.BOX || state == State.BOX_ON_TARGET) {
                    boxes.add(new int[]{x, y});
                } else if (state == State.TARGET || state == State.PLAYER_ON_TARGET) {
                    targets.add(new int[]{x, y});
                }
            }
        }

        int totalDistance = 0;
        boolean[] usedTargets = new boolean[targets.size()];

        for (int[] box : boxes) {
            int minDistance = Integer.MAX_VALUE;
            int closestTargetIndex = -1;

            for (int i = 0; i < targets.size(); i++) {
                if (usedTargets[i]) continue;

                int[] target = targets.get(i);
                int distance = Math.abs(box[0] - target[0]) + Math.abs(box[1] - target[1]);

                if (distance < minDistance) {
                    minDistance = distance;
                    closestTargetIndex = i;
                }
            }

            if (closestTargetIndex != -1) {
                usedTargets[closestTargetIndex] = true;
                totalDistance += minDistance;
            }
        }

        return totalDistance;
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

        return playerX == other.playerX && playerY == other.playerY;
    }

    @Override
    public int hashCode() {
        int result = 17;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                result = 31 * result + cells[y][x].getState().hashCode();
            }
        }
        result = 31 * result + playerX;
        result = 31 * result + playerY;
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
