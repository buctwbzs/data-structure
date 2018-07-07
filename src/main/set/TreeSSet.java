package main.set;

import main.bst.BST;

public class TreeSSet<E extends Comparable<E>> implements Set<E> {

  private BST<E> bst;

  public TreeSSet() {
    this.bst = new BST<>();
  }

  @Override
  public void add(E e) {
    bst.add(e);
  }

  @Override
  public void remove(E e) {
    bst.remove(e);
  }

  @Override
  public boolean contains(E e) {
    return bst.contains(e);
  }

  @Override
  public int getSize() {
    return bst.size();
  }

  @Override
  public boolean isEmpty() {
    return bst.isEmpty();
  }
}
