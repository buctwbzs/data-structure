package main.array;

public class DynamicArray<E> {

    private E[] data;
    private int size;

    public DynamicArray(int capacity) {

        data = (E[]) new Object[capacity];
        size = 0;

    }

    public DynamicArray() {

        this(10);

    }

    public int getSize() {

        return size;

    }

    public int getCapacity() {

        return data.length;

    }

    public boolean isEmpty() {

        return size == 0;

    }

    public boolean contains(E e) {

        for (int i = 0; i < size; i++) {
            if (data[i] == e)
                return true;
        }
        return false;
    }

    public int find(E e) {

        for (int i = 0; i < size; i++) {
            if (data[i] == e)
                return i;
        }

        return -1;

    }

    public void addFirst(E e) {

        add(0, e);

    }

    public void addLast(E e) {

        add(size, e);

    }

    public void add(int index, E e) {

        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add Last failed, require index >0 && index < size");

        if (size == data.length) {
            resize(2 * data.length);
        }

        for (int i = size - 1; i >= index; --i) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        ++size;

    }

    public E removeFirst() {

        return remove(0);

    }

    public E removeLast() {

        return remove(size - 1);

    }

    public void removeEle(E e) {

        int index = find(e);
        if (index != -1)
            remove(index);

    }

    public E remove(int index) {

        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Remove Failed. index is illegal.");

        E temp = data[index];
        for (int i = index + 1; i < size; ++i) {
            data[i - 1] = data[i];
        }
        --size;
        data[size] = null; // loitering Object != memory leak

        if (size == data.length / 4 && data.length / 2 != 0)
            resize(data.length / 2);
        return temp;

    }

    public E get(int index) {

        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Get Failed. index is illegal.");

        return data[index];

    }

    private void resize(int newCapacity) {

        E[] newData = (E[]) new Object[newCapacity];

        for (int i = 0; i < size; i++)
            data[i] = data[i];
        data = newData;
    }

    @Override
    public String toString() {

        StringBuilder res = new StringBuilder();
        res.append(String.format("Array:size = %d. capacity: capacity=% d"));
        res.append('[');
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1)
                res.append(",");
        }
        res.append("]");

        return res.toString();

    }
}

