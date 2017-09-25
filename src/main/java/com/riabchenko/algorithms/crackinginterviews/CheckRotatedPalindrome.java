package com.riabchenko.algorithms.crackinginterviews;

import com.riabchenko.algorithms.lang.AbstractTest;

/**
 * https://www.youtube.com/watch?v=YSIu6DzJsy8&list=PLlhDxqlV_-vkak9feCSrnjlrnzzzcopSG&index=42
 */
public class CheckRotatedPalindrome extends AbstractTest {
    @Override
    public void test() throws Exception {
        String str = "2112343";
        print("%s is %spalindrome", str, isPalindrome(str) ? "" : "not ");
    }

    public boolean isPalindrome(String str) {
        boolean isPalindrome = false;
        char[] chars = str.toCharArray();
        for (int shift = 0; !isPalindrome && shift < chars.length; shift++) {
            isPalindrome = isPalindrome(chars, shift);
        }
        return isPalindrome;
    }

    private boolean isPalindrome(char[] chars, int shift) {
        boolean isPalindrome = true;
        for (int i = 0; isPalindrome && i <= chars.length / 2; i++) {
            isPalindrome = chars[(i + shift) % chars.length] == chars[(chars.length - i - 1 + shift) % chars.length];
        }
        return isPalindrome;
    }
}
