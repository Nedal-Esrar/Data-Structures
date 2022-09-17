class Node<E> {
  E data;
  int height;

  Node<E> parent, left, right;

  public Node(E data) {
    this.data = data;
  }
}

public class AVL<E extends Comparable> {
  private Node<E> root;

  private int setHeights(Node<E> node) {
    if (node == null)
      return 0;

    if (node.left == null && node.right == null)
      return node.height = 1;

    return node.height = 1 + Math.max(setHeights(node.left), setHeights(node.right));
  }

  private void rotateLeft(Node<E> x) {
    Node<E> pX = x.parent, y = x.right, rY = y.right;

    y.parent = pX;
    if (pX == null)
      root = y;
    else if (pX.right == x)
      pX.right = y;
    else
      pX.left = y;

    x.parent = y;
    y.left = x;
    if (rY != null)
      rY.parent = x;
    x.right = rY;
  }

  private void rotateRight(Node<E> x) {
    Node<E> pX = x.parent, y = x.left, lY = y.left;

    y.parent = pX;
    if (pX == null)
      root = y;
    else if (pX.left == x)
      pX.left = y;
    else
      pX.right = y;

    x.parent = y;
    y.right = x;
    if (lY != null)
      lY.parent = x;
    x.left = lY;
  }

  private void rebalance(Node<E> node) {
    int slh, srh,
        lh = node.left != null ? node.left.height : 0,
        rh = node.right != null ? node.right.height : 0;

    Node<E> p = node.parent, n;

    if (lh - rh > 1) {
      n = node.left;
      slh = n.left != null ? n.left.height : 0;
      srh = n.right != null ? n.right.height : 0;
      if (srh > slh)
        rotateLeft(n);
      rotateRight(node);
      setHeights(root);
    } else if (lh - rh < -1) {
      n = node.right;
      slh = n.left != null ? n.left.height : 0;
      srh = n.right != null ? n.right.height : 0;
      if (slh > srh)
        rotateRight(n);
      rotateLeft(node);
      setHeights(root);
    }

    if (p != null)
      rebalance(p);
  }


}
