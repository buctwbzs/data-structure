package main.heap;

import main.array.DynamicArray;

public class MaxHeap<E extends Comparable> {

  private DynamicArray<E> array = new DynamicArray<>();

  /**
   * If known initial capacity
   *
   * @param capacity
   */
  public MaxHeap(int capacity) {
    this.array = new DynamicArray<>(capacity);
  }

  /**
   * If not known initial capacity;
   */
  public MaxHeap() {
    this.array = new DynamicArray<>();
  }

  public MaxHeap(E[] arr) {

    array = new DynamicArray<>(arr);

    for (int i = parent(array.getSize() - 1); i >= 0; i--)
      shiftDown(i);

  }

  /**
   * Add a element.
   *
   * @return
   */

  public void add(E e) {
    array.addLast(e);
    shiftUp(array.getSize() - 1);
  }

  private void shiftUp(int idx) {

    while (idx > 0 && array.get(parent(idx)).compareTo(array.get(idx)) < 0) {
      array.swap(idx, parent(idx));
      idx = parent(idx);
    }

  }


  public E findMax() {
    if (array.getSize() == 0)
      throw new IllegalArgumentException("Heap is empty");
    return array.get(0);
  }

  /**
   * Get Max Element
   *
   * @return
   */
  public E extractMax() {
    E ret = findMax();
    array.swap(0, array.getSize() - 1);
    array.removeLast();
    shiftDown(0);
    return ret;
  }

  private void shiftDown(int idx) {

    while (leftChild(idx) < array.getSize()) {
      int j = leftChild(idx);

      if (j + 1 < array.getSize() && array.get(j + 1).compareTo(array.get(j)) > 0)
        ++j;

      if (array.get(idx).compareTo(array.get(j)) >= 0)
        break;

      array.swap(idx, j);
      idx = j;

    }

  }

  public E replace(E e) {

    E ret = findMax();
    array.set(0, e);
    shiftDown(0);
    return ret;
  }

  /**
   * Traverse elements expect not leaf nodes and shift down.
   */
  public void heapify() {

  }

  public int size() {
    return array.getSize();
  }

  public boolean isEmpty() {
    return array.isEmpty();
  }

  private int parent(int index) {
    if (index == 0)
      throw new IllegalArgumentException("Index:0 doesn't have parent");
    return (index - 1) / 2;
  }

  private int leftChild(int index) {
    return index * 2 + 1;
  }

  private int rightChild(int index) {
    return index * 2 + 2;
  }


}
