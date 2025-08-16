package org.models;

import java.util.Arrays;

public class PuzzleState {
    private int[][] board = new int[][]{
        {-1, 0, 0},
        {0, 0, 0},     //negative represents an empty cell
        {0, 0, 0}
    };
    private int emptyX = 0; // x-coordinate of the empty cell
    private int emptyY = 0; // y-coordinate of the empty cell

    public int[][] getBoard() {
        return board;
    }
    public void setBoard(int[][] board) {
        this.board = board;
    }

    public PuzzleState moveDown(){
        if(board[2][0]==-1 || board[2][1]==-1 || board[2][2]==-1){
            System.out.println("Cannot move down, empty cell is already at the bottom row.");
            return this;
        }
        board[emptyY][emptyX] = board[emptyY+1][emptyX]; // Move the value from the cell above the empty cell to the empty cell
        emptyY++;
        board[emptyY][emptyX]=-1;
        return this;
    }
    public PuzzleState moveUp(){
        if(board[0][0]==-1 || board[0][1]==-1 || board[0][2]==-1){
            System.out.println("Cannot move up, empty cell is already at the top row.");
            return this;
        }
        board[emptyY][emptyX] = board[emptyY-1][emptyX]; // Move the value from the cell below the empty cell to the empty cell
        emptyY--;
        board[emptyY][emptyX]=-1;
        return this;
    }
    public PuzzleState moveLeft(){
        if(board[0][0]==-1 || board[1][0]==-1 || board[2][0]==-1){
            System.out.println("Cannot move left, empty cell is already at the leftmost column.");
            return this;
        }
        board[emptyY][emptyX] = board[emptyY][emptyX-1]; // Move the value from the cell to the right of the empty cell to the empty cell
        emptyX--;
        board[emptyY][emptyX]=-1;
        return this;
    }
    public PuzzleState moveRight(){
        if(board[0][2]==-1 || board[1][2]==-1 || board[2][2]==-1){
            System.out.println("Cannot move right, empty cell is already at the rightmost column.");
            return this;
        }
        board[emptyY][emptyX] = board[emptyY][emptyX+1]; // Move the value from the cell to the left of the empty cell to the empty cell
        emptyX++;
        board[emptyY][emptyX]=-1;
        return this;
    }




    //=========TRASH==========

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(board);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof PuzzleState)) return false;
        PuzzleState other = (PuzzleState) obj;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != other.board[i][j]) return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int[] row : board) {
            for (int cell : row) {
                sb.append(cell).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
