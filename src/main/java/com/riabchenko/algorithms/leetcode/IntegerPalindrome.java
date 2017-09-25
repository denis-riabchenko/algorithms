package com.riabchenko.algorithms.leetcode;

/**
 * Created by driabchenko on 7/21/15.
 */
public class IntegerPalindrome {
    public static void main(String[] args) {
        (new IntegerPalindrome()).execute();
    }

    public void execute() {
        System.out.println(isPalindrome(100021));
    }

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        if (x < 10) {
            return true;
        }
        while (x > 9 && x / (int) Math.pow(10, (int) Math.log10(x)) == x % 10) {
            x %= (int) Math.pow(10, (int) Math.log10(x));
            x /= 10;
        }
        return x < 10;
    }

    public boolean isPalindrome0(int x) {
        if (x < 0) {
            return false;
        }
        if (x < 10) {
            return true;
        }
        int d = 1000000000;
        while (x / d == 0) {
            d /= 10;
        }
        while (x > 0) {
            if (x / d != x % 10) {
                return false;
            }
            x %= d;
            x /= 10;
            d /= 100;
        }
        return true;
    }
}
