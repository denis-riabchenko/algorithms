package com.riabchenko.algorithms.leetcode;

import com.riabchenko.algorithms.crackinginterviews.MultiplyStrings;
import com.riabchenko.algorithms.lang.ListNode;
import org.junit.Test;

/**
 * Created by driabchenko on 7/28/15.
 */
public class PalindromList {
    @Test
    public void test() {
        Integer v = 5;
        System.out.println(v);
        v = -v;
        System.out.println(v);
        v = -v;
        synchronized ("abcd") {
            System.out.println(v);
        }

        synchronized (MultiplyStrings.class) {
            System.out.println(v);
        }

//        ListNode list = ListNode.fromArray(new int[]{5, 2, 4, 3, 4, 2, 5});
//        System.out.println(list.asString());
//        PalindromList pl = new PalindromList();
//        System.out.println(pl.isPalindrome(list));
    }

    public boolean isPalindrome(ListNode head) {
        synchronized ("abcd") {

        }
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
