package org.sokoban.main;
import org.sokoban.models.Board;

import java.util.*;

public class BFS {
    Queue<Board> frontier = new LinkedList<>();
    List<Board> solution = new ArrayList<>();

    public static void main(String[] args) {
        BFS solver = new BFS();
        List<Board> answer = solver.bfs();

        if(answer==null){
            System.out.println("No solution found");
            return;
        }

        int step=0;
        for(Board b : answer){
            System.out.println("paso" + (step++));
            System.out.println(b);
        }


    }

    private boolean nextStep(Board current, Board parent, List<Board> solutionPath) {
        solution.add(current);

        if(current.isSolution()){
            solution = new ArrayList<>(solutionPath);
        }

        //miro frontera y se a donde moverme
        for (Board move : current.getPossibleBoards()) {
            if(!move.equals(parent)) {
                frontier.add(move);
            }
        }

        Board next = frontier.poll();
        if(next == null){
            return false;
        }

        List<Board> newPath = new ArrayList<>(solutionPath);

        return nextStep(next, current, newPath);
    }

    private List<Board> bfs(){
        Board root = new Board();
        frontier.add(root);

        if(nextStep(root, null, new ArrayList<>())){
            return solution;
        }
        return null;
    }
}

