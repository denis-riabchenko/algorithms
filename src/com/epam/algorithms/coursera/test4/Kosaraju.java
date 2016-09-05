package com.epam.algorithms.coursera.test4;

import com.google.common.collect.Sets;

import java.util.*;

/**
 * Created by driabchenko on 8/9/15.
 */
public class Kosaraju {
    private void DFS(Graph graph, int s, Set<Integer> visited, List<Integer> comp) {
        LinkedList<Integer> stack = new LinkedList<>();
        visited.add(s);
        stack.push(s);

        while (!stack.isEmpty()) {
            int v = stack.peek();
            boolean ret = true;
            for (int w : graph.arcs(v)) {
                if (!visited.contains(w)) {
                    ret = false;
                    visited.add(w);
                    stack.push(w);
                }
            }
            if (ret) {
                comp.add(stack.pop());
            }
        }
    }

    public List<Integer> fillOrder(Graph graph) {
        Set<Integer> visited = new HashSet<>(graph.size());
        List<Integer> order = new ArrayList<>();

        graph.verticies().forEach(v -> {
            if (!visited.contains(v)) {
                DFS(graph, v, visited, order);
            }
        });

        return order;
    }

    public List<Set<Integer>> getSCComponents(Graph graph) {
        graph.reverse();
        List<Integer> order = fillOrder(graph);
        Collections.reverse(order);
        graph.reverse();
        Set<Integer> visited = new HashSet<>(graph.size());
        List<Set<Integer>> SCComp = new ArrayList<>();
        order.stream().filter(v -> !visited.contains(v)).forEach(v -> {
            List<Integer> comp = new ArrayList<>();
            DFS(graph, v, visited, comp);
            SCComp.add(Sets.newHashSet(comp));
        });

        return SCComp;
    }
}
