package test.com.buctwbzs.dataStructure.linkedList;


import com.buctwbzs.dataStructure.linkedList.LinkedList;

public class LinkedListTest {

    public static void main(String[] args) {

        LinkedList<Integer> list = new LinkedList<>();

        list.push(0);
        list.append(1);
        list.append(2);
        list.toString();
        list.swapNodes(1, 2);
        list.toString();
    }
}
