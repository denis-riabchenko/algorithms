package com.riabchenko.algorithms.crackinginterviews.o1collection;

import java.util.*;

/**
 * O(1) add, getRandom, remove collection (can contain duplicates)
 */
public class O1List<T> {
    private Map<T, Pointer> indexMap = new HashMap<>();
    private List<T> valueList = new ArrayList<>();

    public void add(T value) {
        Pointer pointer = indexMap.get(value);
        if (pointer == null) {
            pointer = new Pointer(valueList.size());
            valueList.add(value);
            indexMap.put(value, pointer);
        } else {
            pointer.increase();
        }
    }

    public boolean remove(T value) {
        Pointer pointer = indexMap.get(value);
        if (pointer != null) {
            if (!pointer.decrease()) {
                indexMap.remove(value);
                if (pointer.getIndex() < valueList.size() - 1) {
                    T last = valueList.get(valueList.size() - 1);
                    valueList.set(pointer.getIndex(), last);
                    indexMap.get(last).setIndex(pointer.getIndex());
                }
                valueList.remove(valueList.size() - 1);
            }
            return true;
        }
        return false;
    }

    public T getRandom() {
        Random random = new Random();
        return valueList.get(random.nextInt(valueList.size()));
    }

    public boolean contain(T value) {
        Pointer pointer = indexMap.get(value);
        return pointer != null && pointer.count > 0;
    }

    private static class Pointer {
        private int index;
        private int count = 1;

        public Pointer(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public void increase() {
            count++;
        }

        public boolean decrease() {
            return --count > 0;
        }
    }
}
