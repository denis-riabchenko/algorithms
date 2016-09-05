package com.epam.algorithms.leetcode;

import java.util.BitSet;

/**
 * Created by driabchenko on 10/5/15.
 */
public class UglyNumbers {
    public static void main(String[] args) {
        System.out.println(isUgly(214797179));
    }

    private static final int[] div = {2, 3, 5};
    public static boolean isUgly(int num) {
        for (int d : div) {
            while (num % d == 0) {
                num /= d;
            }
        }
        String s;

        return num == 1;
    }
}
