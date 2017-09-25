package com.riabchenko.algorithms.crackinginterviews.weight;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Collection that returns random element according to its weight.
 */
public class RandomWithWeight {
    @Test
    public void testRandom() {
        RandomWeightList list = new RandomWeightList();

        list.add("A", 200);
        list.add("B", 100);

        assertProbability(list, "A", 200 / 300.0);
        assertProbability(list, "B", 100 / 300.0);
    }

    private void assertProbability(RandomWeightList list, String value, double probability) {
        int count = 0;
        for (int i = 0; i < 100000; i++) {
            if (value.equals(list.getRandom())) {
                count++;
            }
        }
        assertEquals(probability, count / 100000.0, probability / 10);
    }
}
