package com.epam.algorithms.leetcode;

import com.epam.algorithms.lang.ListNode;

/**
 * Created by driabchenko on 10/13/15.
 */
public class ReorderList {
    public ListNode reorder(ListNode head) {
        if (head != null && head.next != null) {
            ListNode p1 = head;
            ListNode p2 = head.next;
            int c = 0;
            while (p2 != null) {
                c ++;
                if (c % 2 == 0) {
                    p1 = p1.next;
                }
                p2 = p2.next;
            }

            p2 = p1.next;
            p1.next = null;

            ListNode p2r = null;
            while (p2 != null) {
                ListNode tmp = p2.next;
                p2.next = p2r;
                p2r = p2;
                p2 = tmp;
            }

            p1 = head;
            while (p1 != null) {
                ListNode tmp1 = p1;
                ListNode tmp2 = p2r;
                p1 = p1.next;

                if (p2r != null) {
                    p2r = p2r.next;
                    tmp2.next = p1;
                }

                tmp1.next = tmp2;
            }
        }
        return head;
    }

    public static void main(String... args) {
        test(new int[] {1, 2, 3, 4, 5, 6, 7, 8});
        test(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9});
    }

    public static void test(int[] arr) {
        ReorderList c = new ReorderList();
        ListNode head = ListNode.fromArray(arr);
        System.out.print(head.asString());
        head = c.reorder(head);
        System.out.println("   >>   " + head.asString());
    }
}
