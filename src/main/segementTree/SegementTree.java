package main.segementTree;


public class SegementTree<E> {

  private E[] tree;
  private E[] data;
  private Merger<E> merger;

  public SegementTree(E[] arr, Merger<E> merger) {

    data = (E[]) new Object[data.length];
    this.merger = merger;

    for (int i = 0; i < data.length; i++) {
      data[i] = arr[i];
    }
    tree = (E[]) new Object[data.length * 4];
    buildSegementTree(0, 0, data.length - 1);
  }

  /**
   * @param index
   * @param l
   * @param r
   */
  private void buildSegementTree(int index, int l, int r) {

    if (l == r) {
      tree[index] = data[l];
      return;
    }

    int leftTreeIndex = leftChild(index);
    int rightTreeIndex = rightChild(index);
    int mid = l + (r - l) / 2;
    buildSegementTree(leftTreeIndex, l, mid);
    buildSegementTree(rightTreeIndex, l + 1, r);

    tree[index] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);

  }


  public int getSize() {
    return data.length;
  }

  public E get(int idx) {
    if (idx < 0 || idx > data.length)
      throw new IllegalArgumentException("Idx is illegal");
    return data[idx];
  }

  private int leftChild(int idx) {
    return 2 * idx + 1;
  }

  private int rightChild(int idx) {
    return 2 * idx + 2;
  }
}
