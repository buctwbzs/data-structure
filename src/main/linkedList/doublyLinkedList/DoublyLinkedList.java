package linkedList.doublyLinkedList;


/**
 * Head️
 * ️↪
 *          -----------   next  ----------- next  ----------- next -----------  next
 *          |  | A |  |  ----> |  | B |  | ----> |  | C |  | ----> |  | D |  |  ---->NULL
 * NULL <-- |  |   |  |  <---- |  |   |  | <---- |  |   |  | <---- |  |   |  |
 *      prev-----------  prev  ----------- prev  ----------- prev  -----------
 * <p>
 * 🤔Why Doubly Linked List?
 * 1) The size of array is fixed.
 * 2) Inserting and deleting Ele is Expensive.
 * <p>
 * 🤓Advantages on arrays:
 * 1) Dynamic size.
 * 2) Ease of insertion and deletion.
 * 3) A DLL can be traversed in both forward and backward direction
 * 4) The delete operation in DLL is more efficient if pointer to the node to be deleted is given.
 * <p>
 * 😩Drawbacks:
 * 1) Random access is not allowed.
 * 2) Extra memory space for a pointer is required with each element of this list.
 * 3) Every node of DLL Require extra space for an previous pointer.
 * 4) All operations require an extra pointer previous to be maintained.
 */

public class DoublyLinkedList<T> {

    Node<T> head;

    static class Node<T> {
        private T data;
        private Node<T> prev;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
            prev = null;
            next = null;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node<T> getPrev() {
            return prev;
        }

        public void setPrev(Node<T> prev) {
            this.prev = prev;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }

    public void setHead(Node<T> head) {
        this.head = head;
    }

    public Node<T> getHead() {
        return head;
    }
    /**
     * Insertion- a node can be added in four ways.
     *
     * 1) At the front of the DLL.
     * 2) At the end of the DLL.
     * 3) After a given node.
     * 4) Before a given node.
     */

    /**
     * At the front of the DLL.
     *
     * @param data
     */
    public void push(T data) {

        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
            return;
        }
        head.setPrev(newNode);
        newNode.setNext(head);
        head = newNode;
    }

    /**
     * At the end of DLL
     *
     * @param data
     */
    public void append(T data) {

        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        }
        Node<T> temp = head;
        while (temp.getNext() != null) {
            temp = temp.getNext();
        }
        temp.setNext(newNode);
        newNode.setPrev(temp);
    }


    /**
     * Before a given node.
     *
     * @param node
     * @param data
     */
    public void insertBefore(Node<T> node, T data) {

        if (node == null) {
            System.out.println("Given node can't be null");
            return;
        }
        Node<T> newNode = new Node<>(data);
        Node<T> prev = node.getPrev();
        newNode.setPrev(prev);
        newNode.setNext(node);
        node.setPrev(newNode);
        if (prev != null){
            prev.setNext(newNode);
        }
    }

    /**
     * After a given node.
     *
     * @param node
     * @param data
     */
    public void insertAfter(Node<T> node, T data) {

        if (node == null) {
            System.out.println("Given node can't be null");
            return;
        }
        Node<T> newNode = new Node<>(data);
        Node<T> next = node.getNext();
        newNode.setPrev(node);
        newNode.setNext(next);
        node.setNext(newNode);
        if (next != null) {
            next.setPrev(newNode);
        }
    }

    /**
     * Deletion- delete a given node in a doubly linked list
     * Algorithm:
     * 1) If node to be deleted is head node, then change the head pointer to next current head.
     * 2) Set next of previous to del, if previous to del exixts.
     * 3) Set prev of next to del, if next to del exixts.
     * Time Complexity: O(1)
     */

    public void delete(Node<T> head, Node<T> node){

        if (head == null || node == null) return;
        if (head == node){
            setHead(node.getNext());
        }
        if (node.next != null) {
            node.getNext().setPrev(node.getPrev());
        }
        if (node.prev != null) {
            node.getPrev().setNext(node.getNext());
        }
        return;
    }

    /**
     * Reverse DLL.
     */
    public void reverse(){

    }

}
