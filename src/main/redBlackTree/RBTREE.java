package main.redBlackTree;

import java.util.concurrent.BlockingDeque;

public class RBTREE<K extends Comparable<K>, V> {

  private static final boolean RED = true;
  private static final boolean BLACK = false;
  private Node root;
  private int size;

  private class Node {

    private K key;
    private V value;
    private boolean color;

    private Node left, right;


    public Node(K key, V value) {
      this.key = key;
      this.value = value;
      left = right = null;
      color = RED;
    }

  }

  public RBTREE() {
    root = null;
    size = 0;
  }


  /**
   * Add node to RBTree.
   */
  public void add(K key, V value) {

    root = add(root,key,value);
    root.color = BLACK;


  }

  private Node add(Node node, K key, V value){

    if (root == null) {
      ++size;
      return new Node(key, value);
    }

    if (key.compareTo(node.key) < 0)
      node.left = add(node.left, key,value);
    else if (key.compareTo(node.key)>0)
      node.right = add(node.right,key,value);
    else
      node.value = value;

    if (isRed(node.right)&& !isRed(node.left))
      node = leftRotate(node);

    if (isRed(node.left) && isRed(node.left.left))
      node = rightRoate(node);

    if (isRed(node.left) && isRed(node.right))
      flipColors(node);
    return node;
  }

  /**
   * Perform left rotate on subtree.
   * -------------------------------------------------------
   *    node                    x
   *    /  \    left rotate    / \
   *   T1   x   -----------> node T3
   *       / \                /\
   *      T2 T3              T1 T2
   * -------------------------------------------------------
   *
   * @param node
   * @return
   */
  private Node leftRotate(Node node){

    Node x = node.right;

    node.right = x.left;
    x.left = node;

    x.color = node.color;
    node.color = RED;

    return x;
  }

  /**
   * Perform right rotate on subtree.
   * -------------------------------------------------------
   *    node                      x
   *    /  \    right rotate     / \
   *   x   T2   ----------->    y  node
   *  / \                          / \
   *  y T1                        T1  T2
   * -------------------------------------------------------
   *
   * @param node - root.
   */
  private Node rightRoate(Node node){

      Node x = node.left;
      x.right = node;
      node.left = x.right;
      x.color = node.color;
      node.color = RED;
      return x;
  }

  /**
   * -------------------------------------------------------------------------
   * Preform:
   *                                    root(red)
   * 2-node --> 3-node --> 4-node -->   /  \
   *                                 left  right
   *                               (black) (black)
   * -------------------------------------------------------------------------
   * Eg1:
   *  Use 2-3 Tree expression.
   *
   *  [42] -->  [37 42]  --> [37 42 66]
   * -------------------------------------------------------------------------
   * Eg2:
   *  Use RBTree expression.
   *
   *  42(black) -->   42(black)  -->  42(black)      -->     42(red)
   *                  /              /      \               /     \
   *             37(red)          37(red)   66(red)    37(black)  66(black)
   * -------------------------------------------------------------------------
   * @param node
   */
  private void flipColors(Node node) {

    node.color = RED;
    node.left.color = node.right.color = BLACK;

  }

  private boolean isRed(Node node){
    if (node == null){
      return BLACK;
    }
    return node.color;
  }

  /**
   * Get size;
   *
   * @return
   */
  public int getSize() {
    return size;
  }


}
