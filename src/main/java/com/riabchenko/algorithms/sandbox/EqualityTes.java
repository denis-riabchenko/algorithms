package com.riabchenko.algorithms.sandbox;

import org.junit.Test;

import java.lang.reflect.Method;

/**
 *
 */
public class EqualityTes {
    @Test
    public void testEquality() throws Exception {
        String c = "a";
        c += "s";
        c.intern();

        String a = "as";
        String b = "as";

        System.out.printf("a == b -> %b\n", a   == b);
        System.out.printf("a == c -> %b\n", a == c);
    }
}
