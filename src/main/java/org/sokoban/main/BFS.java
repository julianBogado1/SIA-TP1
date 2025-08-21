package org.sokoban.main;

import org.sokoban.models.Board;
import org.sokoban.models.Node;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS {
    Queue<Board> frontier = new LinkedList<Board>();
    Queue<Board> solution = new LinkedList<Board>();
    public static void main(String[] args) {


    }

    private boolean nextStep(Board current){
        if(current.isGoal()){
            solution.add(current);
            return true;
        }
        //miro frontera y se a donde moverme
        boolean result = nextStep();
    }

    private Queue<Board> bfs(){
        Board root = new Board();
        frontier.add(root);

        if(nextStep(root)) return solution;
        return null;
    }
}

