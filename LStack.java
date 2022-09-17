import java.util.EmptyStackException;

public class LStack<E> {
  private SNode<E> top;
  private int sz;

  public int size() {
    return sz;
  }

  public void push(E item) {
    top = new SNode<>(item, top);
  }

  public E pop() {
    if (sz == 0)
      throw new EmptyStackException();

    E ret = top.data;
    top = top.next;
    return ret;
  }

  public E peek() {
    if (sz == 0)
      throw new EmptyStackException();

    return top.data;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("[");

    if (sz != 0) {
      sb.append(top.data);
      for (SNode<E> trav = top.next; trav != null; trav = trav.next)
        sb.append(", ").append(trav.data);
    }

    return sb.append("]").toString();
  }
}
