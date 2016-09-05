package com.epam.algorithms.coursera.test4;

import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

/**
 * Created by driabchenko on 8/9/15.
 */
public class KosarajuTest {

    public static void main(String[] args) throws IOException {
        Graph g = Graph.loadGraph("com/epam/algorithms/coursera/test4/SCC.txt");
        Kosaraju k = new Kosaraju();
        List<Set<Integer>> scComponents = k.getSCComponents(g);
        Stream<Integer> result = scComponents.stream().map(lst -> (new HashSet<>(lst)).size())
                .sorted(Comparator.reverseOrder()).limit(5);
        System.out.println(Arrays.toString(result.toArray()));
        // This gives us     434821,969,459,313,211
        // Correct answer is 434821,968,459,313,211 why????
    }
}
