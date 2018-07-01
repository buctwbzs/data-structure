package test.com.buctwbzs.dataStructure.bst;

import bst.BST;

public class BSTTest {

    public static void main(String[] args) {

        BST<Integer> bst = new BST<>();
        int[] nums = new int[]{5, 3, 6, 8, 4, 2};

        // Test add
        for (int i = 0; i < 5; i++) {
            bst.add(i);
        }


        // Test contains
        for (int i = 0; i < 5; i++) {
            System.out.println(bst.contains(i));
        }
        System.out.println("pre order traverse begin");
        // Test pre order traverse
        bst.preOrderTraverse();

        // Test in order traverse
        System.out.println("in order traverse begin");
        bst.inOrderTraverse();

        // Test post order traverse
        System.out.println("post order traverse begin");
        bst.postOrderTraverse();
    }
}
