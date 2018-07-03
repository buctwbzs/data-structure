package main.queue;


/**
 * front == tail, empty.
 * front == tail + 1, full.
 *
 * @param <E>
 */

public class LoopQueue<E> implements Queue<E> {

    private E[] data;
    private int front, tail;
    private int size;

    public LoopQueue(int capacity) {

        this.data = (E[]) new Object[capacity + 1];

    }

    public LoopQueue() {

        this.data = (E[]) new Object[10];

    }

    @Override
    public void enqueue(E e) {

        if (front == (tail + 1) % data.length)
            resize(getCapacity() * 2);
        data[tail] = e;
        tail = (tail + 1) % data.length;
        ++size;

    }

    @Override
    public E dequeue() {

        if (isEmpty()) throw new IllegalArgumentException("Queue is empty");

        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        --size;
        if (size == getCapacity() / 4 && getCapacity() / 2 != 0)
            resize(getCapacity() / 2);

        return ret;

    }

    @Override
    public E getFront() {

        if (isEmpty()) throw new IllegalArgumentException("Queue is empty");
        return data[front];

    }

    private void resize(int newCapacity) {

        E[] newData = (E[]) new Object[newCapacity + 1];

        for (int i = 0; i < size; i++) {
            newData[i] = data[(front + 1) % data.length];
        }
        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public int getSize() {

        return size;

    }

    @Override
    public boolean isEmpty() {

        return front == tail;

    }

    public int getCapacity() {

        return data.length - 1;

    }

    @Override
    public String toString() {

        StringBuilder res = new StringBuilder();
        res.append("LoopQueue: ");
        res.append("front [");
        for (int i = 0; i != tail; i = (i + 1) % data.length) {
            res.append(data[i]);
            if (i != size - 1)
                res.append(", ");
        }
        res.append("] tail");
        return res.toString();
    }
}
