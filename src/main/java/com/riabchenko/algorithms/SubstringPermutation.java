package com.riabchenko.algorithms;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static com.google.common.truth.Truth.assertThat;

/**
 * Check whether any permutation of string A is a substring of string B.
 */
public class SubstringPermutation {
  @Test
  public void test() {
    assertThat(isSubstring("bca", "ab")).isFalse();
    assertThat(isSubstring("bca", "aba")).isFalse();
    assertThat(isSubstring("bca", "aabaccbc")).isFalse();
    assertThat(isSubstring("bca", "bac")).isTrue();
    assertThat(isSubstring("bca", "aabacbc")).isTrue();
  }

  public boolean isSubstring(String a, String b) {
    int n = a.length();
    int m = b.length();
    if (n > m) {
      return false;
    }

    int[] hashA = new int[26];
    for (int i = 0; i < n; i++) {
      int c = a.charAt(i) - 'a';
      hashA[c]++;
    }

    int[] hashB = new int[26];
    for (int i = 0; i < m; i++) {
      int c = b.charAt(i) - 'a';
      hashB[c]++;
    }

    int left = 0;
    int right = m - 1;

    while (right - left > n - 1) {
      int lc = b.charAt(left) - 'a';
      int rc = b.charAt(right) - 'a';

      if (hashB[lc] > hashA[lc]) {
        hashB[lc]--;
        left++;
      } else if (hashB[rc] > hashA[rc]) {
        hashB[rc]--;
        right--;
      } else {
        break;
      }
    }

    if (right - left != n - 1) {
      return false;
    }

    for (int i = 0; i < hashA.length; i++) {
      if (hashA[i] != hashB[i]) {
        return false;
      }
    }
    return true;
  }

//  public boolean isSubstring(String a, String b) {
//    int n = a.length();
//    int m = b.length();
//    if (n > m) {
//      return false;
//    }
//
//    Map<Character, Integer> hashA = new HashMap<>(n);
//    for (int i = 0; i < n; i++) {
//      char c = a.charAt(i);
//      hashA.put(c, hashA.getOrDefault(c, 0) + 1);
//    }
//
//    Map<Character, Integer> hashB = new HashMap<>(m);
//    for (int i = 0; i < m; i++) {
//      char c = b.charAt(i);
//      hashB.put(c, hashB.getOrDefault(c, 0) + 1);
//    }
//
//    int left = 0;
//    int right = m - 1;
//
//    while (right - left > n - 1) {
//      char lc = b.charAt(left);
//      char rc = b.charAt(right);
//
//      if (hashB.get(lc) > hashA.get(lc)) {
//        hashB.put(lc, hashB.get(lc) - 1);
//        left++;
//      } else if (hashB.get(rc) > hashA.get(rc)) {
//        hashB.put(rc, hashB.get(rc) - 1);
//        right--;
//      } else {
//        break;
//      }
//    }
//
//    if (right - left != n - 1) {
//      return false;
//    }
//
//    return hashA.equals(hashB);
//  }
}
