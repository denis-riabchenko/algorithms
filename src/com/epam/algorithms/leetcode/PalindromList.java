package com.epam.algorithms.leetcode;

import com.epam.algorithms.lang.ListNode;

/**
 * Created by driabchenko on 7/28/15.
 */
public class PalindromList {
    public static void main(String[] args) {
        (new PalindromList()).execute();
    }

    public void execute() {
        ListNode list = ListNode.fromArray(new int[]{5, 1, 4, 3, 4, 2, 5});
        System.out.println(list.asString());
        PalindromList pl = new PalindromList();
        System.out.println(pl.isPalindrome(list));
    }

    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        ListNode reversed = null;
        ListNode half = head;
        ListNode tmp;
        int size = 0;
        do {
            head = head.next;
            if (size % 2 == 0) {
                tmp = half;
                half = half.next;

                if (head != null) {
                    tmp.next = reversed;
                    reversed = tmp;
                }
            }
            size ++;
        } while (head != null);

        while (reversed != null || half != null) {
            if (reversed == null || half == null || reversed.val != half.val) {
                return false;
            }
            reversed = reversed.next;
            half = half.next;
        }

        return true;
    }
}
