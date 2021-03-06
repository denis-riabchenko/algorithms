package com.riabchenko.algorithms.coursera.test6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Created by driabchenko on 8/22/15.
 */
public class MedianMaintenance {
    private static ArrayList<Integer> numbers;
    private final static int DefaultSize = 10000;

    private static void input(String path) {
        System.out.println("Processing input file...");
        try {
            numbers = new ArrayList<Integer>(DefaultSize);
            File file = new File(path);
            Scanner sc = new Scanner(file);
            while (sc.hasNextInt())
                numbers.add(sc.nextInt());
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // size of maxHeap >= size of minHeap
    private static int medianMaintenance() {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(DefaultSize / 2,
                new Comparator<Integer>() {
                    public int compare(Integer a, Integer b) {
                        if (a < b) return 1;
                        else if (a == b) return 0;
                        else return -1;
                    }
                });
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        int[] medians = new int[DefaultSize];
        for(int i = 0; i < numbers.size(); i++) {
            int v = numbers.get(i);
            if(maxHeap.isEmpty())
                maxHeap.add(v);
            else {
                if(v < maxHeap.peek()) {
                    if(maxHeap.size() > minHeap.size())
                        minHeap.add(maxHeap.poll());
                    maxHeap.add(v);
                }
                else if(v > minHeap.peek()) {
                    if(minHeap.size() == maxHeap.size())
                        maxHeap.add(minHeap.poll());
                    minHeap.add(v);
                }
                else {
                    if(minHeap.size() == maxHeap.size())
                        maxHeap.add(v);
                    else
                        minHeap.add(v);
                }
            }
            medians[i] = maxHeap.peek();
        }
        int sum = 0;
        for(int i = 0; i < medians.length; i++) {
            sum += medians[i];
        }
        return sum % 10000;
    }

    public static void main(String[] args) {
//        if (args.length == 0) {
//            System.err.println("Please input file path.");
//            System.exit(-1);
//        }
//        input(args[0]);
        input("/Users/driabchenko/IdeaProjects/algorithms/src/com/epam/algorithms/coursera/test6/Median.txt");
        System.out.println(medianMaintenance());
    }
}
