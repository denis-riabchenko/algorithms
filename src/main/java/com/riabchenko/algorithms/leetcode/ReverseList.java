package com.riabchenko.algorithms.leetcode;

import com.riabchenko.algorithms.lang.ListNode;
import org.junit.Test;

public class ReverseList {
    @Test
    public void test() {
        reverseList(ListNode.fromArray(new int[] {1, 2, 3, 4, 5}));
    }

    public ListNode reverseList(ListNode head) {
        ListNode h = null;
        while (head != null) {
            ListNode n2 = head.next;
            head.next = h;
            h = head;
            head = n2;
        }
        return h;
    }
}
