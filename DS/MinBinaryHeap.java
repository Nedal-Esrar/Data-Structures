package DS;

public class MinBinaryHeap<E extends Comparable<E>> {
  private ArrayList<E> heap;

  public MinBinaryHeap() {
    heap = new ArrayList<>();
  }

  private int getParentIndex(int i) {
    return (i - 1) / 2;
  }

  private int getLeftChildIndex(int i) {
    return 2 * i + 1;
  }

  private int getRightChildIndex(int i) {
    return 2 * i + 2;
  }

  public E getMin() {
    if (heap.isEmpty()) {
      throw new IllegalStateException();
    }

    return heap.get(0);
  }

  public void insert(E value) {
    heap.addLast(value);

    int idx = heap.size() - 1;

    while (idx != 0 && heap.get(idx).compareTo(heap.get(getParentIndex(idx))) < 0) {
      int parentIndex = getParentIndex(idx);

      heap.swap(idx, parentIndex);

      idx = parentIndex;
    }
  }

  private void minHeapify(int idx) {
    int l = getLeftChildIndex(idx);
    int r = getRightChildIndex(idx);

    int minIdx = idx;

    if (l < heap.size() && heap.get(l).compareTo(heap.get(minIdx)) < 0) {
      minIdx = l;
    }

    if (r < heap.size() && heap.get(r).compareTo(heap.get(minIdx)) < 0) {
      minIdx = l;
    }
    
    if (minIdx != idx) {
      heap.swap(idx, minIdx);
      
      minHeapify(minIdx);
    }
  }

  public E extractMin() {
    if (heap.isEmpty()) {
      throw new IllegalStateException();
    }

    if (heap.size() == 1) {
      return heap.removeFirst();
    }

    E ret = heap.get(0);

    heap.set(0, heap.removeLast());

    minHeapify(0);

    return ret;
  }

  public boolean isEmpty() {
    return heap.isEmpty();
  }
}
