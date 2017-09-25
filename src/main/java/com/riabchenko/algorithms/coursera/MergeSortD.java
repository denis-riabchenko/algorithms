package com.riabchenko.algorithms.coursera;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by driabchenko on 7/4/15.
 *
 * Merge sort implementation using O(n) extra space
 *
 */
public class MergeSortD {
    public static void main(String[] args) {
        (new MergeSortD()).execute();
    }

    public void execute() {
        Integer[] arr = {5, 4, 1, 8, 7, 2, 6, 3};
        System.out.printf("%s -> ", Arrays.toString(arr));
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static <T extends Comparable> void mergeSort(T[] arr) {
        if (arr != null && arr.length > 0) {
            Object[] tmp = new Object[arr.length];
            mergeSort(arr, 0, arr.length, new Comparator<T>() {
                @Override
                public int compare(T o1, T o2) {
                    return o1.compareTo(o2);
                }
            }, tmp);
        }
    }

    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr != null && arr.length > 0) {
            Object[] tmp = new Object[arr.length];
            mergeSort(arr, 0, arr.length, comparator, tmp);
        }
    }

    private static <T> void mergeSort(T[] arr, int start, int length, Comparator<T> comparator, Object[] tmp) {
        int half = length / 2;
        if (half > 0) {
            // Sort left part
            mergeSort(arr, start, half, comparator, tmp);
            // Sort right part
            mergeSort(arr, start + half, length - half, comparator, tmp);

            // Merge both parts into resulting array
            for (int k = start, l = start, r = start + half; k < start + length; k ++) {
                if (l < start + half && (r >= start + length || comparator.compare(arr[l], arr[r]) < 0)) {
                    tmp[k] = arr[l ++];
                } else if (r < start + length && (l >= start + half || comparator.compare(arr[l], arr[r]) >= 0)) {
                    tmp[k] = arr[r ++];
                }
            }
            System.arraycopy(tmp, start, arr, start, length);
        }
    }
}
