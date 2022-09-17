package DS;

class CDeque {

  private int dq[], front, rear;

  public CDeque(int k) {
    dq = new int[k];
    front = rear = -1;
  }

  public boolean insertFront(int value) {
    if ((front - 1 + dq.length) % dq.length == rear)
      return false;

    dq[front = front == -1 ? (rear = 0) : (front - 1 + dq.length) % dq.length] = value;

    return true;
  }

  public boolean insertLast(int value) {
    if ((rear + 1) % dq.length == front)
      return false;

    dq[rear = front == -1 ? (front = 0) : (rear + 1) % dq.length] = value;

    return true;
  }

  public boolean deleteFront() {
    if (front == -1)
      return false;
    else if (front == rear)
      front = rear = -1;
    else
      front = (front + 1) % dq.length;

    return true;
  }

  public boolean deleteLast() {
    if (front == -1)
      return false;
    else if (front == rear)
      front = rear = -1;
    else
      rear = (rear - 1 + dq.length) % dq.length;

    return true;
  }

  public int getFront() {
    if (front == -1)
      return -1;

    return dq[front];
  }

  public int getRear() {
    if (front == -1)
      return -1;

    return dq[rear];
  }

  public boolean isEmpty() {
    return front == -1;
  }

  public boolean isFull() {
    return (front - 1 + dq.length) % dq.length == rear ||
            (rear + 1) % dq.length == front;
  }
}
