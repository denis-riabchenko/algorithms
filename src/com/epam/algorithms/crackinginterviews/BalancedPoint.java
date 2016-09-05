package com.epam.algorithms.crackinginterviews;

import com.epam.algorithms.lang.AbstractTest;

/**
 *
 */
public class BalancedPoint extends AbstractTest {
    @Override
    public void test() throws Exception {
        int[] array = {5, 4, 0, 19, 3, 4, 2, 0};
        print("%s: %d", array, balancedPoint(array));

        array = new int[] {5, 4, 2, 1};
        print("%s: %d", array, balancedPoint(array));
    }

    public int balancedPoint(int[] array) {
        long leftSum = 0;
        long rightSum = 0;

        for (int index = 1; index < array.length; index ++) {
            rightSum += array[index];
        }

        for (int index = 1; index < array.length; index ++) {
            leftSum += array[index - 1];
            rightSum -= array[index];
            if (leftSum == rightSum) {
                return index;
            }
        }

        return -1;
    }
}
