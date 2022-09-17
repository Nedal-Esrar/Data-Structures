package nodes;

public class AVLNode<E> {
    public E data;
    public int height;

    public AVLNode<E> parent, left, right;

    public AVLNode(E data) {
        this.data = data;
    }
}
