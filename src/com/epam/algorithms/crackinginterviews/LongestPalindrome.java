package com.epam.algorithms.crackinginterviews;

import com.epam.algorithms.lang.AbstractTest;

/**
 *
 */
public class LongestPalindrome extends AbstractTest {
    @Override
    public void test() throws Exception {
        print(findLongestPalindrome("47674123321234213"));
        print(findLongestPalindrome("4767412332124213"));
    }

    public String findLongestPalindrome(String str) {
        if (str == null) {
            return null;
        }

        int longestStart = 0;
        int longestEnd = 0;
        char[] chars = str.toCharArray();

        for (int middle = 0; middle < chars.length; middle++) {
            int start = middle;
            int endEven = middle;
            int endOdd = middle + 1;

            while (start >= 0 && (endEven < chars.length || endOdd < chars.length)) {
                if (endEven < chars.length && chars[start] == chars[endEven]) {
                    if ((endEven - start) > (longestEnd - longestStart)) {
                        longestStart = start;
                        longestEnd = endEven;
                    }
                } else {
                    endEven = chars.length;
                }

                if (endOdd < chars.length && chars[start] == chars[endOdd]) {
                    if ((endOdd - start) > (longestEnd - longestStart)) {
                        longestStart = start;
                        longestEnd = endOdd;
                    }
                } else {
                    endOdd = chars.length;
                }

                start--;
                endEven++;
                endOdd++;
            }
        }

        return str.substring(longestStart, longestEnd + 1);
    }
}
