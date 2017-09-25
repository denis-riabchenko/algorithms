package com.riabchenko.algorithms.coursera.test2;

import com.riabchenko.algorithms.coursera.QuickSort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by driabchenko on 7/26/15.
 */
public abstract class QuickSortTest extends QuickSort {
    private static int count = 0;

    @Override protected <T> int compareAndSwap(final T[] input, final int i, final int j,
            final Comparator<T> comparator, final T pivot) {
        count ++;
        return super.compareAndSwap(input, i, j, comparator, pivot);
    }

    public static void main(String[] args) throws IOException {
        QuickSortTest s = new QuickSortTest() {
            @Override
            protected <T> int choosePivot(T[] input, int start, int length) {
                return start;
            }
        };
        Integer[] arr = s.loadArray("com/epam/algorithms/coursera/test2/QuickSort.txt");
//        System.out.printf("%s -> ", Arrays.toString(arr));
        s.quickSort(arr);
        System.out.println(count);

        s = new QuickSortTest() {
            @Override
            protected <T> int choosePivot(T[] input, int start, int length) {
                return start + length - 1;
            }
        };
        s.quickSort(arr);
        System.out.println(count);

//        System.out.println(Arrays.toString(arr));
    }

    public Integer[] loadArray(String resource) throws IOException {
        List<Integer> lst = new ArrayList<>(10000);

        try (InputStream input = getClass().getClassLoader().getResourceAsStream(resource);
             InputStreamReader sr = new InputStreamReader(input);
             BufferedReader br = new BufferedReader(sr)) {
            String line;
            while((line = br.readLine()) != null){
                lst.add(Integer.parseInt(line));
            }
        } catch (NumberFormatException ex) {
            System.out.printf(ex.getLocalizedMessage());
        }

        return lst.toArray(new Integer[lst.size()]);
    }
}
