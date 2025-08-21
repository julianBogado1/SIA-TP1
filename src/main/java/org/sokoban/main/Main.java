package org.sokoban.main;

import java.io.FileWriter;
import java.util.List;

import org.sokoban.algorithms.Greedy;
import org.sokoban.models.Board;

public class Main {
    public static void main(String[] args) {
        //String filename = "dfs_metrics.txt";
        String filename = "greedy_metrics.txt";
        //String algorithm =  "DFS";
        String algorithm =  "Greedy";

        Board initialBoard = new Board();
        //List<Board> solutionPath = new DFS().search(initialBoard);
        List<Board> solutionPath = new Greedy(initialBoard).search();

        System.out.println(solutionPath);

        try{
            FileWriter writer = new FileWriter(filename, true);
            writer.write(String.format(algorithm + " Solution Path: \n"));
            for ( int i=0; i< solutionPath.size(); i++ ) {
                writer.write(String.format("Step %d: \n%s", i, solutionPath.get(i)));
            }
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}