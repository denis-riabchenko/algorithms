package com.riabchenko.algorithms.coursera.test3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by driabchenko on 8/2/15.
 */
public class Graph {
    private Random rnd = new Random(System.currentTimeMillis());
    private List<Edge> edges = new ArrayList<>();

    public Graph() {

    }

    public Graph(Graph g) {
        for (Edge e : g.getEdges()) {
            this.edges.add(new Edge(e.getLeft(), e.getRight()));
        }
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public Set<Integer> getVertices() {
        Set<Integer> vertices = new HashSet<>();
        for (Edge e : edges) {
            vertices.add(e.getLeft());
            vertices.add(e.getRight());
        }
        return vertices;
    }

    public int merge() {
        Edge edge = edges.get(rnd.nextInt(edges.size()));
        edges.remove(edge);

        List<Edge> rem = new ArrayList<>();
        for (Edge e : edges) {
            if (e.getLeft() == edge.getRight()) {
                e.setLeft(edge.getLeft());
            }
            if (e.getRight() == edge.getRight()) {
                e.setRight(edge.getLeft());
            }
            if (e.isLoop()) {
                rem.add(e);
            }
        }
        edges.removeAll(rem);

        int count = Integer.MAX_VALUE;
        if (getVertices().size() == 2) {
            count = edges.size();
        }
        return count;
    }

    public static Graph loadGraph(String resource) throws IOException {
        Graph g = new Graph();

        try (InputStream input = g.getClass().getClassLoader().getResourceAsStream(resource);
             InputStreamReader sr = new InputStreamReader(input);
             BufferedReader br = new BufferedReader(sr)) {

            String line;
            while((line = br.readLine()) != null) {
                String[] values = line.split("\t");
                Integer left = null;
                for (String l : values) {
                    Integer label = Integer.parseInt(l);
                    if (left == null) {
                        left = label;
                    } else {
                        boolean exist = false;
                        for (Edge e : g.getEdges()) {
                            if ((e.getLeft() == left && e.getRight() == label) ||
                                    (e.getLeft() == label && e.getRight() == left)) {
                                exist = true;
                                break;
                            }
                        }
                        if (!exist) {
                            g.getEdges().add(new Edge(left, label));
                        }
                    }
                }
            }
        } catch (NumberFormatException ex) {
            System.out.printf(ex.getLocalizedMessage());
        }

        return g;
    }
}
