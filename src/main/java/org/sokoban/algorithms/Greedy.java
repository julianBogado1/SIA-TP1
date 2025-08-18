package org.sokoban.algorithms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class Greedy<T> {
    private List<T> items;
    private Comparator<T> comparator;
    private Predicate<T> constraint;

    public Greedy(List<T> items, Comparator<T> comparator, Predicate<T> constraint){
        this.items = new ArrayList<>(items);
        this.comparator = comparator;
        this.constraint = constraint;
    }

    public List<T> solve() {
        List<T> solution = new ArrayList<>();
        items.sort(comparator);

        for (T item : items) {
            if (constraint.test(item)) {
                solution.add(item);
            }
        }

        return solution;
    }

}
