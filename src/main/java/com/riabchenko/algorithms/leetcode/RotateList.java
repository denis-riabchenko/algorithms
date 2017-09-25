package com.riabchenko.algorithms.leetcode;

import com.riabchenko.algorithms.lang.ListNode;


/**
 * Created by driabchenko on 10/13/15.
 */
public class RotateList {
    public ListNode rotateRight(ListNode head, int k) {
        if (head != null && head.next != null && k > 0) {
            int c = 1;
            ListNode p = head;
            while (p.next != null) {
                c ++;
                p = p.next;
            }

            if (k % c > 0) {
                c -= k % c;
                ListNode t = head;
                while (c > 1) {
                    c--;
                    t = t.next;
                }
                p.next = head;
                head = t.next;
                t.next = null;
            }
        }
        return head;
    }

    public static void main(String... args) {
        test(new int[] {1, 2, 3}, 2000000000);
        test(new int[] {1, 2, 3, 4, 5}, 2);
        test(new int[] {1, 2}, 1);
        test(new int[] {1, 2}, 2);
    }

    public static void test(int[] arr, int k) {
        RotateList c = new RotateList();
        ListNode head = ListNode.fromArray(arr);
        System.out.print(head.asString());
        head = c.rotateRight(head, k);
        System.out.println("   >>   " + head.asString());
    }
}

