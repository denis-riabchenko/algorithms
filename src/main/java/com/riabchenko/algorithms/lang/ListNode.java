package com.riabchenko.algorithms.lang;

public class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int x) { val = x; }

    public static ListNode fromArray(int[] values) {
        ListNode head = new ListNode(0);
        if (values != null && values.length > 0) {
            ListNode node = head;
            for (int v : values) {
                node = node.next = new ListNode(v);
            }
        }
        return head.next;
    }

    public String asString() {
        return asString(this);
    }

    public String toString() {
        return Integer.toString(val);
    }

    public static String asString(ListNode head) {
        if (head == null) {
            return null;
        }

        StringBuilder result = new StringBuilder();
        int counter = 0;
        ListNode l = head;
        while (l != null && counter < 100) {
            if (l.next == null) {
                result.append(l.val);
            } else {
                result.append(l.val).append(" -> ");
            }
            l = l.next;
            counter ++;
        }
        return result.toString();
    }
}
