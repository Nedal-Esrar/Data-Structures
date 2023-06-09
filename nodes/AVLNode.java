package nodes;

public class AVLNode<E> {
    public E data;
    public int height;

    public AVLNode<E> left, right;

    public AVLNode(E data) {
        this.data = data;
    }
}
