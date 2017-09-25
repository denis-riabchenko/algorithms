package com.riabchenko.algorithms.crackinginterviews.weight;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 */
public class RandomWeightList {
    private class Value {
        String value;
        int boundary;

        Value(String value, int boundary) {
            this.value = value;
            this.boundary = boundary;
        }
    }

    private int size;
    private final List<Value> values = new ArrayList<>();

    public void add(String value, int weight) {
        values.add(new Value(value, weight + size - 1));
        size += weight;
    }

    public String getRandom() {
        Random random = new Random();
        int index = random.nextInt(size);
        int left = 0;
        for (Value value : values) {
            if (index > left && index <= value.boundary) {
                return value.value;
            }
            left = value.boundary;
        }
        return null;
    }
}
