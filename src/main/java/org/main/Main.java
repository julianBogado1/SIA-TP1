package org.main;

import org.models.PuzzleState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        PuzzleState puzzleState = new PuzzleState();
        BufferedWriter writer = new BufferedWriter(new FileWriter( "src/main/resources/states.txt"));
        writer.write(puzzleState.toString());
        writer.newLine();
        puzzleState.moveRight();
        writer.write(puzzleState.toString());
        writer.newLine();
        puzzleState.moveRight();
        writer.write(puzzleState.toString());
        writer.newLine();
        puzzleState.moveDown();
        writer.write(puzzleState.toString());
        writer.newLine();
        puzzleState.moveDown();
        writer.write(puzzleState.toString());
        writer.newLine();
        puzzleState.moveLeft();
        writer.write(puzzleState.toString());
        writer.newLine();
        puzzleState.moveLeft();
        writer.write(puzzleState.toString());
        writer.newLine();
        puzzleState.moveUp();
        writer.write(puzzleState.toString());
        writer.newLine();
        puzzleState.moveRight();
        writer.write(puzzleState.toString());
        writer.close();
    }
    
}
