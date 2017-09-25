package com.riabchenko.algorithms.crackinginterviews;

import com.riabchenko.algorithms.lang.AbstractTest;

import java.util.*;

/**
 *
 */
public class TopTenMostFrequent2 extends AbstractTest {
    @Override
    public void test() throws Exception {
        int n = 10;
        List<String> items = new ArrayList<>();
        for (int i = 1; i <= n * 5; i++) {
            String str = String.format("%d", i);
            items.add(str);
            if (i <= n) {
                for (int j = 1; j < i; j++) {
                    items.add(str);
                }
            }
        }
        print(getTopTen(items, n).toArray());
        items.add("11");
        print(getTopTen(items, n).toArray());
        items.add("2");
        items.add("11");
        print(getTopTen(items, n).toArray());
        items.add("2");
        items.add("11");
        print(getTopTen(items, n).toArray());
    }

    public <T> List<T> getTopTen(List<T> items, int n) {
        final Map<T, Integer> frequencies = new LinkedHashMap<>();
        for (T item : items) {
            Integer fr = frequencies.get(item);
            if (fr == null) {
                fr = 0;
            }
            frequencies.put(item, fr + 1);
        }

        final Comparator<T> order = (o1, o2) -> frequencies.get(o1) - frequencies.get(o2);
        PriorityQueue<T> queue = new PriorityQueue<>(n, order);
        for (T item : frequencies.keySet()) {
            if (queue.size() < n) {
                queue.add(item);
            } else if (order.compare(queue.peek(), item) < 0) {
                queue.poll();
                queue.add(item);
            }
        }
        LinkedList<T> result = new LinkedList<>();
        while(!queue.isEmpty()) {
            result.addFirst(queue.poll());
        }
        return result;
    }
}
