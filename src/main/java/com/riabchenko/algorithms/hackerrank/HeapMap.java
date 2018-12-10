package com.riabchenko.algorithms.hackerrank;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://www.hackerrank.com/challenges/qheap1
 */
public class HeapMap<K, V extends Comparable<V>> {
  private class Value implements Comparable<Value> {
    int index;
    V v;

    public Value(int index, V v) {
      this.index = index;
      this.v = v;
    }

    @Override
    public int compareTo(Value value) {
      return v.compareTo(value.v);
    }
  }
  private Map<K, Value> indexes;
  private K[] heap;
  private int size;

  @SuppressWarnings("unchecked")
  public HeapMap() {
    indexes = new HashMap<>(10);
    heap = (K[]) new Object[10];
  }

  public boolean containsKey(K k) {
    return indexes.containsKey(k);
  }

  public V get(K k) {
    Value value = indexes.get(k);
    return value == null ? null : value.v;
  }

  public V put(K k, V v) {
    Value value = indexes.get(k);
    if (value == null) {
      ensureCapacity();
      heap[size] = k;
      indexes.put(k, new Value(size, v));
      bubbleUp(size++);
      return null;
    }
    V oldValue = value.v;
    int ind = value.index;
    value.v = v;
    bubbleUp(ind);
    sinkDown(ind);
    return oldValue;
  }

  @SuppressWarnings("unchecked")
  public V remove(K k) {
    Value value = indexes.remove(k);
    if (value != null) {
      int ind = value.index;
      heap[ind] = heap[--size];
      if (ind < size) {
        indexes.get(heap[ind]).index = ind;
        bubbleUp(ind);
        sinkDown(ind);
      }
      return value.v;
    }
    return null;
  }

  public K firstKey() {
    if (size == 0) {
      throw new IllegalStateException();
    }
    return heap[0];
  }

  public V removeFirst() {
    K k = firstKey();
    return remove(k);
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  private void bubbleUp(int ind) {
    int pind = (ind - 1) / 2;
    while (ind > 0 && compareByInd(pind, ind) > 0) {
      swap(ind, pind);
      ind = pind;
      pind = (ind - 1) / 2;
    }
  }

  private void sinkDown(int ind) {
    int cind = ind * 2 + 1;
    while (cind < size) {
      if (cind < size - 1 && compareByInd(cind, cind + 1) > 0) {
        cind++;
      }
      if (compareByInd(ind, cind) < 0) {
        return;
      }
      swap(ind, cind);
      ind = cind;
      cind = ind * 2 + 1;
    }
  }

  private void ensureCapacity() {
    if (size == heap.length) {
      int newLength = heap.length + (heap.length >> 1);
      heap = Arrays.copyOf(heap, newLength);
    }
  }

  private void swap(int ind, int pind) {
    K tmp = heap[pind];
    heap[pind] = heap[ind];
    heap[ind] = tmp;
    indexes.get(heap[ind]).index = ind;
    indexes.get(heap[pind]).index = pind;
  }

  private int compareByInd(int ind1, int ind2) {
    Value v1 = indexes.get(heap[ind1]);
    Value v2 = indexes.get(heap[ind2]);
    return v1.compareTo(v2);
  }
}
