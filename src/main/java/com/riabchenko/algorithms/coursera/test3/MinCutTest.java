package com.riabchenko.algorithms.coursera.test3;

import java.io.IOException;

/**
 * Created by driabchenko on 8/2/15.
 */
public class MinCutTest {
    public static void main(String... args) throws IOException {
        MinCutTest cuts = new MinCutTest();
        Graph gr = Graph.loadGraph("com/epam/algorithms/coursera/test3/kargerMinCut.txt");

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 1000; i ++) {
            Integer f = cuts.minCut(new Graph(gr));
            if (f != null && f < min) {
                min = f;
            }
        }
        System.out.println(min);
    }

    public Integer minCut(Graph gr) {
        int count = Integer.MAX_VALUE;
        while (count == Integer.MAX_VALUE) {
            count = gr.merge();
        }

        return count;
    }
}
