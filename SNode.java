public class SNode<E> {
  E data;
  SNode<E> next;

  public SNode(E data) {
    this(data, null);
  }

  public SNode(E data, SNode<E> next) {
    this.data = data;
    this.next = next;
  }
}
