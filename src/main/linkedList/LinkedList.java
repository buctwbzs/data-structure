package main.linkedList;


/**
 * Head
 * --------     --------     --------     --------
 * | A |  | --> | B |  | --> | C |  | --> | D |  | --> NULL
 * --------     --------     --------     --------
 * <p>
 * 🤔Why Linked List?
 * 1) The size of array is fixed.
 * 2) Inserting and deleting Ele is Expensive.
 * <p>
 * 🤓Advantages on arrays:
 * 1) Dynamic size.
 * 2) Ease of insertion and deletion.
 * <p>
 * 😩Drawbacks:
 * 1) Random access is not allowed.
 * 2) Extra memory space for a pointer is required with each element of this list.
 */

public class LinkedList<E> {

    // Head of list.
    private Node<E> head;

    public static class Node<E> {
        private Node<E> next;
        private E data;


        /**
         * Constructor
         */
        public Node(E data, Node next) {

            this.data = data;
            this.next = next;

        }

        public Node(E data) {

            this.data = data;
            this.next = null;

        }

        public Node() {

            this(null, null);

        }

        public Node<E> getNext() {

            return next;

        }

        public void setNext(Node<E> next) {

            this.next = next;

        }

        public void setData(E data) {
            this.data = data;
        }

        public E getData() {
            return data;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    public LinkedList() {
        head = null;
    }

    public Node<E> getHead() {
        return head;
    }


    /**
     * Insert a new Node at the front.
     *
     * @param newData
     */
    public void push(E newData) {

        Node<E> newNode = new Node<>(newData);
        newNode.setNext(head);
        head = newNode;
    }

    /**
     * Append a node at the end.
     *
     * @param newData
     */
    public void append(E newData) {

        Node<E> newNode = new Node<>(newData);
        if (head == null) {
            head = newNode;
            return;
        }
        Node<E> last = head;
        while (last.getNext() != null) {
            last = last.getNext();
        }
        last.setNext(newNode);
        return;
    }

    /**
     * Insert a new Node after a given node.
     *
     * @param newData
     */
    public void insertAfter(Node<E> prev, E newData) {

        if (prev == null) {
            System.out.println("The given prev node can't be null");
            return;
        }
        Node<E> newNode = new Node<>(newData);
        Node<E> next = prev.getNext();
        newNode.setNext(next);
        prev.setNext(newNode);
    }

    /**
     * Given a ‘key’, delete the first occurrence of this key in linked list.
     *
     * @param key
     */
    public void delete(E key) {

        Node<E> temp = head, prev = null;
        if (temp != null && temp.getData() == key) {
            head = temp.next;
            return;
        }
        while (temp != null && temp.getData() != key) {
            prev = temp;
            temp = temp.getNext();
        }
        if (temp == null) return;
        prev.setNext(temp.getNext());
    }

    /**
     * Delete a linked list node at the given position.
     *
     * @param idx
     */
    public void remove(int idx) {
        Node<E> temp = head, prev = null;
        int i = 0;
        if (idx == 0) {
            head = temp.getNext();
            return;
        }
        while (temp != null && i < idx - 1) {
            ++i;
            temp = temp.getNext();
        }
        if (temp == null || temp.getNext() == null) return;
        Node<E> next = temp.getNext().getNext();
        temp.setNext(next);
    }

    /**
     * Get Length of a Linked List(Iterative)
     *
     * @return len
     */
    public int getLength() {

        Node<E> temp = head;
        int len = 0;

        while (temp != null) {
            temp = temp.getNext();
            ++len;
        }
        return len;
    }

    /**
     * Get Length of a Linked List(Recursive)
     *
     * @return len
     */

    public int getLengthByRecursive(Node<E> head) {
        if (head == null) return 0;
        return 1 + getLengthByRecursive(head.getNext());
    }

}
