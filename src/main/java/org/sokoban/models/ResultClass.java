package org.sokoban.models;

public class ResultClass {

    private boolean found;
    private int nodesExpanded;
    private int solutionSize;
    private int frontierSize;
    private int maxDepth;
    private long executionTime;

    public ResultClass(boolean found, int nodesExpanded, int solutionSize, int frontierSize, int maxDepth, long executionTime) {
        this.found = found;
        this.nodesExpanded = nodesExpanded;
        this.solutionSize = solutionSize;
        this.frontierSize = frontierSize;
        this.maxDepth = maxDepth;
        this.executionTime = executionTime;
    }

    // Getters and toString() method

    public boolean isFound() {
        return found;
    }

    public int getNodesExpanded() {
        return nodesExpanded;
    }

    public int getSolutionSize() {
        return solutionSize;
    }

    public int getFrontierSize() {
        return frontierSize;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    @Override
    public String toString() {
        return "ResultClass{" +
                "found=" + found +
                ", nodesExpanded=" + nodesExpanded +
                ", solutionSize=" + solutionSize +
                ", frontierSize=" + frontierSize +
                ", maxDepth=" + maxDepth +
                ", executionTime=" + executionTime +
                '}';
    }
}
