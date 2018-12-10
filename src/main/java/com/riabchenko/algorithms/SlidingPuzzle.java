package com.riabchenko.algorithms;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

import static com.google.common.truth.Truth.assertThat;

/**
 *
 */
public class SlidingPuzzle {
  @Test
  public void test() {
    assertThat(minSteps("123456780")).isEqualTo(0);
    assertThat(minSteps("123456708")).isEqualTo(1);
    assertThat(minSteps("123406758")).isEqualTo(2);
    assertThat(minSteps("123046758")).isEqualTo(3);
    assertThat(minSteps("023146758")).isEqualTo(4);
    assertThat(minSteps("123465708")).isEqualTo(Integer.MAX_VALUE);
  }

  private int minSteps(String board) {
    int n = (int) Math.sqrt(board.length());
    Deque<String> queue = new ArrayDeque<>();
    queue.add(board);
    Set<String> visited = new HashSet<>();
    int level = 0;
    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int l = 0; l < size; l++) {
        board = queue.poll();
        visited.add(board);
        int e = board.length() - 1;
        boolean solved = true;
        for (int k = 0; k < board.length() - 1; k++) {
          char c = board.charAt(k);
          if (c == '0') {
            e = k;
            solved = false;
            break;
          }
          if (c != '0' + k + 1) {
            solved = false;
          }
        }

        if (solved) {
          return level;
        }

        int i = e / n;
        int j = e % n;
        if (i > 0) {
          String newBoard = swap(board, e, (i - 1) * n + j);
          if (!visited.contains(newBoard)) {
            queue.offer(newBoard);
          }
        }
        if (i < n - 1) {
          String newBoard = swap(board, e, (i + 1) * n + j);
          if (!visited.contains(newBoard)) {
            queue.offer(newBoard);
          }
        }
        if (j > 0) {
          String newBoard = swap(board, e, i * n + j - 1);
          if (!visited.contains(newBoard)) {
            queue.offer(newBoard);
          }
        }
        if (j < n - 1) {
          String newBoard = swap(board, e, i * n + j + 1);
          if (!visited.contains(newBoard)) {
            queue.offer(newBoard);
          }
        }
      }
      level++;
    }
    return Integer.MAX_VALUE;
  }

  private String swap(String board, int k, int l) {
    char[] b = board.toCharArray();
     char c = b[k];
     b[k] = b[l];
     b[l] = c;
    return new String(b);
  }
}
