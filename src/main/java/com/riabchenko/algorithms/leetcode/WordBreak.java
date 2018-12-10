package com.riabchenko.algorithms.leetcode;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import static com.google.common.truth.Truth.assertThat;

/**
 * https://leetcode.com/problems/word-break/description/
 */
public class WordBreak {
    @Test
    public void test() {
        assertThat(wordBreak("leetcode", Lists.newArrayList("leet", "code"))).isTrue();
        assertThat(wordBreak("abcdef", Lists.newArrayList("abc", "abcd", "def"))).isTrue();
        assertThat(wordBreak("abcdef", Lists.newArrayList("abc", "abcd", "de"))).isFalse();
        assertThat(wordBreak("goalspecial", Lists.newArrayList("go", "goal", "goals", "special"))).isTrue();
        assertThat(wordBreak("bccdbacdbdacddabbaaaadababadad",
                Lists.newArrayList("cbc", "bcda", "adb", "ddca", "bad", "bbb", "dad", "dac", "ba", "aa", "bd", "abab", "bb", "dbda", "cb", "caccc", "d", "dd", "aadb", "cc", "b", "bcc", "bcd", "cd", "cbca", "bbd", "ddd", "dabb", "ab", "acd", "a", "bbcc", "cdcbd", "cada", "dbca", "ac", "abacd", "cba", "cdb", "dbac", "aada", "cdcda", "cdc", "dbc", "dbcb", "bdb", "ddbdd", "cadaa", "ddbc", "babb"))).isTrue();
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        queue.add(0);
        while (!queue.isEmpty()) {
            int start = queue.poll();
            if (start == s.length()) {
                return true;
            }
            int[] dict = new int[wordDict.size()];
            for (int i = start; i < s.length(); i++) {
                boolean found = false;
                char c = s.charAt(i);
                for (int j = 0; j < wordDict.size(); j++) {
                    String w = wordDict.get(j);
                    if (dict[j] == i - start && dict[j] < w.length()) {
                        if (c == w.charAt(dict[j])) {
                            dict[j]++;
                            found = true;
                            if (dict[j] == w.length()) {
                                if (start + dict[j] == s.length()) {
                                    return true;
                                }
                                queue.add(start + dict[j]);
                            }
                        }
                    }
                }
                if (!found) {
                    break;
                }
            }
        }
        return false;
    }
}
