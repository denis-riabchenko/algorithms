package com.riabchenko.algorithms.geeksforgeeks;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.util.*;

import static com.google.common.truth.Truth.assertThat;

/**
 *  https://practice.geeksforgeeks.org/problems/game-of-death-in-a-circle/0
 */
public class GameOfDeath {
    private static class Node {
        final int val;
        Node next;

        Node(int val) {
            this.val = val;
        }

        public String toString() {
            return asString(this);
        }

        public static String asString(Node p) {
            Set<Node> visited = new HashSet<>();
            List<Integer> values = new ArrayList<>();
            while (p != null && visited.add(p)) {
                values.add(p.val);
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
        Node head = new Node(-1);
        Node tail = head;
        for (int i = 1; i <= n; i++) {
            tail.next = new Node(i);
            tail = tail.next;
        }
        tail.next = head.next;

        int i = 0;
        while (head.next != head) {
            i++;
            if (i % k == 0) {
                head.next = head.next.next;
                System.out.println(head.toString());
            } else {
                head = head.next;
            }
        }

        return head.val;
    }

    @Test
    public void test() {
        assertThat(eval(8, 7)).isEqualTo(4);
//        assertThat(eval(4, 3)).isEqualTo(1);
//        assertThat(eval(8, 3)).isEqualTo(7);
//        assertThat(eval(4, 2)).isEqualTo(1);
//        assertThat(eval(50, 10)).isEqualTo(36);
//        assertThat(eval(95, 94)).isEqualTo(31);
//
//        for (int n = 1; n <= 200; n++) {
//            for (int k = 1; k <= 200; k++) {
//                eval(n, k);
//            }
//        }
    }
}
