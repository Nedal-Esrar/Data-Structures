package DS;

import nodes.SNode;

public class LQueue<E> {
  SNode<E> front, rear;
  int sz;

  public int size() {
    return sz;
  }

  public boolean isEmpty() {
    return sz == 0;
  }

  public void enqueue(E item) {
    if (rear == null)
      rear = front = new SNode<>(item);
    else
      rear.next = new SNode<>(item);

    ++sz;
  }

  public E dequeue() {
    if (sz == 0)
      throw new IllegalStateException();

    E ret = front.data;

    front = front.next;
    if (sz-- == 1)
      rear = null;

    return ret;
  }

  public E front() {
    if (sz == 0)
      throw new IllegalStateException();

    return front.data;
  }

  public E rear() {
    if (sz == 0)
      throw new IllegalStateException();

    return rear.data;
  }
}
