package com.riabchenko.algorithms.coursera.test5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DijkstraTest {
    public static void main(String[] args) throws IOException {
        List<Arc> arcs = loadGraph("com/epam/algorithms/coursera/test5/dijkstraData.txt");
        Dijkstra dijkstra = new Dijkstra();
        Map<Integer, Path> pathMap = dijkstra.findShortestPaths(1, arcs);
        pathMap.values().forEach(System.out::println);

        StringBuilder result = new StringBuilder();
        int[] vertices = {7,37,59,82,99,115,133,165,188,197};
        for (int v : vertices) {
            result.append(pathMap.get(v).length()).append(",");
        }
        System.out.println(result.toString());
        // 3024,3684,2947,2660,2367,2399,2029,4039,3139,3068
        // 2599,2610,2947,2052,2367,2399,2029,2442,2505,3068
    }


    public static List<Arc> loadGraph(String resource) throws IOException {
        List<Arc> g = new ArrayList<>();
        try (InputStream input = DijkstraTest.class.getClassLoader().getResourceAsStream(resource);
                InputStreamReader sr = new InputStreamReader(input);
                BufferedReader br = new BufferedReader(sr)) {

            String line;
            while((line = br.readLine()) != null) {
                Arc.readArcs(line, g);
            }
        } catch (NumberFormatException ex) {
            System.out.printf(ex.getLocalizedMessage());
        }

        return g;
    }
}
