package com.riabchenko.algorithms.lang;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;

/**
 *
 */
@RunWith(JUnit4.class)
public abstract class AbstractTest {
    @Test
    public void testExecution() throws Exception {
        test();
    }

    public void print(String template, Object... values) {
        for (int i = 0; i < values.length; i++) {
            values[i] = arrayToString(values[i]);
        }
        print(String.format(template, values));
    }

    public void print(Object str) {
        System.out.println(arrayToString(str));
    }

    public Object arrayToString(Object obj) {
        if (obj instanceof String[]) {
            return Arrays.toString((String[]) obj);
        }
        if (obj instanceof Integer[]) {
            return Arrays.toString((Integer[]) obj);
        }
        if (obj instanceof Long[]) {
            return Arrays.toString((Long[]) obj);
        }
        if (obj instanceof Double[]) {
            return Arrays.toString((Double[]) obj);
        }
        if (obj instanceof Float[]) {
            return Arrays.toString((Float[]) obj);
        }
        if (obj instanceof Byte[]) {
            return Arrays.toString((Byte[]) obj);
        }
        if (obj instanceof Character[]) {
            return Arrays.toString((Character[]) obj);
        }
        if (obj instanceof Short[]) {
            return Arrays.toString((Short[]) obj);
        }
        if (obj instanceof Object[]) {
            return Arrays.toString((Object[]) obj);
        }
        if (obj instanceof int[]) {
            return Arrays.toString((int[]) obj);
        }
        if (obj instanceof long[]) {
            return Arrays.toString((long[]) obj);
        }
        if (obj instanceof double[]) {
            return Arrays.toString((double[]) obj);
        }
        if (obj instanceof float[]) {
            return Arrays.toString((float[]) obj);
        }
        if (obj instanceof byte[]) {
            return Arrays.toString((byte[]) obj);
        }
        if (obj instanceof char[]) {
            return Arrays.toString((char[]) obj);
        }
        if (obj instanceof short[]) {
            return Arrays.toString((short[]) obj);
        }
        return obj.toString();
    }

    public abstract void test() throws Exception;
}
