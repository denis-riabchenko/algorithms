package com.riabchenko.algorithms.coursera.test4;

import com.google.common.collect.Sets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by driabchenko on 8/9/15.
 */
public class Graph {
    private Map<Integer, Set<Integer>> g;
    private Map<Integer, Set<Integer>> r;

    private Graph(int initialSize) {
        g = new LinkedHashMap<>(initialSize);
        r = new LinkedHashMap<>(initialSize);
    }

    public void reverse() {
        Map<Integer, Set<Integer>> tmp = g;
        g = r;
        r = tmp;
    }

    public int size() {
        return g.size();
    }

    public Iterable<Integer> verticies() {
        return Collections.unmodifiableSet(g.keySet());
    }

    public Iterable<Integer> arcs(int v) {
        Set<Integer> arcs = g.get(v);
        if (arcs == null) {
            arcs = Collections.emptySet();
        }
        return Collections.unmodifiableSet(arcs);
    }

    public static Graph loadGraph(String resource) throws IOException {
        Graph g = new Graph(875715);
        try (InputStream input = KosarajuTest.class.getClassLoader().getResourceAsStream(resource);
             InputStreamReader sr = new InputStreamReader(input);
             BufferedReader br = new BufferedReader(sr)) {

            String line;
            while((line = br.readLine()) != null) {
                String[] values = line.split(" ");
                int v = Integer.parseInt(values[0]);
                int w = Integer.parseInt(values[1]);
                if (v != w) {
                    Set<Integer> arcs = g.g.get(v);
                    if (arcs == null) {
                        arcs = new HashSet<>();
                        g.g.put(v, arcs);
                    }
                    arcs.add(w);

                    arcs = g.r.get(w);
                    if (arcs == null) {
                        arcs = new HashSet<>();
                        g.r.put(w, arcs);
                    }
                    arcs.add(v);
                }
            }
        } catch (NumberFormatException ex) {
            System.out.printf(ex.getLocalizedMessage());
        }

        return g;
    }
}
