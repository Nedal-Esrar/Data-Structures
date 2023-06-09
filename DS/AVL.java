package DS;

import nodes.AVLNode;

public class AVL<E extends Comparable<E>> {
  private AVLNode<E> root;

  private int size;

  private int getHeight(AVLNode<E> node) {
    return node == null ? 0 : node.height;
  }

  private int getBalanceFactor(AVLNode<E> node) {
    return node == null ? 0 : getHeight(node.left) - getHeight(node.right);
  }

  private int updateHeight(AVLNode<E> node) {
    return Math.max(getHeight(node.left), getHeight(node.right)) + 1;
  }

  private AVLNode<E> rotateLeft(AVLNode<E> x) {
    AVLNode<E> z = x.right, t2 = z.left;

    z.left = x;
    x.right = t2;

    x.height = updateHeight(x);
    z.height = updateHeight(z);

    return z;
  }

  private AVLNode<E> rotateRight(AVLNode<E> x) {
    AVLNode<E> z = x.left, t2 = z.right;

    z.right = x;
    x.left = t2;

    x.height = updateHeight(x);
    z.height = updateHeight(z);

    return z;
  }

  private AVLNode<E> balance(AVLNode<E> node) {
    int bf = getBalanceFactor(node);

    if (bf > 1) {
      if (getBalanceFactor(node.left) < 0) {
        node = rotateLeft(node.left);
      }

      node = rotateRight(node);
    } else if (bf < -1) {
      if (getBalanceFactor(node.right) > 0) {
        node = rotateRight(node.right);
      }

      node = rotateLeft(node);
    }

    return node;
  }

  public boolean insert(E value) {
    if (this.contains(value)) {
      return false;
    }

    root = insert(root, value);

    ++size;

    return true;
  }

  private AVLNode<E> insert(AVLNode<E> root, E value) {
    if (root == null) {
      return new AVLNode<>(value);
    }

    int cmp = value.compareTo(root.data);

    if (cmp < 0) {
      root.left = insert(root.left, value);
    } else {
      root.right = insert(root.right, value);
    }

    updateHeight(root);

    return balance(root);
  }

  public boolean delete(E value) {
    if (!this.contains(value)) {
      return false;
    }

    root = delete(root, value);

    --size;

    return true;
  }

  private E getMinValue(AVLNode<E> node) {
    while (node.left != null) {
      node = node.left;
    }

    return node.data;
  }

  private AVLNode<E> delete(AVLNode<E> root, E value) {
    int cmp = value.compareTo(root.data);

    if (cmp < 0) {
      root.left = delete(root.left, value);
    } else if (cmp > 0) {
      root.right = delete(root.right, value);
    } else {
      if (root.left == null && root.right == null) {
        return null;
      } else if (root.left == null) {
        return root.right;
      } else if (root.right == null) {
        return root.left;
      } else {
        E rightSubtreeMinValue = getMinValue(root.right);

        root.data = rightSubtreeMinValue;

        root.right = delete(root.right, rightSubtreeMinValue);
      }
    }

    updateHeight(root);

    return balance(root);
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public boolean contains(E value) {
    AVLNode<E> trav = root;

    while (trav != null) {
      int cmp = trav.data.compareTo(value);

      if (trav.data.compareTo(value) == 0) {
        return true;
      }

      if (cmp > 0) {
        trav = trav.left;
      } else {
        trav = trav.right;
      }
    }

    return false;
  }
}
