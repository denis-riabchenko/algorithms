package com.riabchenko.algorithms.crackinginterviews;

import com.riabchenko.algorithms.lang.AbstractTest;

/**
 * https://www.youtube.com/watch?v=DSbm-QozyDA&list=PLlhDxqlV_-vkak9feCSrnjlrnzzzcopSG&index=47
 */
public class DoubleToRational extends AbstractTest {
    @Override
    public void test() throws Exception {
        print(doubleToRational(-0.125d));
    }

    public String doubleToRational(double divident) {
        if (divident == 0) {
            return "0";
        }
        double divider = 1;
        while (divident != Math.floor(divident)) {
            divider *= 10;
            divident *= 10;
        }

        double gcd = divident > divider ? findGCD(divident, divider) : findGCD(divider, divident);
        divident /= gcd;
        divider /= gcd;
        String template =  divident < 0 || divider < 0 ? "-%d/%d" : "%d/%d";
        return String.format(template, Math.abs((int) divident), Math.abs((int) divider));
    }

    private double findGCD(double a, double b) {
        double remainder = a % b;
        if (remainder > -1 && remainder < 1) {
            return b;
        }
        return findGCD(a, remainder);
    }
}
