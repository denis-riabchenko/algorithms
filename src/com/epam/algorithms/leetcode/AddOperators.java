package com.epam.algorithms.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by driabchenko on 10/9/15.
 */
public class AddOperators {
    public static void main(String... args) {
        AddOperators m = new AddOperators();
        List<String> expr = m.addOperators("232", 8);
        System.out.println(Arrays.toString(expr.toArray()));
    }

    public List<String> addOperators(String num, int target) {
        LinkedList<String> result = new LinkedList<>();
        if (num != null && !num.isEmpty()) {
            int n = ((int) num.charAt(0)) - (int) '0';
            addOperators(num.substring(1, num.length()), num.substring(0, 1), n, target, result);
        }
        return result;
    }
    class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;
        UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
    }

    public void addOperators(String num, String expr, int current, int target, List<String> result) {
        if (num != null && !num.isEmpty()) {
            int n = ((int) num.charAt(0)) - (int) '0';
            if (num.length() > 1) {
                String rest = num.substring(1, num.length());
                addOperators(rest, expr + "+" + n, current + n, target, result);
                addOperators(rest, expr + "-" + n, current - n, target, result);
                addOperators(rest, expr + "*" + n, current * n, target, result);
            } else {
                if (current + n == target) {
                    result.add(expr + "+" + n);
                }
                if (current - n == target) {
                    result.add(expr + "-" + n);
                }
                if (current * n == target) {
                    result.add(expr + "*" + n);
                }
            }
        }
    }
}
