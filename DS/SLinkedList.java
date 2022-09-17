package DS;

import java.util.Iterator;
import nodes.SNode;

public class SLinkedList<E> implements Iterable<E> {
  private SNode<E> head, tail;
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

    if (idx == 0) {
      head = new SNode<>(item, head);
      if (sz == 0)
        tail = head;
    } else if (idx == sz)
      tail = tail.next = new SNode<>(item);
    else {
      SNode<E> trav = head;
      while (idx-- != 1)
        trav = trav.next;
      trav.next = new SNode<>(item, trav.next);
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

    E ret;

    if (idx == 0) {
      ret = head.data;
      head = head.next;
    } else {
      SNode<E> trav = head;
      while (idx-- != 1)
        trav = trav.next;

      if (trav.next == tail)
        tail = trav;

      ret = trav.data;
      trav.next = trav.next.next;
    }

    --sz;
    return ret;
  }

  public E get(int idx) {
    if (idx < 0 || idx >= sz)
      throw new IndexOutOfBoundsException();

    SNode<E> trav = head;
    while (idx-- != 0)
      trav = trav.next;

    return trav.data;
  }

  public void set(int idx, E item) {
    if (idx < 0 || idx >= sz)
      throw new IndexOutOfBoundsException();

    SNode<E> trav = head;
    while (idx-- != 0)
      trav = trav.next;

    trav.data = item;
  }

  public boolean contains(E item) {
    return indexOf(item) != -1;
  }

  public int indexOf(E item) {
    SNode<E> trav = head;

    for (int i = 0; i < sz; ++i, trav = trav.next)
      if (trav.data.equals(item))
        return i;

    return -1;
  }

  public int lastIndexOf(E item) {
    int idx = -1, i = 0;

    for (SNode<E> trav = head; i < sz; trav = trav.next, ++i)
      if (trav.data.equals(item))
        idx = i;

    return idx;
  }

  public boolean isEmpty() {
    return sz == 0;
  }

  public void clear() {
    head = tail = null;
    sz = 0;
  }

  public SLinkedList<E> clone() {
    if (head == null)
      return new SLinkedList<>();

    SLinkedList<E> clone = new SLinkedList<>();

    SNode<E> t1 = head, t2 = new SNode<>(head.data);

    clone.head = t2;
    while (t1.next != null)
      t2 = t2.next = new SNode<>((t1 = t1.next).data);
    clone.tail = new SNode<>(tail.data);

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
      for (SNode<E> trav = head.next; trav != null; trav = trav.next)
        sb.append(", ").append(trav.data);
    }

    return sb.append("]").toString();
  }



  public void reverse() {
    tail = head;
    reverse(null, head);
  }

  private void reverse(SNode<E> prev, SNode<E> curr) {
    if (curr.next == null)
      curr.next = prev;
    else {
      head = curr.next;
      curr.next = prev;
      reverse(curr, head);
    }
  }

  @Override
  public Iterator<E> iterator() {
    return new Iterator<E>() {
      private SNode<E> trav = head;

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
