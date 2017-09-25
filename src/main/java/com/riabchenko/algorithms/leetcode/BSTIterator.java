package com.riabchenko.algorithms.leetcode;

import com.riabchenko.algorithms.lang.TreeNode;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/binary-search-tree-iterator/
 */
public class BSTIterator {
    public BSTIterator() {}

    @Test
    public void test() {
        TreeNode<Integer> root = TreeNode.createTree(new Integer[] {4,2,1,null,null,3,null,null,6,5,null,null,7});

        BSTIterator iterator = new BSTIterator();

        iterator.traversePreorder(root);
        System.out.print("\n\nPreorder: ");
        while (iterator.hasNext()) {
            System.out.print(iterator.next());
        }

        iterator.traverseInorder(root);
        System.out.print("\n\nInorder: ");
        while (iterator.hasNext()) {
            System.out.print(iterator.next());
        }

        iterator.traversePostorder(root);
        System.out.print("\n\nPostorder: ");
        while (iterator.hasNext()) {
            System.out.print(iterator.next());
        }

        System.out.println("\n");
    }

    private LinkedList<Integer> list = new LinkedList<>();

    public static BSTIterator iterator(TreeNode<Integer> root) {
        BSTIterator iterator = new BSTIterator();
        iterator.traverseInorder(root);
        return iterator;
    }

    public void traversePreorder(TreeNode<Integer> node) {
        list.clear();
        Deque<TreeNode<Integer>> rights = new LinkedList<>();
        while(node != null) {
            list.add(node.value);
            if (node.right != null) {
                rights.push(node.right);
            }
            node = node.left;
            if (node == null && !rights.isEmpty()) {
                node = rights.pop();
            }
        }
    }

    private void traverseInorder(TreeNode<Integer> node) {
        list.clear();
        Deque<TreeNode<Integer>> stack = new LinkedList<>();
        while(node != null || !stack.isEmpty()){
            while(node != null){
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            list.add(node.value);
            node = node.right;
        }
    }

    private void traversePostorder(TreeNode<Integer> node) {
        list.clear();
        Deque<TreeNode<Integer>> stack = new LinkedList<>();
        while(node != null || !stack.isEmpty()){
            while(node.right != null){
                stack.push(node.right);
                node = node.right;
            }
            while(node.left != null){
                stack.push(node.left);
                node = node.left;
            }
            list.add(node.value);
            node = stack.pop();
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !list.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        return list.removeFirst();
    }
}
