package com.epam.algorithms.crackinginterviews;

import com.epam.algorithms.lang.AbstractTest;
import com.epam.algorithms.lang.TreeNode;

/**
 * https://www.youtube.com/watch?v=aNtDir94pcA&list=PLlhDxqlV_-vkak9feCSrnjlrnzzzcopSG&index=44
 */
public class VerifyBST extends AbstractTest {
    private static final String TEMPLATE = "[%s] is %sBST";

    @Override
    public void test() throws Exception {
        TreeNode<Integer> root = TreeNode.createTree(new Integer[] {8, 3, 1, null, null, 6, 4, null, null, 7, null, null, 10, null, 14, 13});
        print("[%s] is %sBST", root.recursiveToString(), isBST(root) ? "" : "not ");
        root = TreeNode.createTree(new Integer[] {8, 3, 1, null, null, 6, 4, null, null, 9, null, null, 10, null, 14, 13});
        print(TEMPLATE, root.recursiveToString(), isBST(root) ? "" : "not ");
        root = TreeNode.createTree(new Integer[] {2, 1, null, null, 3});
        print("[%s] is %sBST", root.recursiveToString(), isBST(root) ? "" : "not ");
    }

    public boolean isBST(TreeNode<Integer> root) {
        return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBST(TreeNode<Integer> root, int from, int to) {
        return root == null
                || (root.value > from
                && root.value <= to
                && isBST(root.left, from, root.value)
                && isBST(root.right, root.value, to));
    }
}
