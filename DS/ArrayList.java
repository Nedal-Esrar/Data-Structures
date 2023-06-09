package DS;

import java.util.Iterator;

import static java.lang.System.arraycopy;
import static java.util.Arrays.copyOf;

public class ArrayList<E> implements Iterable<E> {
  private E[] arr;
  private int sz;

  public ArrayList() {
    this(10);
  }

  public ArrayList(int initCap) {
    arr = (E[]) new Object[initCap];
  }

  public ArrayList(E[] items) {
    arr = copyOf(items, items.length);
  }

  public int size() {
    return sz;
  }

  public void addFirst(E item) {
    add(0, item);
  }

  public void addLast(E item) {
    add(sz, item);
  }

  public void add(int idx, E item) {
    if (idx < 0 || idx > sz) {
      throw new IndexOutOfBoundsException();
    }

    if (sz == arr.length) {
      arr = copyOf(arr, 2 * sz);
    }

    if (idx != sz) {
      arraycopy(arr, idx, arr, idx + 1, sz - idx);
    }

    arr[idx] = item;

    ++sz;
  }

  public E removeFirst() {
    return removeAtIndex(0);
  }

  public E removeLast() {
    return removeAtIndex(sz - 1);
  }

  public E removeAtIndex(int idx) {
    if (idx < 0 || idx >= sz) {
      throw new IndexOutOfBoundsException();
    }

    E ret = arr[idx];

    if (idx != sz - 1) {
      arraycopy(arr, idx + 1, arr, idx, sz - idx);
    }

    arr[--sz] = null;

    return ret;
  }

  public boolean removeValue(E item) {
    for (int i = 0; i < sz; ++i) {
      if (arr[i].equals(item)) {
        removeAtIndex(i);

        return true;
      }
    }

    return false;
  }

  public E get(int idx) {
    if (idx < 0 || idx >= sz) {
      throw new IndexOutOfBoundsException();
    }

    return arr[idx];
  }

  public void set(int idx, E item) {
    if (idx < 0 || idx >= sz) {
      throw new IndexOutOfBoundsException();
    }

    arr[idx] = item;
  }

  public boolean contains(E item) {
    return indexOf(item) != -1;
  }

  public int indexOf(E item) {
    for (int i = 0; i < sz; ++i) {
      if (arr[i].equals(item)) {
        return i;
      }
    }

    return -1;
  }

  public int lastIndexOf(E item) {
    for (int i = sz - 1; i > -1; --i) {
      if (arr[i].equals(item)) {
        return i;
      }
    }

    return -1;
  }

  public void trimToSize() {
    if (sz != arr.length) {
      arr = copyOf(arr, sz);
    }
  }

  public void clear() {
    for (int i = 0; i < sz; ++i) {
      arr[i] = null;
    }

    sz = 0;
  }

  public ArrayList<E> clone() {
    return new ArrayList<>(this.arr);
  }

  public boolean isEmpty() {
    return sz == 0;
  }

  public void swap(int i, int j) {
    E tmp = arr[j];
    arr[j] = arr[i];
    arr[i] = tmp;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("[");

    if (sz != 0) {
      sb.append(arr[0]);

      for (int i = 1; i < sz; ++i)
        sb.append(", ").append(arr[i]);
    }

    return sb.append("]").toString();
  }

  public Iterator<E> iterator() {
    return new Iterator<>() {
      private int idx;

      @Override
      public boolean hasNext() {
        return idx != sz;
      }

      @Override
      public E next() {
        return arr[idx++];
      }
    };
  }
}
