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
        Map<Character, Integer> targetMap = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            targetMap.put(c, targetMap.getOrDefault(c, 0) + 1);
        }

        int start = 0;
        int end = 0;
        int[] min = null;
        int count = 0;
        Map<Character, Integer> windowMap = new HashMap<>();
        while (start < s.length() && end <= s.length()) {
            while (!targetMap.containsKey(s.charAt(start))) {
                start++;
            }
            if (end < start) {
                end = start;
            }
            while (end <= s.length() && count < t.length()) {
                char ce = s.charAt(end - 1);
                if (targetMap.containsKey(ce)) {
                    windowMap.put(ce, windowMap.getOrDefault(ce, 0) + 1);
                    count++;
                }
                end++;
            }
            if (count == t.length()) {
                if (min == null) {
                    min = new int[] {start, end};
                } else if (min[1] - min[0] > end - start){
                    min[0] = start;
                    min[1] = end;
                }

                while (start < s.length() && count == t.length()) {
                    char cs = s.charAt(start);
                    Integer sc = windowMap.get(cs);
                    if (sc != null && sc > 0) {
                        windowMap.put(cs, sc - 1);
                        count--;
                    }
                    start++;
                }
                if (end <= start) {
                    end = start + 1;
                }
            }
        }
        if (min != null) {
            return s.substring(min[0], min[1]);
        }
        return "";
    }
}
