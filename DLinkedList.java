import java.util.Iterator;

public class DLinkedList<E> implements Iterable<E> {
  private DNode<E> head, tail;
  private int sz;

  public void addFirst(E item) {
    add(0, item);
  }

  public void addLast(E item) {
    add(sz, item);
  }

  public void add(int idx, E item) {
    if (idx < 0 || idx > sz)
      throw new IndexOutOfBoundsException();

    if (idx == 0)
      if (head == null)
        head = tail = new DNode<>(item, head, null);
      else
        head.next.prev = head = new DNode<>(item, head, null);
    else if (idx == sz)
      tail.prev.next = tail = new DNode<>(item, null, tail);
    else {
      DNode<E> trav;

      if (idx > sz / 2) {
        trav = tail;
        idx = sz - 1 - idx;

        while (idx-- != 0)
          trav = trav.prev;
      } else {
        trav = head;

        while (idx-- != 0)
          trav = trav.next;
      }

      trav = new DNode<E>(item, trav, trav.prev);
      trav.next.prev = trav;
      trav.prev.next = trav;
    }

    ++sz;
  }

  public E removeFirst() {
    return removeByIndex(0);
  }

  public E removeLast() {
    return removeByIndex(sz - 1);
  }

  public E removeByIndex(int idx) {
    if (idx < 0 || idx >= sz)
      throw new IndexOutOfBoundsException();

    DNode<E> trav;

    if (idx > sz / 2) {
      trav = tail;
      idx = sz - 1 - idx;

      while (idx-- != 0)
        trav = trav.prev;
    } else {
      trav = head;

      while (idx-- != 0)
        trav = trav.next;
    }

    E ret = trav.data;

    if (trav != tail)
      trav.next.prev = trav.prev;
    if (trav != head)
      trav.prev.next = trav.next;

    ++sz;

    return ret;
  }

  public E get(int idx) {
    if (idx < 0 || idx >= sz)
      throw new IndexOutOfBoundsException();

    DNode<E> trav;

    if (idx > sz / 2) {
      trav = tail;
      idx = sz - 1 - idx;

      while (idx-- != 0)
        trav = trav.prev;
    } else {
      trav = head;

      while (idx-- != 0)
        trav = trav.next;
    }

    return trav.data;
  }

  public void set(int idx, E item) {
    if (idx < 0 || idx >= sz)
      throw new IndexOutOfBoundsException();

    DNode<E> trav;

    if (idx > sz / 2) {
      trav = tail;
      idx = sz - 1 - idx;

      while (idx-- != 0)
        trav = trav.prev;
    } else {
      trav = head;

      while (idx-- != 0)
        trav = trav.next;
    }

    trav.data = item;
  }

  public boolean contains(E item) {
    return indexOf(item) != -1;
  }

  public int indexOf(E item) {
    DNode<E> trav = head;

    for (int i = 0; i < sz; ++i, trav = trav.next)
      if (trav.data.equals(item))
        return i;

    return -1;
  }

  public int lastIndexOf(E item) {
    DNode<E> trav = tail;

    for (int i = sz - 1; i > -1; --i, trav = trav.next)
      if (trav.data.equals(item))
        return i;

    return -1;
  }

  public boolean isEmpty() {
    return sz == 0;
  }

  public void clear() {
    head = tail = null;
    sz = 0;
  }

  public DLinkedList<E> clone() {
    if (head == null)
      return new DLinkedList<>();

    DLinkedList<E> clone = new DLinkedList<>();

    DNode<E> t1 = head, t2 = new DNode<>(head.data);

    clone.head = t2;
    while (t1.next != null)
      t2 = t2.next = new DNode<>((t1 = t1.next).data);
    clone.tail = new DNode<>(tail.data);

    return clone;
  }

  public int size() {
    return sz;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("[");

    if (sz != 0) {
      sb.append(head.data);
      for (DNode<E> trav = head.next; trav != null; trav = trav.next)
        sb.append(", ").append(trav.data);
    }

    return sb.append("]").toString();
  }

  @Override
  public Iterator<E> iterator() {
    return new Iterator<>() {
      DNode<E> trav = head;

      @Override
      public boolean hasNext() {
        return trav != null;
      }

      @Override
      public E next() {
        E ret = trav.data;
        trav = trav.next;
        return ret;
      }
    };
  }
}
