package com.riabchenko.algorithms.hackerrank;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class MorganString {
  @Test
  public void test() {
    int v = get();
    v = v;
  }
  int get() {
    int n = 0;
    try {
      n = 1;
      //throw new Exception("asd");
    } catch (Exception ex) {
      return 0;
    } finally {
      return 100;
    }
  }

  static class Morgan {
    StringBuilder str;
    int i = 0;
    int j = 0;
    char[] a;
    char[] b;

    Morgan(String a, String b) {
      this.a = a.toCharArray();
      this.b = b.toCharArray();
      str = new StringBuilder(a.length() + b.length());
      calculate();
    }

    private void calculate() {
      while (i < a.length && j < b.length) {
        if (a[i] < b[j]) {
          str.append(a[i++]);
        } else if (b[j] < a[i]) {
          str.append(b[j++]);
        } else {
          int k = 0;
          while (i + k < a.length && j + k < b.length && a[i + k] == b[j + k]) {
            k++;
          }
          char na = (i + k < a.length) ? a[i + k] : '{';
          char nb = (j + k < b.length) ? b[j + k] : '^';

          if (na <= nb) {
            while (k > 0) {
              if (a[i] < b[j]) {
                str.append(a[i++]);
                k--;
              } else if (b[j] < a[i]) {
                str.append(b[j++]);
              } else {
                str.append(a[i++]);
                k--;
              }
            }
          } else {
            while (k > 0) {
              if (a[i] < b[j]) {
                str.append(a[i++]);
              } else if (b[j] < a[i]) {
                str.append(b[j++]);
                k--;
              } else {
                str.append(b[j++]);
                k--;
              }
            }
          }
        }
      }

      while (i < a.length) {
        str.append(a[i++]);
      }
      while (j < b.length) {
        str.append(b[j++]);
      }
    }

    @Override
    public String toString() {
      return str.toString();
    }
  }

  static String morganAndString(String a, String b) {
    Morgan m = new Morgan(a, b);
    return m.toString();
  }

  public static void main(String[] args) throws IOException {
    try (
        InputStream input = new FileInputStream(new File("case10input.txt"));
        InputStream output = new FileInputStream(new File("case10output.txt"))) {
      Scanner in = new Scanner(input);
      Scanner out = new Scanner(output);
      int t = in.nextInt();
      for (int a0 = 0; a0 < t; a0++) {
        String a = in.next();
        String b = in.next();
        String c = out.next();
        String r = morganAndString(a, b);
        if (!r.equals(c)) {
          int i = 0;
          while (i < r.length() && i < c.length() && r.charAt(i) == c.charAt(i)) {
            i++;
          }
          assertEquals(c, r);
        }
      }
      in.close();
    }
  }
}
