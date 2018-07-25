package main.trie;

import java.util.TreeMap;

public class Trie {

  private Node root;

  private int size;

  private class Node {

    public boolean isWord;

    public TreeMap<Character, Node> next;

    public Node(boolean isWord) {

      this.isWord = isWord;
      this.next = new TreeMap<>();

    }

    public Node() {

      this.isWord = false;

    }

  }

  private Trie() {

    root = new Node();
    size = 0;

  }

  public int getSize() {

    return size;

  }

  /**
   * Add word to Trie.
   *
   * @param word
   */
  public void add(String word) {

    Node curr = root;

    for (int i = 0; i < word.length(); i++) {

      char c = word.charAt(i);

      if (root.next.get(c) == null)
        curr.next.put(word.charAt(i), new Node());

      curr = curr.next.get(c);

    }
    // If curr is not end, set true and maintain size.
    if (!curr.isWord) {

      curr.isWord = true;
      ++size;

    }
  }

  /**
   * Add word to Trie by recursive.
   *
   * @param word
   */
  public void addByRecursive(String word) {

    int index = 0;

    addByRecursive(index, word, root);

  }

  private void addByRecursive(int index, String word, Node node) {

    if (index == word.length()) {

      if (!node.isWord) {

        node.isWord = true;
        ++size;

      }

      return;

    }

    char c = word.charAt(index);

    if (node.next.get(c) == null) {

      node.next.put(c, new Node());

    }

    addByRecursive(++index, word, node.next.get(c));

  }
}
