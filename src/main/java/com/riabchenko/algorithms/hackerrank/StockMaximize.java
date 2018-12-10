package com.riabchenko.algorithms.hackerrank;

import org.junit.Test;

import java.util.Scanner;

import static com.google.common.truth.Truth.assertThat;

/**
 * https://www.hackerrank.com/challenges/stockmax/problem
 */
public class StockMaximize {
    @Test
    public void test() {
        assertThat(maxProfit(1, 2, 5, 3, 10)).isEqualTo(29);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            int[] prices = new int[scanner.nextInt()];
            for (int j = 0; j < prices.length; j++) {
                prices[j] = scanner.nextInt();
            }
            System.out.println(maxProfit(prices));
        }
    }

    private static long maxProfit(int... prices) {
        long max = prices[prices.length - 1];
        long profit = 0L;
        for (int i = prices.length - 2; i >= 0; i--) {
            if (prices[i] < max) {
                profit += max - prices[i];
            } else {
                max = prices[i];
            }
        }
        return profit;
    }
}
