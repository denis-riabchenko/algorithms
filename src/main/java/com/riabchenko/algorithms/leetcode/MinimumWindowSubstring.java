package com.riabchenko.algorithms.leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static com.google.common.truth.Truth.assertThat;

/**
 * https://leetcode.com/problems/minimum-window-substring/description/
 */
public class MinimumWindowSubstring {
    @Test
    public void test() {
        assertThat(minWindow("ADOBECODEBANC", "ABC")).isEqualTo("BANC");
    }

    public String minWindow(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            map.put(t.charAt(i), 0);
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            Integer count = map.get(c);
            if (count != null) {
                map.put(c, count + 1);
            }
        }

        int min = s.length();
        int l = 0;
        int r = s.length() - 1;
        while (l < r) {
            int lc = getCount(map, s.charAt(l));
            int rc = getCount(map, s.charAt(r));
            if (lc > 1 && lc >= rc) {
                l++;
                skip(map, s.charAt(l));
            }
            if (rc > 1 && rc >= lc) {
                r--;
                skip(map, s.charAt(r));
            }
            if (min == r - l + 1) {
                return s.substring(l, r + 1);
            }
            min = r - l + 1;
        }
        return "";
    }

    private int getCount(Map<Character, Integer> map, char c) {
        Integer count = map.get(c);
        if (count == null) {
            return Integer.MAX_VALUE;
        }
        return count;
    }

    private void skip(Map<Character, Integer> map, char c) {
        Integer count = map.get(c);
        if (count != null) {
            map.put(c, count - 1);
        }
    }

}
