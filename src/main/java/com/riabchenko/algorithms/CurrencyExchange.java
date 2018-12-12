package com.riabchenko.algorithms;

import com.riabchenko.algorithms.hackerrank.HeapMap;

import java.util.*;

/**
 * Given pairs of currencies with exchange rates, implement method to return exchange rate between any two
 * arbitrary currencies.
 */
public class CurrencyExchange {
  private static class Edge {
    final String from;
    final String to;
    final double weight;

    public Edge(String from, String to, double weight) {
      this.from = from;
      this.to = to;
      this.weight = weight;
    }
  }
  private final List<Edge> rates = new ArrayList<>();
  private final Set<String> currencies = new HashSet<>();

  public CurrencyExchange(String[][] pairs, double[] rates) {
    for (int i = 0; i < pairs.length; i++) {
      this.rates.add(new Edge(pairs[i][0], pairs[i][1], -Math.log(rates[i])));
      currencies.add(pairs[i][0]);
      currencies.add(pairs[i][1]);
    }
  }

  public double findRate(String c1, String c2) {
    if (c1.equals(c2)) {
      return 1.0;
    }
    if (!currencies.contains(c1) || !currencies.contains(c2) || rates.isEmpty()) {
      return 0.0;
    }
    Double weight = findPathBF(c1, c2);
//    Double weight = findPathFW(c1, c2);
//    Double weight = findPathD(c1, c2);
    if (weight == null) {
      return 0.0;
    }
    return Math.pow(Math.E, -weight);
  }

  // Dijkstra's algorithm
  private Double findPathD(String from, String to) {
    HeapMap<String, Double> heapMap = new HeapMap<>();

    Map<String, Set<Edge>> edges = new HashMap<>(currencies.size());
    for (Edge rate : rates) {
      edges.computeIfAbsent(rate.from, f -> new HashSet<>()).add(rate);
      heapMap.put(rate.from, Double.MAX_VALUE);
      heapMap.put(rate.to, Double.MAX_VALUE);
    }

    Map<String, String> parent = new HashMap<>();
    Map<String, Double> distance = new HashMap<>();
    distance.put(from, 0.0);

    while (!heapMap.isEmpty()) {
      String u = heapMap.firstKey();
      Double uw = heapMap.removeFirst();
      distance.put(u, uw);
      Set<Edge> neighbors = edges.getOrDefault(u, Collections.emptySet());
      for (Edge e : neighbors) {
        Double vw = heapMap.get(e.to);
        if (vw != null && vw > uw + e.weight) {
          heapMap.put(e.to, uw + e.weight);
          distance.put(e.to, uw + e.weight);
          parent.put(e.to, u);
        }
      }
    }

    return distance.get(to);
  }


  private static class Table<R, C, V> {
    private Map<R, Map<C, V>> rows = new HashMap<>();

    public V get(R r, C c) {
      Map<C, V> row = rows.get(r);
      if (row == null) {
        return null;
      }
      return row.get(c);
    }

    public V put(R r, C c, V v) {
      return rows.computeIfAbsent(r, rk -> new HashMap<>()).put(c, v);
    }
  }

  // Bellman-Ford algorithm
  private Double findPathBF(String from, String to) {
    Map<String, String> predecessors = new HashMap<>();
    Map<String, Double> weights = new HashMap<>();
    weights.put(from, 0.0);
    for (int i = 1; i < currencies.size(); i++) {
      for (Edge edge : rates) {
        Double srcWeight = weights.get(edge.from);
        Double dstWeight = weights.get(edge.to);
        if (srcWeight != null && (dstWeight == null || srcWeight + edge.weight < dstWeight)) {
          weights.put(edge.to, srcWeight + edge.weight);
          predecessors.put(edge.to, edge.from);
        }
      }
    }

    for (Edge edge : rates) {
      Double srcWeight = weights.get(edge.from);
      Double dstWeight = weights.get(edge.to);
      if (srcWeight != null && (dstWeight == null || srcWeight + edge.weight < dstWeight)) {
        throw new IllegalStateException("Negative loop");
      }
    }

    Double weight = weights.get(to);
    if (weight != null) {
      String p = to;
      do {
        System.out.print(p + " ");
        p = predecessors.get(p);
      } while (p != null);
      System.out.println();
    }
    return weight;
  }

  // Floyd-Warshall algorithm
  private Double findPathFW(String from, String to) {
    Table<String, String, Double> weights = new Table<>();
    Table<String, String, String> predecessors = new Table<>();
    for (Edge edge : rates) {
      weights.put(edge.from, edge.from, 0.0);
      weights.put(edge.to, edge.to, 0.0);
      weights.put(edge.from, edge.to, edge.weight);
      predecessors.put(edge.from, edge.to, edge.from);
    }

    for (String k : currencies) {
      for (String i : currencies) {
        for (String j : currencies) {
          Double ij = weights.get(i, j);
          Double ik = weights.get(i, k);
          Double kj = weights.get(k, j);
          if (ik != null && kj != null && (ij == null || ij > ik + kj)) {
            weights.put(i, j, ik + kj);
            predecessors.put(i, j, predecessors.get(k, j));
          }
        }
      }
    }

    for (String i : currencies) {
      Double ii = weights.get(i, i);
      if (ii != null && ii < 0) {
        throw new IllegalStateException("Negative loop");
      }
    }

    Double weight = weights.get(from, to);
    if (weight != null) {
      String p = to;
      do {
        System.out.print(p + " ");
        p = predecessors.get(from, p);
      } while (p != null);
      System.out.println();
    }
    return weight;
  }
}
