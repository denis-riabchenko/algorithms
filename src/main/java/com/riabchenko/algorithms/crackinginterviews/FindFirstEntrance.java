package com.riabchenko.algorithms.crackinginterviews;

import com.riabchenko.algorithms.lang.AbstractTest;

/**
 * https://www.youtube.com/watch?v=bvzJw9CVmkI&list=PLlhDxqlV_-vkak9feCSrnjlrnzzzcopSG&index=45
 */
public class FindFirstEntrance extends AbstractTest {
    @Override
    public void test() throws Exception {
        int[] array = {1, 2, 4, 4, 4, 4, 5, 5};
        print("%s: %d", array, getFirstIndex(array, 1));
        print("%s: %d", array, getFirstIndex(array, 2));
        print("%s: %d", array, getFirstIndex(array, 3));
        print("%s: %d", array, getFirstIndex(array, 4));
        print("%s: %d", array, getFirstIndex(array, 5));
    }

    public int getFirstIndex(int[] nums, int n) {
        return getFirstIndex(nums, n, 0, nums.length - 1);
    }

    public int getFirstIndex(int[] nums, int n, int from, int to) {
        if (from > to || nums[from] > n || nums[to] < n) {
            return -1;
        }
        if (nums[from] == n) {
            return from;
        }

        int med = (from + to) / 2;
        if (nums[med] >= n) {
            int index = getFirstIndex(nums, n, from, med - 1);
            return nums[med] == n && index == -1 ? med : index;
        }
        return getFirstIndex(nums, n, med + 1, to);
    }
}
