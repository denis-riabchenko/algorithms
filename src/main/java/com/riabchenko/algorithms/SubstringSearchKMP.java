package com.riabchenko.algorithms;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

/**
 *
 */
public class SubstringSearchKMP {
  @Test
  public void test() {
    assertThat(find("aaab", "abaabaaabb")).isEqualTo(5);
    assertThat(find("aaab", "abaabaabb")).isEqualTo(-1);
  }

  private int find(String pattern, String text) {
    return find(pattern, prepare(pattern), text);
  }

  private int find(String pattern, int[] p, String text) {
    for (int i = 0, j = 0; i < text.length();) {
      if (pattern.charAt(j) != text.charAt(i)) {
        if (j == 0) {
          i++;
        } else {
          j = p[j - 1];
        }
      } else {
        if (j == p.length - 1) {
          return i - j;
        }
        i++;
        j++;
      }
    }
    return -1;
  }

  private int[] prepare(String pattern) {
    int[] p = new int[pattern.length()];
    for (int j = 0, i = 1; i < pattern.length();) {
      if (pattern.charAt(j) == pattern.charAt(i)) {
        p[i] = j + 1;
        i++;
        j++;
      } else if (j > 0) {
        j = p[j - 1];
      } else {
        i++;
      }
    }
    return p;
  }
}
