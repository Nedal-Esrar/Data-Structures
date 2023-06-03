package DS;

class CircularDeque {
  int dq[], front, rear;

  public CircularDeque(int k) {
    dq = new int[k];
    front = rear = -1;
  }

  public boolean insertFront(int value) {
    if (isFull()) {
      return false;
    }

    if (isEmpty()) {
      front = rear = 0;
    } else {
      front = (front - 1 + dq.length) % dq.length;
    }

    dq[front] = value;

    return true;
  }

  public boolean insertLast(int value) {
    if (isFull()) {
      return false;
    }

    if (isEmpty()) {
      front = rear = 0;
    } else {
      rear = (rear + 1) % dq.length;
    }

    dq[rear] = value;

    return true;
  }

  public boolean deleteFront() {
    if (isEmpty()) {
      return false;
    }

    if (front == rear) {
      front = rear = -1;
    } else {
      front = (front + 1) % dq.length;
    }

    return true;
  }

  public boolean deleteLast() {
    if (isEmpty()) {
      return false;
    }

    if (front == rear) {
      front = rear = -1;
    } else {
      rear = (rear - 1 + dq.length) % dq.length;
    }

    return true;
  }

  public int getFront() {
    return isEmpty() ? -1 : dq[front];
  }

  public int getRear() {
    return isEmpty() ? -1 : dq[rear];
  }

  public boolean isEmpty() {
    return front == -1;
  }

  public boolean isFull() {
    return (front - 1 + dq.length) % dq.length == rear ||
        (rear + 1) % dq.length == front;
  }
}
