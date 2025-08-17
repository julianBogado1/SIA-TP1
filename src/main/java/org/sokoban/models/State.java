package org.sokoban.models;

public enum State {
    EMPTY(' '),
    WALL('#'),
    BOX('B'),
    TARGET('T'),
    BOX_ON_TARGET('X'),
    PLAYER_ON_TARGET('Y'),
    PLAYER('P');

    private final char symbol;

    State(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }
}
