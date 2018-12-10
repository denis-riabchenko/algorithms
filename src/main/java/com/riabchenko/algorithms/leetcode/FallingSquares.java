package com.riabchenko.algorithms.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 *  https://leetcode.com/problems/falling-squares/description/
 */
public class FallingSquares {
    @Test
    public void test() {
//        assertThat(fallingSquares(new int[][] {{1, 2}, {2, 3}, {6, 1}})).containsExactly(2, 5, 5).inOrder();
//        assertThat(fallingSquares(new int[][] {{100, 100}, {200, 100}})).containsExactly(100, 100).inOrder();
//        assertThat(fallingSquares(new int[][] {{6, 1}, {9, 2}, {2, 4}})).containsExactly(1, 2, 4).inOrder();
        assertThat(fallingSquares(new int[][] {{2, 1}, {2, 9}, {1, 8}})).containsExactly(1, 10, 18).inOrder();
    }

    private static class IntervalNode {
        final int low;
        final int high;
        int maxHigh;
        int height;
        IntervalNode left;
        IntervalNode right;

        IntervalNode(int low, int size, int height) {
            this.low = low;
            this.height = height;
            high = low + size;
            maxHigh = high;
        }
    }

    public List<Integer> fallingSquares(int[][] positions) {
        List<Integer> resLeft = new ArrayList<>();
        List<Integer> resRight = new ArrayList<>();
        IntervalNode rootLeft = null;
        IntervalNode rootRight = null;
        for (int[] square : positions) {
            rootLeft = insertSquareLeft(rootLeft, square[0], square[1], 0, resLeft);
            rootRight = insertSquareRight(rootRight, square[0], square[1], 0, resRight);
        }
        return resLeft;
    }

    private IntervalNode insertSquareLeft(IntervalNode root, int low, int size, int height, List<Integer> res) {
        if (root == null) {
            height += size;
            if (res.isEmpty()) {
                res.add(height);
            } else {
                res.add(Math.max(height, res.get(res.size() - 1)));
            }
            return new IntervalNode(low, size, height);
        }

        int high = low + size;
        if ((low >= root.low && low < root.high) || (high > root.low && high < root.high)
                || (root.low >= low && root.low < high) || (root.high > low && root.high < high)) {
            height = root.height;
        }
        if (low < root.low || (root.left != null && high < root.left.maxHigh)) {
            root.left = insertSquareLeft(root.left, low, size, height, res);
        } else {
            root.right = insertSquareLeft(root.right, low, size, height, res);
        }
        root.maxHigh = Math.max(high, root.maxHigh);

        return root;
    }

    private IntervalNode insertSquareRight(IntervalNode root, int low, int size, int height, List<Integer> res) {
        if (root == null) {
            height += size;
            if (res.isEmpty()) {
                res.add(height);
            } else {
                res.add(Math.max(height, res.get(res.size() - 1)));
            }
            return new IntervalNode(low, size, height);
        }

        int high = low + size;
        if ((low >= root.low && low < root.high) || (high > root.low && high < root.high)
                || (root.low >= low && root.low < high) || (root.high > low && root.high < high)) {
            height = root.height;
        }
        if (low > root.low || (root.right != null && high < root.left.maxHigh)) {
            root.left = insertSquareRight(root.left, low, size, height, res);
        } else {
            root.right = insertSquareRight(root.right, low, size, height, res);
        }
        root.maxHigh = Math.max(high, root.maxHigh);

        return root;
    }
}
