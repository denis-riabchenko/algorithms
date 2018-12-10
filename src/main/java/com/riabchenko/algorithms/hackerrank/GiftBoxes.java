package com.riabchenko.algorithms.hackerrank;

import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static com.google.common.truth.Truth.assertThat;

/**
 *
 */
public class GiftBoxes {
  @Test
  public void test() {
    assertThat(giftBoxes("aaaab",
        "aaaaaabaaabaaabaaaab")).isEqualTo(4);
    assertThat(giftBoxes("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab",
        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab")).isEqualTo(5);
  }

  static int giftBoxes(String g, String c) {
    int boxes = 0;
    BigInteger pattern = prepare(g, g.length());
    while (find(pattern, g.length(), c)) {
      boxes++;
    }
    return boxes;
  }

  private static boolean find(BigInteger pattern, int patternLength, String text) {
    BigInteger window = prepare(text, patternLength);
    if (pattern.equals(window)) {
      return true;
    }

    BigInteger m = BigInteger.valueOf(26);
    int i = patternLength;
    while (i < text.length()) {
      int d = (text.charAt(i) - 'a');
      window = window.multiply(m).add(BigInteger.valueOf(d));
      if (pattern.equals(window)) {
        return true;
      }
      i++;
    }
    return pattern.equals(window);
  }

  private static BigInteger prepare(String s, int length) {
    BigInteger p = BigInteger.ZERO;
    BigInteger m = BigInteger.valueOf(26);
    for (int i = 0; i < length; i++) {
      int d = (s.charAt(i) - 'a');
      p = p.multiply(m).add(BigInteger.valueOf(d));
    }
    return p;
  }

  private static final Scanner scan = new Scanner(System.in);

  public static void main(String[] args) throws IOException {
    BufferedWriter bw = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    int t = Integer.parseInt(scan.nextLine().trim());

    for (int tItr = 0; tItr < t; tItr++) {
      String g = scan.nextLine();

      String c = scan.nextLine();

      int result = giftBoxes(g, c);

      bw.write(String.valueOf(result));
      bw.newLine();
    }

    bw.close();
  }
}
