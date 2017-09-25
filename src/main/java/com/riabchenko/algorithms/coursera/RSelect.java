package com.riabchenko.algorithms.coursera;

import org.junit.Assert;

import java.util.Comparator;

/**
 * Created by driabchenko on 8/2/15.
 */
public class RSelect {
    public static void main(String[] args) {
        (new RSelect()).execute();
    }

    public void execute() {
        Integer[] input = {3, 8, 2, 5, 1, 4, 7, 6};
        Assert.assertEquals(3L, (long) select(input, 2));
        Assert.assertEquals(5L, (long) select(input, 4));
    }

    public <T extends Comparable<T>> T select(T[] input, int index) {
        return select(input, index, Comparator.<T>naturalOrder());
    }

    public <T> T select(T[] input, int index, Comparator<T> comparator) {
        return select(input, 0, input.length, index, comparator);
    }

    protected <T> T select(T[] input, int start, int length, int index, Comparator<T> comparator) {
        T sel = null;
        if (length > 0) {
            int i = partition(input, start, length, comparator);
            if (i > index) {
                sel = select(input, start, i - start, index, comparator);
            } else if (i < index) {
                sel = select(input, i + 1, start + length - i - 1, index, comparator);
            } else {
                sel = input[i];
            }
        }
        return sel;
    }

    protected <T> int partition(T[] input, int start, int length, Comparator<T> comparator) {
        int p = choosePivot(input, start, length);
        if (p > start) {
            swap(input, start, p);
        }

        T pivot = input[start];
        int i = start;
        int j = i + 1;
        while (i < j && j < start + length) {
            if (comparator.compare(input[i + 1], pivot) <= 0) {
                i ++;
            } else if (comparator.compare(input[j], pivot) > 0) {
                j ++;
            } else {
                swap(input, i + 1, j);
            }
        }
        swap(input, start, i);
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
