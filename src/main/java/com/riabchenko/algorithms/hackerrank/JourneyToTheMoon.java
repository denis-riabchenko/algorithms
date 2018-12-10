package com.riabchenko.algorithms.hackerrank;

import java.util.*;

/**
 * https://www.hackerrank.com/challenges/journey-to-the-moon/problem
 */
public class JourneyToTheMoon {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int p = scanner.nextInt();
        ListNode[] graph = new ListNode[n];
        Set<Integer> notVisited = new HashSet<>(graph.length);
        for (int i = 0; i < n; i++) {
            notVisited.add(i);
        }
        for (int i = 0; i < p; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();

            ListNode node = graph[a];
            graph[a] = new ListNode(b);
            graph[a].next = node;

            node = graph[b];
            graph[b] = new ListNode(a);
            graph[b].next = node;
        }

        long result = n * (long) n;
        while (!notVisited.isEmpty()) {
            int v = notVisited.iterator().next();
            int[] groupSize = new int[1];
            dfs(graph, v, notVisited, groupSize);
            result -= groupSize[0] * (long) groupSize[0];
        }
        System.out.println(result / 2);
    }

    private static void dfs(ListNode[] graph, int v, Set<Integer> notVisited, int[] groupSize) {
        if (!notVisited.remove(v)) {
            return;
        }

        groupSize[0]++;
        ListNode node = graph[v];
        while (node != null) {
            dfs(graph, node.val, notVisited, groupSize);
            node = node.next;
        }
    }

    static final class ListNode {
        final int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }
}
