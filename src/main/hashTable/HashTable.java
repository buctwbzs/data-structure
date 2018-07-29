package main.hashTable;

import java.util.TreeMap;

public class HashTable<K, V> {

  private static int[] capcities = {
          53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593, 49157, 98317,
          196613, 393241, 786433, 1572869, 3145739, 6291469, 12582917, 25165843,
          50331653, 100663319, 201326611, 402653189, 805306457, 1610612741
  };
  private static final int upper = 10;
  private static final int lower = 2;
  private static int capacityIdx = 0;

  private int m;
  private int size;
  private TreeMap<K, V>[] hashTable;


  public HashTable(int m) {

    this.m = capcities[capacityIdx];
    size = 0;

    for (int i = 0; i < m; i++) {
      hashTable[i] = new TreeMap<>();
    }

  }


  private int hash(K key) {
    return key.hashCode() & 0x7fffffff % m;
  }

  public int getSize() {
    return size;
  }

  public void add(K key, V value) {

    TreeMap<K, V> map = hashTable[hash(key)];

    if (map.containsKey(key)) {
      map.put(key, value);
    } else {
      map.put(key, value);
      ++size;
      if (size >= upper * m && capacityIdx + 1 < capcities.length) {
        capacityIdx++;
        resize(capcities[capacityIdx]);
      }
    }

  }

  public V remove(K key) {

    TreeMap<K, V> map = hashTable[hash(key)];
    V value = null;

    if (map.containsKey(key)) {
      value = map.remove(key);
      --size;
      if (size <= lower * m && capacityIdx - 1 >= 0) {
        --capacityIdx;
        resize(capcities[capacityIdx]);

      }
    }
    return value;
  }

  public void set(K key, V value) {

    TreeMap<K, V> map = hashTable[hash(key)];

    if (!map.containsKey(key)) {
      throw new IllegalArgumentException(key + "doesn't exist!");
    }

    map.put(key, value);

  }

  public boolean contain(K key) {

    return hashTable[hash(key)].containsKey(key);

  }

  public V get(K key) {

    return hashTable[hash(key)].get(key);

  }

  private void resize(int newM) {

    TreeMap<K, V>[] newHashTable = new TreeMap[newM];

    for (int i = 0; i < newM; i++) {
      newHashTable[i] = new TreeMap<>();
    }

    int oldM = m;
    this.m = newM;

    for (int i = 0; i < oldM; i++) {
      TreeMap<K, V> map = hashTable[i];

      for (K key : map.keySet()) {
        newHashTable[hash(key)].put(key, map.get(key));
      }
      this.hashTable = newHashTable;
    }
  }

}
