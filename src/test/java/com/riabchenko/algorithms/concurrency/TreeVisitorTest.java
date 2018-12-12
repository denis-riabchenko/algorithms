package com.riabchenko.algorithms.concurrency;

import com.riabchenko.algorithms.concurency.TreeVisitor;
import com.riabchenko.algorithms.lang.TreeNode;
import org.junit.Test;

/**
 *
 */
public class TreeVisitorTest {
  @Test
  public void test() throws InterruptedException {
    TreeVisitor visitor = new TreeVisitor(5);
    TreeNode<Integer> root = TreeNode.createTree(new Integer[] {4,2,1,null,null,3,null,null,6,5,null,null,7,null,9,8,null,null,10});
    visitor.traverse(root);
  }
}
