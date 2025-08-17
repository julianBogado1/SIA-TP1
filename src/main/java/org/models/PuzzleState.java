package org.models;

import java.util.Arrays;
//cambios para hacer
//un metodo que outputtee todos los estados posibles que se ramifican de un estado dado
//separados por una accion

//eso para poder crear el cjto frontera
//cada vez que se llega a un nodo nuevo, los estados alcanzables desde ese se tienen que agregar a la frontera
// para decidir que nodo de la frontera se selecciona para expandir en cada paso, Fr estara ordenado segun un criterio
// el criterio lo determina el algoritmo de busqueda utilizado


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

    public static PuzzleState fromMatrix(int[][] matrix) {
        if(matrix.length != 3 || matrix[0].length !=3){
            throw new IllegalArgumentException("Matrix must be 3x3.");
        }
        PuzzleState toReturn = new PuzzleState();
        boolean valid = false;
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                toReturn.board[i][j] = matrix[i][j];
                if(matrix[i][j]==-1){
                    toReturn.emptyX = j;
                    toReturn.emptyY = i;
                    valid = true;
                }
            }
        }
        if(!valid){
            throw new IllegalArgumentException("Matrix must contain one empty cell represented by -1.");
        }
        return toReturn;
    }
    public static PuzzleState generateRandomPuzzle(){
        int[][] matrix = new int[3][3];
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                matrix[i][j] = (int) (Math.random()*10);
            }
        }
        int emptyX = (int) (Math.random()*3);
        int emptyY = (int) (Math.random()*3);
        matrix[emptyY][emptyX] = -1;
        return fromMatrix(matrix);
    }


    //=========MOVES==========

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
