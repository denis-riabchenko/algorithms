package com.epam.algorithms.lang;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 */
public class TreeNode<T> {
    public final T value;
    public TreeNode<T> left;
    public TreeNode<T> right;

    public TreeNode(T value) {
        this.value = checkNotNull(value);
    }

    public String toString() {
        return String.format("%d: (%s, %s)",
                value,
                left == null ? null : left.value,
                right == null ? null : right.value);
    }

    public String recursiveToString() {
        return String.format("%d, %s, %s",
                value,
                left == null ? null : left.recursiveToString(),
                right == null ? null : right.recursiveToString());
    }

    public static <T> TreeNode<T> createTree(T[] traversal) {
        TreeNode<T> root = null;
        if (traversal != null && traversal.length > 0 && traversal[0] != null) {
            root = new TreeNode<>(traversal[0]);
            recursivelyLoadTree(root, traversal, 1);
        }
        return root;
    }

    private static <T> int recursivelyLoadTree(TreeNode<T> node, T[] traversal, int start) {
        if (start < traversal.length) {
            if (traversal[start] != null) {
                node.left = new TreeNode<>(traversal[start]);
                start = recursivelyLoadTree(node.left, traversal, start + 1);
            } else {
                start++;
            }

            if (start < traversal.length && traversal[start] != null) {
                node.right = new TreeNode<>(traversal[start]);
                start = recursivelyLoadTree(node.right, traversal, start + 1);
            } else {
                start++;
            }
        }
        return start;
    }
}
