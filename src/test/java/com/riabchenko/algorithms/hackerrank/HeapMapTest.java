package com.riabchenko.algorithms.hackerrank;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 *
 */
public class HeapMapTest {
  private HeapMap<String, Integer> heapMap;

  @Before
  public void before() {
    heapMap = new HeapMap<>();
//    heapMap.put("A", 0);
//    heapMap.put("B", Integer.MAX_VALUE);
//    heapMap.put("C", Integer.MAX_VALUE);
//    heapMap.put("D", Integer.MAX_VALUE);
//    heapMap.put("E", Integer.MAX_VALUE);
//    heapMap.put("F", Integer.MAX_VALUE);
    heapMap.put("A", 10);
    heapMap.put("B", 14);
    heapMap.put("C", 19);
    heapMap.put("D", 26);
    heapMap.put("E", 31);
    heapMap.put("F", 42);
    heapMap.put("G", 27);
    heapMap.put("H", 44);
    heapMap.put("J", 35);
    heapMap.put("K", 33);
  }

  @Test
  public void testGetMin() {
    assertThat(heapMap.firstKey()).isEqualTo("A");
  }

  @Test
  public void testExtractMin() {
    heapMap.remove("A");
    assertMinHeap();
  }

  @Test
  public void testExtractArbitrary() {
    heapMap.remove("F");
    assertMinHeap();
    assertThat(heapMap.containsKey("F")).isFalse();
  }

  @Test
  public void testUpdateArbitrary() {
    heapMap.put("G", 7);
    assertThat(heapMap.firstKey()).isEqualTo("G");
    assertThat(heapMap.get("G")).isEqualTo(7);
    assertMinHeap();
  }

  private void assertMinHeap() {
    List<Integer> values = new ArrayList<>();
    while (!heapMap.isEmpty()) {
      int min = heapMap.removeFirst();
      values.add(min);
    }
    List<Integer> sorted = new ArrayList<>(values);
    Collections.sort(sorted);
    assertThat(values).containsExactlyElementsIn(sorted).inOrder();
  }
}
