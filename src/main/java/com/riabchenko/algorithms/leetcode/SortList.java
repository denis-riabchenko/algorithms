package com.riabchenko.algorithms.leetcode;

import com.riabchenko.algorithms.lang.ListNode;

public class SortList {
    public static void main(String[] args) {
//        ListNode list = ListNode.fromArray(new int[] { 5, 2, 4, 3 });
//        System.out.println(list.asString());
//        SortList sr = new SortList();
//        System.out.println(sr.sortList(list).asString());
        ListNode list = ListNode.fromArray(new int[] { 1, 2, 3 });
        System.out.println(removeNthFromEnd(list, 3).asString());
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode p = head;
        while (n >= 0 && p != null) {
            n --;
            p = p.next;
        }
        if (p == null && n == 0) {
            return head.next;
        }
        ListNode h = head;
        while (p != null) {
            h = h.next;
            p = p.next;
        }
        h.next = h.next.next;
        return head;
    }

    public ListNode removeElements(ListNode head, int val) {
        if(head == null) return head;
        while (head != null && head.val == val) {
            head = head.next;
        }
        ListNode node = head;
        while (node != null) {
            if(node.next != null && node.next.val == val) {
                node.next = node.next.next;
            }
        }
        return head;
    }

    public ListNode sortList(ListNode head) {
        int size = 0;
        ListNode h = head;
        while (h != null) {
            size ++;
            h = h.next;
        }
        h = new ListNode(0);
        h.next = head;
        mergeSort(h, size);
        return h.next;
    }

    public void mergeSort(ListNode head, int size) {
        int half = size / 2;
        if (half > 0) {
            ListNode head2 = head.next;
            for (int i = 0; i < half - 1; i ++) {
                head2 = head2.next;
            }

            ListNode h2 = head2.next;
            head2.next = null;

            head2 = new ListNode(0);
            head2.next = h2;

            mergeSort(head, half);
            mergeSort(head2, size - half);

            head.next = mergeTwoLists(head.next, half, head2.next, size - half);
        }
    }

    public ListNode mergeTwoLists(ListNode l1, int s1, ListNode l2, int s2) {
        ListNode head = new ListNode(0);
        ListNode h = head;

        while (s1 > 0 || s2 > 0) {
            if (s1 == 0) {
                h.next = l2;
                s2 = 0;
            } else if (s2 == 0) {
                h.next = l1;
                s1 = 0;
            } else if (l1.val <= l2.val) {
                h.next = l1;
                l1 = l1.next;
                s1 --;
            } else {
                h.next = l2;
                l2 = l2.next;
                s2 --;
            }

            h = h.next;
        }
        return head.next;
    }
}
