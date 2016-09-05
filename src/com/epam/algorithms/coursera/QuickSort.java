package com.epam.algorithms.coursera;

import java.util.Comparator;

public class QuickSort {
    public <T extends Comparable<? super T>> void quickSort(T[] input) {
        quickSort(input, Comparator.<T>naturalOrder());
    }

    public <T> void quickSort(T[] input, Comparator<T> comparator) {
        quickSort(input, 0, input.length, comparator);
    }

    protected <T> void quickSort(T[] input, int start, int length, Comparator<T> comparator) {
        if (length > 1) {
            int i = partition(input, start, length, comparator);
            if (i > start) {
                quickSort(input, start, i - start, comparator);
                quickSort(input, i, start + length - i, comparator);
            }
        }
    }

    protected <T> int partition(T[] input, int start, int length, Comparator<T> comparator) {
        int i = choosePivot(input, start, length);
        if (i > start) {
            swap(input, start, i);
            i = start;
        }

        T p = input[i];
        for (int j = i + 1; j < start + length; j ++) {
            i = compareAndSwap(input, i, j, comparator, p);
        }

        return i;
    }

    protected <T> int compareAndSwap(T[] input, int i, int j, Comparator<T> comparator, T pivot) {
        if (comparator.compare(input[j], pivot) <= 0) {
            swap(input, i, j);
            i ++;
        }
        return i;
    }

    protected <T> int choosePivot(T[] input, int start, int length) {
        return start;
    }

    protected <T> void swap(T[] input, int i, int j) {
        T tmp = input[i];
        input[i] = input[j];
        input[j] = tmp;
    }
}
