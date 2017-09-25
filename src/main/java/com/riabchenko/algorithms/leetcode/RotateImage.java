package com.riabchenko.algorithms.leetcode;

import java.util.Arrays;

/**
 * Created by driabchenko on 12/8/15.
 * https://leetcode.com/problems/rotate-image/
 */
public class RotateImage {
    public static void main(String[] args) {
        (new RotateImage()).execute();
    }

    public void execute() {
        int[][] matrix = {{1, 2}, {3, 4}};
        print(matrix);
        rotate(matrix);
        print(matrix);
    }

    public void rotate(int[][] matrix) {
        final int n = matrix.length;
        final int[][] directions = {{1, 0}, {-1, 0}};
        for (int j = 0; j <= (n + 1) / 2; j ++) {
            for (int i = j; i < n - j; i ++) {
                int i1 = i;
                int j1 = j;
                int v1 = matrix[j1][i1];
                for (int k = 0; k < 4; k ++) {
                    int i2 = j1 + (n - 1) * directions[k / 2][0];
                    int j2 = i1 + (n - 1) * directions[k / 2][1];
                    int v2 = matrix[j2][i2];
                    matrix[j2][i2] = v1;
                    v1 = v2;
                    i1 = i2;
                    j1 = j2;
                }
            }
        }
    }

    private String toString(int[][] matrix) {
        StringBuilder builder = new StringBuilder();
        for (int j = 0; j < matrix.length; j ++) {
            builder.append(Arrays.toString(matrix[j])).append("\n");
        }
        return builder.toString();
    }

    private void print(int[][] matrix) {
        System.out.println(toString(matrix));
    }
}
