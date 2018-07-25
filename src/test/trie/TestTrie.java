package test.trie;

import main.trie.Trie;

public class TestTrie {

  public static void main(String[] args) {

    String s = "hahah";
    String ss = "hahahh";

    String a = "a";

    String sa = "sa";

    Trie trie = new Trie();

    trie.add(s);
    trie.add(ss);
    trie.addByRecursive(a);
    trie.addByRecursive(sa);

    System.out.println(trie.contains(s));
    System.out.println(trie.contains(ss));
    System.out.println(trie.contains(a));
    System.out.println(trie.contains(sa));

  }

}
