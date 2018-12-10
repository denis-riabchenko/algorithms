package com.riabchenko.algorithms.tree.traversal;

import com.riabchenko.algorithms.lang.TreeNode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

import static com.google.common.truth.Truth.assertThat;

/**
 *
 */
public class Traversals {
  private StringBuilder out;
  private StringBuilder outIterative;
  private TreeNode<Integer> root;
  private Deque<TreeNode<Integer>> stack;

  @Before
  public void before() {
    out = new StringBuilder();
    outIterative = new StringBuilder();
//    root = TreeNode.createTree(new Integer[] {4,2,1,null,null,3,null,null,6,5,null,null,7,null,8});
    root = TreeNode.createTree(new Integer[] {4,2,1,null,null,3,null,null,6,5,null,null,7,null,9,8,null,null,10});
    stack = new ArrayDeque<>();
  }

  @After
  public void after() {
    System.out.println(Arrays.toString(stack.toArray()));
    System.out.println(out.toString());
  }

  @Test
  public void testPre() {
    preOrder(root, out);
    preOrderIterative(root, outIterative);
    assertThat(outIterative.toString()).isEqualTo(out.toString());
  }

  @Test
  public void testIn() {
    inOrder(root, out);
    inOrderIterative(root, outIterative);
    assertThat(outIterative.toString()).isEqualTo(out.toString());
  }

  @Test
  public void testPost() {
    postOrder(root, out);
    postOrderIterative(root, outIterative);
    assertThat(outIterative.toString()).isEqualTo(out.toString());
  }

  public void preOrder(TreeNode<Integer> node, StringBuilder out) {
    stack.push(node);
    printStack();
    out.append(node.value).append(' ');
    if (node.left != null) {
      preOrder(node.left, out);
    }
    if (node.right != null) {
      preOrder(node.right, out);
    }
    stack.pop();
  }

  public void preOrderIterative(TreeNode<Integer> node, StringBuilder out) {
    Deque<TreeNode<Integer>> stack = new ArrayDeque<>();
    stack.push(node);
    while (!stack.isEmpty()) {
      node = stack.pop();
      out.append(node.value).append(' ');
      if (node.right != null) {
        stack.push(node.right);
      }
      if (node.left != null) {
        stack.push(node.left);
      }
    }
  }

  public void inOrder(TreeNode<Integer> node, StringBuilder out) {
    stack.push(node);
    if (node.left != null) {
      inOrder(node.left, out);
    }
    printStack();
    out.append(node.value).append(' ');
    if (node.right != null) {
      inOrder(node.right, out);
    }
    stack.pop();
  }

  public void inOrderIterative(TreeNode<Integer> node, StringBuilder out) {
    Deque<TreeNode<Integer>> stack = new ArrayDeque<>();
    while (node != null || !stack.isEmpty()) {
      while (node != null) {
        stack.push(node);
        node = node.left;
      }
      node = stack.pop();
      out.append(node.value).append(' ');
      node = node.right;
    }
  }

  public void postOrder(TreeNode<Integer> node, StringBuilder out) {
    if (node.left != null) {
      postOrder(node.left, out);
    }
    if (node.right != null) {
      postOrder(node.right, out);
    }
    out.append(node.value).append(' ');
  }

  public void postOrderIterative(TreeNode<Integer> node, StringBuilder out) {
    Deque<TreeNode<Integer>> stack = new ArrayDeque<>();
    Deque<TreeNode<Integer>> printStack = new ArrayDeque<>();
    stack.push(node);
    while (!stack.isEmpty()) {
      node = stack.pop();
      printStack.push(node);
      if (node.left != null) {
        stack.push(node.left);
      }
      if (node.right != null) {
        stack.push(node.right);
      }
    }
    while (!printStack.isEmpty()) {
      out.append(printStack.pop().value).append(' ');
    }
  }

  private void printStack() {
    System.out.println(Arrays.toString(stack.toArray()));
  }
}
