package org.sokoban.main;

import java.io.FileWriter;
import java.util.List;

import org.sokoban.algorithms.DFS;
import org.sokoban.models.Board;

public class Main {
    public static void main(String[] args) {

        Board initialBoard = new Board();
        List<Board> solutionPath = new DFS().search(initialBoard);

        System.out.println(solutionPath);

        try{
            FileWriter writer = new FileWriter("dfs_metrics.txt", true);
            writer.write(String.format("DFS Solution Path: \n"));
            for ( int i=0; i< solutionPath.size(); i++ ) {
                writer.write(String.format("Step %d: \n%s", i, solutionPath.get(i)));
            }
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}