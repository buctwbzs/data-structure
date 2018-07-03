
package main.stack;

import main.array.DynamicArray;

/**
 * 🛠Mainly the following three basic operations are performed in the stack:
 * <p>
 * Push: Adds an item in the stack. If the stack is full, then it is said to be an Overflow condition.
 * Pop: Removes an item from the stack. The items are popped in the reversed order in which they are pushed. If the stack is empty, then it is said to be an Underflow condition.
 * Peek or Top: Returns top element of stack.
 * isEmpty: Returns true if stack is empty, else false
 * all take O(1) time
 */


public class ArrayStack<E> implements Stack<E> {

    DynamicArray<E> array;

    public ArrayStack(int capacity) {
        array = new DynamicArray<>(capacity);
    }

    public ArrayStack() {
        array = new DynamicArray<>(10);
    }

    @Override
    public void push(E e) {

        array.addLast(e);
    }

    @Override
    public E pop() {
        return array.removeLast();
    }

    @Override
    public E peek() {
        return array.getLast();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    public int getCapacity() {
        return array.getCapacity();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack: ");
        res.append("[");
        for (int i = 0; i < array.getSize(); i++) {
            res.append(array.get(i));
            if (i != array.getSize() - 1)
                res.append(", ");
        }
        res.append("] top");
        return res.toString();
    }
}

