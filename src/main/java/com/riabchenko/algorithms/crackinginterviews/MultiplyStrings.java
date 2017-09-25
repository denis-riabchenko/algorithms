package com.riabchenko.algorithms.crackinginterviews;

import com.riabchenko.algorithms.lang.AbstractTest;

/**
 *
 */
public class MultiplyStrings extends AbstractTest {
    @Override
    public void test() throws Exception {
        print(multiply("123", "456"));
    }

    public String multiply(String num1, String num2) {
        char[] n1 = num1.toCharArray();
        char[] n2 = num2.toCharArray();

        return karatsuba(n1, 0, n1.length, n2, 0, n2.length);
    }

    public String karatsuba(char[] n1, int s1, int l1, char[] n2, int s2, int l2) {
        if (l1 == 0 || l2 == 0) {
            return "0";
        }

        if (l1 == 1 && l2 == 1) {
            return Integer.toString((n1[s1] - '0') * (n2[s2] - '0'));
        }

        int n = Math.max(l1, l2);
        int half = n / 2;

        String ac = karatsuba(n1, s1, half, n2, s2, half);
        String bd = karatsuba(n1, s1 + half, n - half, n2, s2 + half, n - half);
        String ad = karatsuba(n1, s1, half, n2, s2 + half, n - half);
        String bc = karatsuba(n1, s1 + half, n - half, n2, s2, half);

        StringBuilder builder = new StringBuilder(ac);
        appendZeroes(builder, n - n % 2 - ad.length()).append(ad);
        appendZeroes(builder, n - n % 2 - bc.length()).append(bc).append(bd);

        return builder.toString();
    }

    private StringBuilder appendZeroes(StringBuilder builder, int zeroes) {
        while (zeroes-- > 0) {
            builder.append('0');
        }
        return builder;
    }
}
