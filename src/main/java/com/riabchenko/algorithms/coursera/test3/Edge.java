package com.riabchenko.algorithms.coursera.test3;

/**
 * Created by driabchenko on 8/2/15.
 */
public class Edge {
    private int left;
    private int right;

    public Edge(int left, int right) {
        if (left <= right) {
            this.left = left;
            this.right = right;
        } else {
            this.left = right;
            this.right = left;
        }
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public boolean isLoop() {
        return left == right;
    }
}
