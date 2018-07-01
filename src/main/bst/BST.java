package bst;

import java.util.LinkedList;
import java.util.Queue;

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
    public void preOrderTraverse() {

        preOrderTraverse(root);
    }

    private void preOrderTraverse(Node node) {

        if (node == null) return;

        System.out.println(node.data);
        preOrderTraverse(node.left);
        preOrderTraverse(node.right);
    }

    // In order traverse
    public void inOrderTraverse() {

        inOrderTraverse(root);
    }

    private void inOrderTraverse(Node node) {

        if (node == null) return;

        inOrderTraverse(node.left);
        System.out.println(node.data);
        inOrderTraverse(node.right);
    }

    // Post order traverse
    public void postOrderTraverse() {

        postOrderTraverse(root);
    }

    private void postOrderTraverse(Node node) {

        if (node == null) return;

        postOrderTraverse(node.left);
        postOrderTraverse(node.right);
        System.out.println(node.data);
    }


    /**
     * BFS traverse
     * ******************************************************
     *
     *        0
     *      /  \
     *     0    0
     *    / \  / \
     *   0  0 0  0
     *
     *******************************************************/
    public void BFSTraverse() {

        Queue<Node> queue = new LinkedList<>();

        queue.add(root);

        while (!queue.isEmpty()) {
            Node curr = queue.remove();
            System.out.println(curr.data);
            if (curr.left != null)
                queue.add(curr.left);
            if (curr.right != null)
                queue.add(curr.right);
        }
    }


    /**
     * Delete and return node whose value is max;
     * ******************************************************
     * 1: Tree is empty.
     *
     *    root == null
     *
     * 2:root is maxNode
     *
     *       0
     *      / \
     *     0 null
     *    /
     *   0
     *
     * 3: maxNode has't left child
     *
     *       0
     *      / \
     *     0   0
     *    /   / \
     *   0  null null
     *
     * 4: maxNode has left child
     *
     *       0
     *      / \
     *     0   0
     *    / \  /\
     *   0  0 0  null
     *******************************************************/

    public E removeMax() {

        E e = findMax();
        root = removeMax(root);
        return e;
    }

    // Return BST's root.
    private Node removeMax(Node node) {

        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            --size;
            return leftNode;
        }
        node.right = removeMin(node.right);
        return node;
    }


    /**
     * Delete and return node whose value is min;
     * *****************************************
     * 1: Tree is empty.
     *
     *      root == null
     *
     * 2:root is maxNode
     *
     *      0
     *     / \
     *   null 0
     *     \
     *      0
     *      <p>
     * 3: maxNode has't left child
     *
     *       0
     *      / \
     *     0   0
     *    /
     *   0
     *
     * 4: maxNode has left child
     *
     *       0
     *      / \
     *     0   0
     *    / \  /
     *   0  0 0
     ******************************************/

    public E removeMin() {

        E min = findMin();
        root = removeMin(root);
        return min;
    }

    private Node removeMin(Node node) {

        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            --size;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }


    /**
     * Remove random node.
     * *************************************************
     **************************************************/


    // Find Node whose value is max
    public E findMax() {

        if (size() == 0)
            throw new IllegalArgumentException("BST is empty");
        return findMax(root).data;
    }

    private Node findMax(Node node) {

        if (node.left == null)
            return node;
        return findMax(node.left);
    }

    // Find Node whose value is min
    public E findMin() {

        if (size() == 0)
            throw new IllegalArgumentException("BST is empty");
        return findMin(root).data;
    }

    private Node findMin(Node node) {

        if (node.right == null)
            return node;
        return findMin(node.right);
    }
}
