package com.riabchenko.algorithms.hackerrank;

import java.util.*;

/**
 * https://www.hackerrank.com/challenges/journey-to-the-moon/problem
 */
public class JourneyToTheMoon2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<Integer, List<Integer>> graph = readGraph(scanner);
        dfs(graph, 0, new boolean[graph.size()]);
    }

    private static Map<Integer, List<Integer>> readGraph(Scanner scanner) {
        final int n = scanner.nextInt();
        final int p = scanner.nextInt();
        Map<Integer, List<Integer>> hash = new HashMap<>(n);
        for (int i = 0; i < n; i++) {
            hash.put(i, new ArrayList<>());
        }
        for (int i = 0; i < p; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            List<Integer> al = hash.get(a);
            al.add(b);
        }
        return hash;
    }

    private static void dfs(Map<Integer, List<Integer>> graph, Integer v, boolean[] visited) {
        if (visited[v]) {
            return;
        }
        visited[v] = true;
        System.out.println(v);
        for (Integer n : graph.get(v)) {
            dfs(graph, n, visited);
        }
    }
}
