package com.epam.algorithms.leetcode;

import com.epam.algorithms.lang.AbstractTest;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class Permutations extends AbstractTest {
    @Override
    public void test() throws Exception {
        permutation("", "123");
        System.out.println();
        permutation("", "123".toCharArray(), new boolean[3]);
        System.out.println();

        List<char[]> permutations = new ArrayList<>();
        permutation(permutations, "123".toCharArray(), new LinkedHashSet<>());
        for (char[] permutation : permutations) {
            System.out.println(String.valueOf(permutation));
        }
    }

    private static void permutation(String base, String set) {
        if (set.length() > 0) {
            for (int i = 0; i < set.length(); i++) {
                String rest = "";
                if (i > 0) {
                    rest = set.substring(0, i);
                }
                if (i < set.length() - 1) {
                    rest += set.substring(i + 1);
                }
                permutation(base + set.substring(i, i + 1), rest);
            }
        } else {
            System.out.println(base + set);
        }
    }

    private static void permutation(String base, char[] set, boolean[] used) {
        if (base.length() == set.length) {
            System.out.println(base);
        } else {
            for (int i = 0; i < used.length; i++) {
                if (!used[i]) {
                    used[i] = true;
                    permutation(base + set[i], set, used);
                    used[i] = false;
                }
            }
        }
    }

    private static void permutation(List<char[]> permutations, char[] set, LinkedHashSet<Integer> used) {
        if (used.size() == set.length) {
            char[] permutation = new char[set.length];
            int i = 0;
            for (Integer index : used) {
                permutation[i++] = set[index];
            }
            permutations.add(permutation);
        } else {
            for (int i = 0; i < set.length; i++) {
                if (!used.contains(i)) {
                    used.add(i);
                    permutation(permutations, set, used);
                    used.remove(i);
                }
            }
        }
    }
}
