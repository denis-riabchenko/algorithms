package com.riabchenko.algorithms.crackinginterviews;

import com.riabchenko.algorithms.lang.AbstractTest;
import com.riabchenko.algorithms.lang.TreeNode;

import java.util.LinkedList;

/**
 *
 */
public class ArithmeticTree extends AbstractTest {
    @Override
    public void test() throws Exception {
        TreeNode<String> root = TreeNode.createTree(new String[] {"+", "*", "2", null, null, "3", null, null, "*", "2", null, null, "3"});
        LinkedList<String> stack = new LinkedList<>();
        treeToExpr(root, stack);
        print(stack.pop());

        LinkedList<Integer> stack2 = new LinkedList<>();
        treeToNumber(root, stack2);
        print(stack2.pop());
    }

    public void treeToExpr(TreeNode<String> node, LinkedList<String> stack) {
        if (node != null) {
            treeToExpr(node.left, stack);
            treeToExpr(node.right, stack);
            if (node.left != null && node.right != null) {
                stack.push(calculateString(node, stack));
            } else {
                stack.push(node.value);
            }
        }
    }

    private String calculateString(TreeNode<String> node, LinkedList<String> stack) {
        String op2 = stack.pop();
        String op1 = stack.pop();

        return String.format("(%s %s %s)", op1, node.value, op2);
    }

    public void treeToNumber(TreeNode<String> node, LinkedList<Integer> stack) {
        if (node != null) {
            treeToNumber(node.left, stack);
            treeToNumber(node.right, stack);
            if (node.left != null && node.right != null) {
                stack.push(calculate(node, stack));
            } else {
                stack.push(Integer.parseInt(node.value));
            }
        }
    }

    private Integer calculate(TreeNode<String> node, LinkedList<Integer> stack) {
        Integer op2 = stack.pop();
        Integer op1 = stack.pop();

        switch (node.value) {
            case "+":
                return op1 + op2;
            case "-":
                return op1 - op2;
            case "*":
                return op1 * op2;
            case "/":
                return op1 / op2;
            default:
                throw new IllegalArgumentException(String.format("Operation %s is not supported", node.value));
        }
    }
}
