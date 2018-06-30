package com.buctwbzs.dataStructure.bst;

/**
 * BST impl.
 *
 * @param <E>
 */

public class BST<E extends Comparable<E>> {


    // Node Element inner class
    private class Node {

        E data;
        Node left, right;

        public Node(E data) {
            this.data = data;
            left = right = null;
        }
    }


    private Node root;
    private int size;

    public BST() {

        root = null;
        size = 0;
    }

    public int size() {

        return size;
    }

    public boolean isEmpty() {

        return size == 0;
    }

    // Add a Node in BST.
    public void add(E data) {

        root = add(root, data);
    }

    private Node add(Node curr, E data) {

        if (curr == null) {
            ++size;
            return new Node(data);
        }
        if (data.compareTo(curr.data) < 0) {
            curr.left = add(curr.left, data);
        } else if (data.compareTo(curr.data) > 0) {
            curr.right = add(curr.right, data);
        }
        return curr;
    }

    // Whether a BST contains a Node whose data equals the given data.
    public boolean contains(E data) {

        return contains(root, data);
    }

    public boolean contains(Node node, E data) {

        if (node == null) return false;
        if (data.compareTo(node.data) == 0) {
            return true;
        } else {
            return contains(node.left, data) || contains(node.right, data);
        }
    }

    // pre order traverse
    public void proOrderTraverse() {

        perOrderTraverse(root);
    }

    private void perOrderTraverse(Node node) {

        if (node == null) return;

        System.out.println(node.data);
        perOrderTraverse(node.left);
        perOrderTraverse(node.right);
    }

    // mid order traverse
    public void inOrderTraverse() {

        inOrderTraverse(root);
    }

    private void inOrderTraverse(Node node) {

        if (node == null) return;

        System.out.println(node.data);
        inOrderTraverse(node.left);
        inOrderTraverse(node.right);
    }
}
