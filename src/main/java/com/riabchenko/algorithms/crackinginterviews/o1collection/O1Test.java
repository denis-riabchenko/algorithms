package com.riabchenko.algorithms.crackinginterviews.o1collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 *
 */
@RunWith(JUnit4.class)
public class O1Test {
    @Test
    public void testO1Set() {
        O1Set<String> o1set = new O1Set<>();
        o1set.add("1");
        o1set.add("5");
        o1set.add("2");
        o1set.add("4");
        o1set.add("3");

        assertTrue(o1set.contain("1"));
        assertTrue(o1set.contain("2"));
        assertTrue(o1set.contain("3"));
        assertTrue(o1set.contain("4"));
        assertTrue(o1set.contain("5"));

        o1set.remove("1");
        assertFalse(o1set.contain("1"));
        assertTrue(o1set.contain("2"));
        assertTrue(o1set.contain("3"));
        assertTrue(o1set.contain("4"));
        assertTrue(o1set.contain("5"));

        o1set.remove("2");
        assertFalse(o1set.contain("2"));
        assertTrue(o1set.contain("3"));
        assertTrue(o1set.contain("4"));
        assertTrue(o1set.contain("5"));

        o1set.remove("3");
        assertFalse(o1set.contain("3"));
        assertTrue(o1set.contain("4"));
        assertTrue(o1set.contain("5"));

        o1set.remove("4");
        assertFalse(o1set.contain("4"));
        assertTrue(o1set.contain("5"));

        o1set.remove("5");
        assertFalse(o1set.contain("5"));
    }
}
