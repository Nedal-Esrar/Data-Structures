package DS;

import java.util.Arrays;
import java.util.EmptyStackException;

public class AStack<E> {
  private E[] arr;
  private int sz;

  public AStack() {
    this(10);
  }

  public AStack(int initCap) {
    arr = (E[]) new Object[initCap];
  }

  public int size() {
    return sz;
  }

  public void push(E item) {
    if (sz == arr.length)
      arr = Arrays.copyOf(arr, 2 * sz);

    arr[sz++] = item;
  }

  public boolean isEmpty() {
    return sz == 0;
  }

  public E pop() {
    if (sz == 0)
      throw new EmptyStackException();

    E ret = arr[--sz];
    arr[sz] = null;
    return ret;
  }

  public E peek() {
    if (sz == 0)
      throw new EmptyStackException();

    return arr[sz - 1];
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
}
