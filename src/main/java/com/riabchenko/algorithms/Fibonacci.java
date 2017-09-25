package com.riabchenko.algorithms;

import java.math.BigInteger;

/**
 * Created by driabchenko on 11/16/15.
 */
public class Fibonacci {
    public static void main(String... args) {
        for (int i = 85; i < 100; i ++) {
            System.out.println(fibonacci(i) + " - " + fibonacciLastNum(i));
        }
    }

    private static BigInteger fibonacci(int n) {
        BigInteger f0 = new BigInteger("1");
        BigInteger f1 = new BigInteger("1");

        for (int i = 2; i < n; i ++) {
            BigInteger f = f1;
            f1 = f1.add(f0);
            f0 = f;
        }

        return f1;
    }

    private static int fibonacciLastNum(int n) {
        int f0 = 1;
        int f1 = 1;

        for (int i = 2; i < n; i ++) {
            int f = f1;
            f1 = (f1 + f0) % 10;
            f0 = f;
        }

        return f1;
    }
}
