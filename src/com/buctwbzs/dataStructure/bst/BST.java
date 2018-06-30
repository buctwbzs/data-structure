package com.buctwbzs.dataStructure.bst;

/**
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
}
