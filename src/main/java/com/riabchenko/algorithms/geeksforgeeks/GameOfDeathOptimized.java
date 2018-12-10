package com.riabchenko.algorithms.geeksforgeeks;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.util.*;

import static com.google.common.truth.Truth.assertThat;

/**
 *  https://practice.geeksforgeeks.org/problems/game-of-death-in-a-circle/0
 */
public class GameOfDeathOptimized {
    private static class Node {
        int from;
        int count;
        Node next;

        Node(int from, int count) {
            this.from = from;
            this.count = count;
        }

        public String toString() {
            Set<Node> visited = new HashSet<>();
            StringBuilder builder = new StringBuilder();
            Node p = this;
            while (p != null && visited.add(p)) {
                if (builder.length() > 0) {
                    builder.append(" -> ");
                }
                builder.append("(").append(p.from).append(", ").append(p.count).append(")");
                p = p.next;
            }
            return builder.toString();
        }

        public static String asString(Node p) {
            Set<Node> visited = new HashSet<>();
            List<Integer> values = new ArrayList<>();
            while (p != null && visited.add(p)) {
                for (int i = 0; i < p.count; i++) {
                    values.add(p.from + i);
                }
                p = p.next;
            }
            Collections.sort(values);
            return Joiner.on(", ").join(values);
        }
    }

    private static int eval(int n, int k) {
        if (n == 1) {
            return 1;
        }
        if (k == 1) {
            return n;
        }
        Node head = new Node(0, 0);
        head.next = new Node(1, n);
        head.next.next = head.next;

        int i = k;
        Node p = head;
        while (p.next != p || p.next.count > 1) {
            Node t = p.next;
            if (i > t.count) { // Skip smaller interval
                i -= t.count;
                p = p.next;
            } else {
                if (t.count == 1) { // Remove whole interval
                    p.next = p.next.next;
                } else {
                    if (i == 1) { // Remove first
                        t.from++;
                        t.count--;
                    } else if (i == t.count) { // Remove last
                        t.count--;
                        p = p.next;
                    } else { // Remove at the middle, split on two intervals
                        Node right = new Node(t.from + i, t.count - i);
                        right.next = t.next;
                        t.next = right;
                        t.count = i - 1;
                        p = p.next;
                    }
                }
                n--;
                i = k % n;
            }
        }

        return p.from;
    }

    @Test
    public void test() {
        assertThat(eval(8, 7)).isEqualTo(4);
        assertThat(eval(8, 3)).isEqualTo(7);
        assertThat(eval(4, 2)).isEqualTo(1);
        assertThat(eval(50, 10)).isEqualTo(36);
        assertThat(eval(95, 94)).isEqualTo(31);

//        for (int n = 1; n <= 200; n++) {
//            for (int k = 1; k <= 200; k++) {
//                eval(n, k);
//            }
//        }
    }
}
