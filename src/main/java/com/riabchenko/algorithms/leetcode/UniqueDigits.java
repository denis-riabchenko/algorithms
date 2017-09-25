package com.riabchenko.algorithms.leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class UniqueDigits {
    @Test
    public void test() {
        assertEquals(1, countNumbersWithUniqueDigits(0));
        assertEquals(10, countNumbersWithUniqueDigits(1));
        assertEquals(91, countNumbersWithUniqueDigits(2));
        assertEquals(739, countNumbersWithUniqueDigits(3));
    }

    public int countNumbersWithUniqueDigits(int n) {
        int res = 0;
        int[] dp = new int[11];
        dp[0] = 1;
        for (int i = 0; i <= n && i < 10; i++) {
            if (i > 0) {
                dp[i] = dp[i - 1] * (10 - i + 1);
                res -= dp[i] / 10;
            }
            res += dp[i];
        }
        return res;
    }
}
