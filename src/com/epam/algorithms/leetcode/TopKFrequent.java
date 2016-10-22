package com.epam.algorithms.leetcode;

import org.junit.Test;

import java.util.*;

public class TopKFrequent {
    @Test
    public void test() {
        System.out.println(Arrays.toString(topKFrequent(new int[] {1, 1, 1, 2, 2, 3}, 2).toArray()));
    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();
        if (nums != null && nums.length > 0 && k > 0) {
            final Map<Integer, Integer> map = new HashMap<>();
            for (int n : nums) {
                map.put(n, map.getOrDefault(n, 0) + 1);
            }
            class Comp implements Comparator<Integer> {
                @Override
                public int compare(Integer n1, Integer n2) {
                    return n1 - n2;
                }
            }
            Comp c = new Comp();

            PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer n1, Integer n2) {
                    Integer f1 = map.get(n1);
                    Integer f2 = map.get(n2);
                    return f1 - f2;
                }
            });
            PriorityQueue<Integer> queue1 = new PriorityQueue<>(

                    (Integer n1, Integer n2) -> {
                        Integer f1 = map.get(n1);
                        Integer f2 = map.get(n2);
                        return f1 - f2;
                    }
            );
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (queue.size() < k) {
                    queue.add(entry.getKey());
                } else if (map.get(queue.peek()) <  entry.getValue()) {
                    queue.poll();
                    queue.add(entry.getKey());
                }
            }
            result.addAll(queue);
        }

        return result;
    }
}
