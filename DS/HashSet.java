package DS;

import java.util.Iterator;
import java.util.Objects;
import nodes.SNode;

public class HashSet<E> implements Iterable<E> {
  private static final int MAX_CAP = 1 << 30;
  private SNode<E>[] hashTable;
  private int sz, threshold;
  private float loadFactor;

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
    SNode<E>[] oldTable = hashTable;
    hashTable = (SNode<E>[]) new SNode[Math.min(MAX_CAP, 2 * oldTable.length)];
    threshold = (int) (loadFactor * hashTable.length);
    
    for (SNode<E> node : oldTable) {
      while (node != null) {
        add(node.data);
        
        node = node.next;
      }
    }
  }

  public boolean add(E item) {
    if (contains(item)) {
      return false;
      
    ++sz;

    if (hashTable.length < MAX_CAP && sz > threshold)
      rehash();

    int idx = hash(item);
    hashTable[idx] = new SNode<>(item, hashTable[idx]);

    return true;
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
    boolean removed = false;

    if (Objects.equals(hashTable[idx].data, item)) {
      hashTable[idx] = hashTable[idx].next;
      removed = true;
    }
    else {
      SNode<E> trav = hashTable[idx];

      while (trav.next != null) {
        if (Objects.equals(trav.next.data, item)) {
          trav.next = trav.next.next;
          
          removed = true;

          break;
        }

        trav = trav.next;
      }
    }

    if (removed)
      --sz;

    return removed;
  }
    
  public int size() {
    return sz;
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
