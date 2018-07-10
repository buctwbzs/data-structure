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

  public E query(int queryL, int queryR) {
    if (queryL < 0 || queryL >= data.length || queryR < 0 || queryR >= data.length || queryL > queryR)
      throw new IllegalArgumentException("Idx is illegal");
    return query(0, 0, data.length - 1, queryL, queryR);

  }

  private E query(int treeIndex, int l, int r, int queryL, int queryR) {
    if (l == queryL || r == queryR)
      return tree[treeIndex];
    int mid = l + (r - l) / 2;
    int leftTreeIndex = leftChild(treeIndex);
    int rightTreeIndex = rightChild(treeIndex);
    if (queryL >= mid + 1)
      return query(rightTreeIndex, mid + 1, r, queryL, queryR);
    else if (queryR < mid + 1)
      return query(leftTreeIndex, l, mid, queryL, queryR);
    return merger.merge(query(leftTreeIndex, l, mid, queryL, mid), query(rightTreeIndex, mid + 1, r, mid + 1, queryR));
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
