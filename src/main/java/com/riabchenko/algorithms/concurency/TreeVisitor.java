package com.riabchenko.algorithms.concurency;

import com.riabchenko.algorithms.lang.TreeNode;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class TreeVisitor<T> {
  private class TreeNodeVisitor implements Runnable {
    private TreeNode<T> nodeToVisit;

    public TreeNodeVisitor(TreeNode<T> nodeToVisit) {
      this.nodeToVisit = nodeToVisit;
    }

    @Override
    public void run() {
      synchronized (visited) {
        System.out.printf("Thread #%d: %s\n", Thread.currentThread().getId(), nodeToVisit);
        if (nodeToVisit.left != null && visited.add(nodeToVisit.left)) {
          executorService.submit(new TreeNodeVisitor(nodeToVisit.left));
        }
        if (nodeToVisit.right != null && visited.add(nodeToVisit.right)) {
          executorService.submit(new TreeNodeVisitor(nodeToVisit.right));
        }
      }
    }
  }

  private final Set<TreeNode<T>> visited;
  private final ExecutorService executorService;

  public TreeVisitor(int poolSize) {
    visited = new HashSet<>();
    executorService = Executors.newFixedThreadPool(poolSize);
  }

  public void traverse(TreeNode<T> root) throws InterruptedException {
    executorService.submit(new TreeNodeVisitor(root));
    executorService.awaitTermination(1000, TimeUnit.MILLISECONDS);
  }
}
