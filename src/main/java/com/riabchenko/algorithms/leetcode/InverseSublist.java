package com.riabchenko.algorithms.leetcode;

import com.riabchenko.algorithms.lang.ListNode;

/**
 * Created by driabchenko on 10/13/15.
 */
public class InverseSublist {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode p = new ListNode(0);
        p.next = head;
        head = p;
        if (n - m > 0) {
            int c = 0;
            int r = n - m;
            while (p.next != null && c < m) {
                c ++;
                p = p.next;
            }


            ListNode p2 = p.next;
            p = p.next;
            while (p != null && r > 0) {
                r --;
                ListNode tmp = p.next;
                p.next = p2;
                p2 = p.next;
                p = tmp;
            }
        }

        return head.next;
    }

    public static void main(String... args) {
        test(new int[] {1, 2, 3, 4, 5}, 2, 4);
    }

    public static void test(int[] arr, int m, int n) {
        InverseSublist c = new InverseSublist();
        ListNode head = ListNode.fromArray(arr);
        System.out.print(head.asString());
        head = c.reverseBetween(head, m, n);
        System.out.println("   >>   " + head.asString());
    }
}
