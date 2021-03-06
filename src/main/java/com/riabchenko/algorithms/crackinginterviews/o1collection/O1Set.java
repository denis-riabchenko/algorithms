package com.riabchenko.algorithms.crackinginterviews.o1collection;

import java.util.*;

/**
 * O(1) add, getRandom, remove collection (can not contain duplicates)
 */
public class O1Set<T> {
    private Map<T, Integer> indexMap = new HashMap<>();
    private List<T> valueList = new ArrayList<>();

    public boolean add(T value) {
        Integer index = indexMap.get(value);
        if (index == null) {
            indexMap.put(value, valueList.size());
            valueList.add(value);
            return true;
        }
        return false;
    }

    public boolean remove(T value) {
        final Integer index = indexMap.get(value);
        if (index != null) {
            indexMap.remove(value);
            final int lastIndex = valueList.size() - 1;
            if (index < lastIndex) {
                final T last = valueList.get(lastIndex);
                valueList.set(index, last);
                indexMap.put(last, index);
            }
            valueList.remove(lastIndex);
            return true;
        }
        return false;
    }

    public T getRandom() {
        Random random = new Random();
        return valueList.get(random.nextInt(valueList.size()));
    }

    public boolean contain(T value) {
        return indexMap.containsKey(value);
    }
}
