import java.util.Iterator;
import java.util.Objects;

public class HashMap<K, V> implements Iterable<Entry<K, V>> {
  private static final int MAX_CAP = 1 << 30;
  float loadFactor;
  int sz, threshold;
  HNode<K, V>[] hashTable;

  public HashMap() {
    this(1.0f, 10);
  }

  public HashMap(float loadFactor) {
    this(loadFactor, 10);
  }

  public HashMap(int initCap) {
    if (initCap < 0)
      throw new IllegalStateException("Not a valid capacity");

    this(1.0f, initCap);
  }

  public HashMap(float loadFactor, int initCap) {
    if (initCap < 0)
      throw new IllegalStateException("Not a valid capacity");

    this.loadFactor = loadFactor;
    hashTable = (HNode<K, V>[]) new HNode[initCap];
    threshold = (int) (initCap * loadFactor);
  }

  private int hash(K key) {
    return Math.abs(Objects.hashCode(key)) % hashTable.length;
  }

  private void rehash() {
    HNode<K, V>[] oldTab = hashTable;
    hashTable = (HNode<K, V>[]) new HNode[Math.min(MAX_CAP, 2 * hashTable.length)];
    threshold = (int) (loadFactor * hashTable.length);

    HNode<K, V> nxt;
    for (int i = 0, idx; i < oldTab.length; ++i)
      while (oldTab[i] != null) {
        idx = hash(oldTab[i].key);
        nxt = oldTab[i].next;
        oldTab[i].next = hashTable[idx];
        hashTable[idx] = oldTab[i];
        oldTab[i] = nxt;
      }
  }

  public int size() {
    return sz;
  }

  public void put(K key, V value) {
    int idx = hash(key);

    HNode<K, V> trav = hashTable[idx];
    while (trav != null) {
      if (Objects.equals(trav.key, key)) {
        trav.value = value;
        return;
      }
      trav = trav.next;
    }

    hashTable[idx] = new HNode<>(key, value, hashTable[idx]);

    ++sz;

    if (hashTable.length < MAX_CAP && sz > threshold)
      rehash();
  }

  public V get(K key) {
    int idx = hash(key);

    HNode<K, V> trav = hashTable[idx];

    while (trav != null) {
      if (Objects.equals(trav.key, key))
        return trav.value;

      trav = trav.next;
    }

    return null;
  }

  public V remove(K key) {
    int idx = hash(key);
    V oldVal = null;

    if (Objects.equals(hashTable[idx].key, key)) {
      oldVal = hashTable[idx].value;
      hashTable[idx] = hashTable[idx].next;
      --sz;
    } else {
      HNode<K, V> trav = hashTable[idx];
      while (trav.next != null) {
        if (Objects.equals(trav.next.key, key)) {
          oldVal = trav.next.value;
          trav.next = trav.next.next;
          --sz;
          break;
        }

        trav = trav.next;
      }
    }

    return oldVal;
  }

  public boolean containsKey(K key) {
    int idx = hash(key);

    HNode<K, V> trav = hashTable[idx];
    while (trav != null) {
      if (Objects.equals(trav.key, key))
        return true;
      trav = trav.next;
    }

    return false;
  }

  public boolean containsValue(V value) {
    for (Entry<K, V> entry : this)
      if (Objects.equals(entry.getValue(), value))
        return true;

    return false;
  }

  @Override
  public Iterator<Entry<K, V>> iterator() {
    return new Iterator<>() {
      int cnt, idx = -1;
      HNode<K, V> trav;

      @Override
      public boolean hasNext() {
        return cnt != sz;
      }

      @Override
      public Entry<K, V> next() {
        ++cnt;

        while (trav == null)
          trav = hashTable[++idx];

        Entry<K, V> ret = trav;
        trav = trav.next;
        return ret;
      }
    };
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("[");
    Iterator<Entry<K, V>> it = iterator();

    if (it.hasNext()) {
      sb.append(it.next());
      while (it.hasNext())
        sb.append(", ").append(it.next());
    }

    return sb.append("]").toString();
  }
}
