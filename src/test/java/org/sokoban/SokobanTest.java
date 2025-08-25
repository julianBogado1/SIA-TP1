package org.sokoban;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.sokoban.algorithms.DFS;
import org.sokoban.algorithms.Greedy;
import org.sokoban.models.Board;
import org.sokoban.models.Cell;
import org.sokoban.models.State;

public class SokobanTest {

    @Test
    public void testDFSSolvesSimpleBoard() {
        Cell[][] level = {
            { new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL) },
            { new Cell(State.WALL), new Cell(State.TARGET), new Cell(State.BOX), new Cell(State.PLAYER), new Cell(State.WALL) },
            { new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL) }
        };
        
        Board board = new Board(5, 3, level, 3, 1);
        List<Board> path = new DFS().search(board);

        assertNotNull(path);
        assertFalse(path.isEmpty());
        assertTrue(path.getLast().isSolution());
    }
    
    @Test
    public void testGreedySolvesSimpleBoard() {
        Cell[][] level = {
            { new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL) },
            { new Cell(State.WALL), new Cell(State.TARGET), new Cell(State.BOX), new Cell(State.PLAYER), new Cell(State.WALL) },
            { new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL) }
        };
        Board board = new Board(5, 3, level, 3, 1);
        List<Board> path = new Greedy(board).search();

        assertNotNull(path);
        assertFalse(path.isEmpty());
        assertTrue(path.getLast().isSolution());
    }

    @Test
    public void testDFSFailsOnImpossibleBoard() {
        Cell[][] level = {
            { new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL) },
            { new Cell(State.WALL), new Cell(State.BOX), new Cell(State.WALL), new Cell(State.TARGET), new Cell(State.WALL) },
            { new Cell(State.WALL), new Cell(State.PLAYER), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL) }
        };
        Board board = new Board(5, 3, level, 2, 1);
        List<Board> path = new DFS().search(board);

        assertTrue(path == null || path.isEmpty());
    }

    @Test
    public void testGreedyFailsOnImpossibleBoard() {
        Cell[][] level = {
            { new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL) },
            { new Cell(State.WALL), new Cell(State.BOX), new Cell(State.WALL), new Cell(State.TARGET), new Cell(State.WALL) },
            { new Cell(State.WALL), new Cell(State.PLAYER), new Cell(State.WALL), new Cell(State.WALL), new Cell(State.WALL) }
        };
        Board board = new Board(5, 3, level, 2, 1);
        List<Board> path = new Greedy(board).search();

        assertTrue(path == null || path.isEmpty());
    }

    @Test
    public void testGreedyFailsHeapBoardTooGreat() {
        Board board = new Board(15, 15, 5);
        List<Board> path = new Greedy(board).search();

        assertTrue(path == null || path.isEmpty());
    }
    
}
