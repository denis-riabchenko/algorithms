package com.riabchenko.algorithms.leetcode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 *
 */
@RunWith(JUnit4.class)
public class DeleteOperationsForTwoStrings {
    @Test
    public void test() {
        //addPermuations(3, "sea".toCharArray(), 0, new StringBuilder(), new HashSet<>());
        assertEquals(2, minDistance("sea", "eat"));
        assertEquals(4, minDistance("sea", "ate"));
        assertEquals(2, minDistance("sea", "sat"));
        assertEquals(2, minDistance("seat", "sate"));
        assertEquals(11, minDistance("dinitrophenylhydrazine", "acetylphenylhydrazine"));
        assertEquals(7, minDistance("food", "money"));
    }

    private int minDistance(String word1, String word2) {
        if (word1.isEmpty() || word2.isEmpty()) {
            return word1.length() + word2.length();
        }
        int[][] dp = new int[word2.length()][word1.length()];
        for (int j = 0; j < word2.length(); j++) {
            for (int i = 0; i < word1.length(); i++) {
                int s = 0;
                if (i > 0 && j > 0) {
                    s = dp[j - 1][i - 1];
                }
                if (word1.charAt(i) == word2.charAt(j)) {
                    s++;
                }
                if (i > 0) {
                    s = Math.max(s, dp[j][i - 1]);
                }
                if (j > 0) {
                    s = Math.max(s, dp[j - 1][i]);
                }
                dp[j][i] = s;
            }
        }
        return word1.length() + word2.length() - 2 * dp[word2.length() - 1][word1.length() - 1];
    }
}
