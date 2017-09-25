package com.riabchenko.algorithms.crackinginterviews;

import com.riabchenko.algorithms.lang.AbstractTest;
import com.riabchenko.algorithms.lang.TreeNode;

import java.util.*;

/**
 * https://www.youtube.com/watch?v=79fPL0F_1XA&list=PLlhDxqlV_-vkak9feCSrnjlrnzzzcopSG&index=43
 */
public class BinaryTreeLevelNodes extends AbstractTest {
    @Override
    public void test() throws Exception {
        TreeNode<Integer> root = TreeNode.createTree(new Integer[] {8, 3, 1, null, null, 6, 4, null, null, 7, null, null, 10, null, 14, 13});
        for (int level = 0; level < 5; level ++) {
            print("%d: %s == %s", level, dfsLevel(root, level), bfsLevel(root, level));
        }
    }

    public Integer[] dfsLevelRec(TreeNode<Integer> root, int depth) {
        List<Integer> nodes = new ArrayList<>();
        dfsLevel(root, depth, nodes);
        return nodes.toArray(new Integer[nodes.size()]);
    }

    public Integer[] dfsLevel(TreeNode<Integer> root, int depth) {
        List<Integer> nodes = new ArrayList<>();

        if (depth >= 0) {
            LinkedList<TreeNode<Integer>> nodesQueue = new LinkedList<>();
            LinkedList<Integer> levelsQueue = new LinkedList<>();
            nodesQueue.push(root);
            levelsQueue.push(0);

            while (!nodesQueue.isEmpty()) {
                TreeNode<Integer> node = nodesQueue.pop();
                int currentLevel = levelsQueue.pop();
                if (currentLevel == depth) {
                    nodes.add(node.value);
                } else {
                    if (node.left != null) {
                        nodesQueue.push(node.left);
                        levelsQueue.push(currentLevel + 1);
                    }
                    if (node.right != null) {
                        nodesQueue.push(node.right);
                        levelsQueue.push(currentLevel + 1);
                    }
                }
            }
        }

        return nodes.toArray(new Integer[nodes.size()]);
    }

    public Integer[] bfsLevel(TreeNode<Integer> root, int depth) {
        List<Integer> nodes = new ArrayList<>();

        if (depth >= 0) {
            Queue<TreeNode<Integer>> nodesQueue = new LinkedList<>();
            Queue<Integer> levelsQueue = new LinkedList<>();
            nodesQueue.add(root);
            levelsQueue.add(0);

            while (!nodesQueue.isEmpty()) {
                TreeNode<Integer> node = nodesQueue.poll();
                int currentLevel = levelsQueue.poll();
                if (currentLevel == depth) {
                    nodes.add(node.value);
                } else {
                    if (node.left != null) {
                        nodesQueue.add(node.left);
                        levelsQueue.add(currentLevel + 1);
                    }
                    if (node.right != null) {
                        nodesQueue.add(node.right);
                        levelsQueue.add(currentLevel + 1);
                    }
                }
            }
        }

        return nodes.toArray(new Integer[nodes.size()]);
    }

    private void dfsLevel(TreeNode<Integer> root, int depth, List<Integer> nodes) {
        if (root != null && depth >= 0) {
            if (depth == 0) {
                nodes.add(root.value);
            } else {
                dfsLevel(root.left, depth - 1, nodes);
                dfsLevel(root.right, depth - 1, nodes);
            }
        }
    }
}
