package main.avl;

import java.util.ArrayList;

public class AVL<K extends Comparable<K>, V> {

  private Node root;
  private int size;

  private class Node {

    public K key;
    public V value;
    public Node left, right;
    public int height;

    Node(K key, V value) {

      this.key = key;
      this.value = value;
      left = right = null;
      height = 1;

    }

  }

  public AVL() {
    root = null;
    size = 0;
  }

  public int getSize() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Get height of node.
   *
   * @param node
   * @return
   */
  private int getHeight(Node node) {
    if (node == null)
      return 0;
    return node.height;
  }

  /**
   * Get balanced factor of node.
   *
   * @param node
   * @return
   */
  private int getBalanceFactor(Node node) {
    if (node == null)
      return 0;
    return getHeight(node.left) - getHeight(node.right);
  }

  /**
   * Judge a Tree whether is a BST or not.
   */
  public boolean isBST() {

    ArrayList<K> keys = new ArrayList<>();
    inOrder(root, keys);

    for (int i = 1; i < keys.size(); i++) {
      if (keys.get(i - 1).compareTo(keys.get(i)) > 0)
        return false;
    }
    return true;
  }

  private void inOrder(Node node, ArrayList<K> keys) {

    if (node == null) return;
    inOrder(node.left, keys);
    keys.add(node.key);
    inOrder(node.right, keys);

  }

  /**
   * Judge a Tree whether is balanced or not.
   *
   * @return
   */
  public boolean isBalanced() {

    return isBalanced(root);

  }

  private boolean isBalanced(Node node) {

    if (node == null) return true;

    int balancedFactor = getBalanceFactor(node);

    if (Math.abs(balancedFactor) > 1) {
      return false;
    }

    return isBalanced(node.left) && isBalanced(node.right);

  }

  public void add(K key, V value) {

    root = add(root, key, value);

  }

  /**
   * Add node.
   *
   * @param node
   * @param key
   * @param value
   * @return
   */
  private Node add(Node node, K key, V value) {

    if (node == null) {
      ++size;
      return new Node(key, value);
    }

    if (node.key.compareTo(key) < 0) {
      node.left = add(node.left, key, value);
    } else if (node.key.compareTo(key) > 0) {
      node.right = add(node.right, key, value);
    } else
      node.value = value;

    node.height = 1 + Math.max(node.right.height, node.left.height);
    int balanceFactor = getBalanceFactor(node);
    // ll
    if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0)
      return rightRotate(node);
    // rr
    if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0)
      return rightRotate(node);
    // lr
    if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
      node.left = leftRotate(node.left);
      return rightRotate(node);
    }
    // rl
    if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
      node.right = rightRotate(node.right);
      return leftRotate(node);
    }
    return node;
  }

  public void remove(K key) {

    remove(root, key);

  }

  private Node remove(Node node, K key) {

    if (node == null) return null;

    Node retNode;
    if (key.compareTo(node.key) < 0) {
      node.left = remove(node.left, key);
      retNode = node;
    } else if (key.compareTo(node.key) > 0) {
      node.right = remove(node.right, key);
      retNode = node;
    } else {
      if (node.left == null) {
        Node rightNode = node.right;
        node.right = null;
        --size;
        retNode = rightNode;
      } else if (node.right == null) {
        Node leftNode = node.left;
        node.left = null;
        --size;
        retNode = leftNode;
      } else {

        Node successor = minimum(node.right);
        successor.right = remove(node.right, successor.key);
        successor.left = node.left;

        node.left = node.right = null;
        retNode = successor;
      }
    }

    if (retNode == null)
      return null;

    retNode.height = 1 + Math.max(retNode.right.height, retNode.left.height);
    int balanceFactor = getBalanceFactor(retNode);
    // ll
    if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0)
      return rightRotate(retNode);
    // rr
    if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0)
      return rightRotate(retNode);
    // lr
    if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
      retNode.left = leftRotate(retNode.left);
      return rightRotate(retNode);
    }
    // rl
    if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
      retNode.right = rightRotate(retNode.right);
      return leftRotate(node);
    }
    return retNode;
  }

  private Node rightRotate(Node y) {
    Node x = y.left;
    Node xRightChild = x.right;
    x.right = y;
    y.left = xRightChild;

    y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
    x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
    // return new root.
    return x;
  }


  private Node leftRotate(Node y) {

    Node x = y.right;
    Node xLeftChild = x.left;
    x.left = y;
    y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
    x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

    return x;
  }

  private Node minimum(Node node) {
    if (node.left == null)
      return node;
    return minimum(node.left);
  }


}
