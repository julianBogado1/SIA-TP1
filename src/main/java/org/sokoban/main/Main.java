package org.sokoban.main;

import org.sokoban.models.Board;
import java.util.List;
import org.sokoban.algorithms.DFS;

public class Main {
    public static void main(String[] args) {

        Board initialBoard = new Board();
        List<Board> solutionPath = new DFS().search(initialBoard);

        System.out.println(solutionPath);

    }
}