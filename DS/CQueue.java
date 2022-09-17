package DS;

public class CQueue<E> {
  E[] arr;
  int front, rear;

  public CQueue() { this(20); }

  public CQueue(int cap) {
    arr = (E[]) new Object[cap];
    front = rear = -1;
  }

  public int size() {
    if (front == -1)
      return 0;
    return (rear - front + 1 + arr.length) % arr.length;
  }

  public boolean isEmpty() {
    return front == -1;
  }

  public void enqueue(E item) {
    if ((rear + 1) % arr.length == front)
      throw new IllegalStateException("Full Queue");
    else if (rear == -1)
      arr[front = rear = 0] = item;
    else
      arr[rear = (rear + 1) % arr.length] = item;
  }

  public E dequeue() {
    if (front == -1)
      throw new IllegalStateException("EMPTY");

    E ret = arr[front];
    front = front == rear ? (rear = -1) : (front + 1) % arr.length;
    return ret;
  }

  public E front() {
    if (front == -1)
      throw new IllegalStateException("EMPTY");
    return arr[front];
  }

  public E rear() {
    if (front == -1)
      throw new IllegalStateException("EMPTY");
    return arr[rear];
  }
}
