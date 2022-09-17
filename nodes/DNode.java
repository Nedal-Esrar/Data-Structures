package nodes;

public class DNode<E> {
  public E data;
  public DNode<E> next, prev;

  public DNode(E data) {
    this(data, null, null);
  }

  public DNode(E data, DNode<E> next, DNode<E> prev) {
    this.data = data;
    this.next = next;
    this.prev = prev;
  }
}
