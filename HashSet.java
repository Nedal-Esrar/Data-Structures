import java.util.Iterator;
import java.util.Objects;

public class HashSet<E> implements Iterable<E> {
  private static final int MAX_CAP = 1 << 30;
  public SNode<E>[] hashTable;
  int sz, threshold;
  float loadFactor;

  public HashSet(float loadFactor, int initCap) {
    if (initCap < 0)
      throw new IllegalStateException("Not a valid capacity");

    this.loadFactor = loadFactor;
    hashTable = (SNode<E>[]) new SNode[initCap];
    threshold = (int) (initCap * loadFactor);
  }

  public HashSet(int initCap) {
    this(1.0f, initCap);
  }

  public HashSet(float loadFactor) {
    this(loadFactor, 10);
  }

  public HashSet() {
    this(1.0f, 10);
  }

  public int size() {
    return sz;
  }

  @Override
  public Iterator<E> iterator() {
    return new Iterator<>() {
      int cnt, idx = -1;
      SNode<E> trav;

      @Override
      public boolean hasNext() {
        return cnt != sz;
      }

      @Override
      public E next() {
        ++cnt;

        while (trav == null)
          trav = hashTable[++idx];

        E ret = trav.data;
        trav = trav.next;

        return ret;
      }
    };
  }

  private int hash(E item) {
    return Math.abs(Objects.hashCode(item)) % hashTable.length;
  }

  private void rehash() {
    SNode<E>[] oldTab = hashTable;
    hashTable = (SNode<E>[]) new SNode[Math.min(MAX_CAP, 2 * hashTable.length)];
    threshold = (int) (loadFactor * hashTable.length);

    SNode<E> nxt;
    for (int i = 0, idx; i < oldTab.length; ++i)
      while (oldTab[i] != null) {
        idx = hash(oldTab[i].data);
        nxt = oldTab[i].next;
        oldTab[i].next = hashTable[idx];
        hashTable[idx] = oldTab[i];
        oldTab[i] = nxt;
      }
  }

  public boolean add(E item) {
    if (!contains(item)) {
      ++sz;

      if (hashTable.length < MAX_CAP && sz > threshold)
        rehash();

      int idx = hash(item);
      hashTable[idx] = new SNode<>(item, hashTable[idx]);

      return true;
    }

    return false;
  }

  public boolean contains(E item) {
    int idx = hash(item);
    SNode<E> trav = hashTable[idx];

    while (trav != null) {
      if (Objects.equals(item, trav.data))
        return true;

      trav = trav.next;
    }

    return false;
  }

  public boolean remove(E item) {
    int idx = hash(item);
    boolean del = false;

    if (Objects.equals(hashTable[idx].data, item)) {
      hashTable[idx] = hashTable[idx].next;
      del = true;
    }
    else {
      SNode<E> trav = hashTable[idx];

      while (trav.next != null) {
        if (Objects.equals(trav.next.data, item)) {
          trav.next = trav.next.next;
          del = true;

          break;
        }

        trav = trav.next;
      }
    }

    if (del)
      --sz;

    return del;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("[");
    Iterator<E> it = iterator();

    if (it.hasNext()) {
      sb.append(it.next());
      while (it.hasNext())
        sb.append(", ").append(it.next());
    }

    return sb.append("]").toString();
  }
}
