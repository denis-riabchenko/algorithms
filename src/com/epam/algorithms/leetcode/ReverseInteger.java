package com.epam.algorithms.leetcode;

/**
 * Created by driabchenko on 7/19/15.
 */
public class ReverseInteger {
    public static void main(String... args) {
        System.out.println(reverse(-123));
    }

    public static int reverse(int x) {
        final int max = Integer.MAX_VALUE / 10;
        final int min = Integer.MIN_VALUE / 10;
        int rev = 0;
        while (x != 0) {
            if (rev < min || rev > max) {
                return 0;
            }
            rev = rev * 10 + x % 10;
            x /=  10;
        }
        return rev;
    }
}
