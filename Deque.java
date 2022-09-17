public class Deque<E> {
  DNode<E> front, rear;
  int sz;

  public int size() {
    return sz;
  }

  public boolean isEmpty() {
    return sz == 0;
  }

  public void enqueueFront(E item) {
    if (front == null)
      front = rear = new DNode<>(item);
    else
      front = front.prev = new DNode<>(item, front, null);

    ++sz;
  }

  public void enqueueRear(E item) {
    if (rear == null)
      front = rear = new DNode<>(item);
    else
      rear = rear.next = new DNode<>(item, null, rear);

    ++sz;
  }

  public E dequeueFront() {
    if (sz == 0)
      throw new IllegalStateException();

    E ret = front.data;
    (front = front.prev).next = null;

    --sz;

    return ret;
  }

  public E dequeueRear() {
    if (sz == 0)
      throw new IllegalStateException();

    E ret = rear.data;
    (rear = rear.next).prev = null;

    --sz;

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
