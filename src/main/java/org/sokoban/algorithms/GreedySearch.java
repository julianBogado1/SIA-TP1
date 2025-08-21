package org.sokoban.algorithms;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.sokoban.models.Board;

public class GreedySearch implements SearchAlgorithm<Board> {

    private final TreeSet<Board> frontier = new TreeSet<>(new GreedyComparator());
    private final Set<Board> explored = new HashSet<>();

    public GreedySearch(Board root) {
        frontier.add(root);
    }

    @Override
    public boolean hasNext() {
        return !frontier.isEmpty();
    }

    @Override
    public Board next() {
        Board current = frontier.pollFirst();
        explored.add(current);

        for (Board child : current.getPossibleBoards()) {
        if (!explored.contains(child) && !frontier.contains(child)) {
            frontier.add(child);
        }
    }

        return current;
    }
}

class GreedyComparator implements Comparator<Board> {
    @Override
    public int compare(Board b1, Board b2) {
        int h1 = b1.heuristic();
        int h2 = b2.heuristic();

        if (h1 == h2) return System.identityHashCode(b1) - System.identityHashCode(b2);
        return Integer.compare(h1, h2);
    }
}