public class HNode<K, V> implements Entry<K, V> {
  public K key;
  public V value;
  public HNode<K, V> next;

  public HNode(K key, V value, HNode<K, V> next) {
    this.key = key;
    this.value = value;
    this.next = next;
  }

  @Override
  public K getKey() {
    return key;
  }

  @Override
  public V getValue() {
    return value;
  }

  @Override
  public void setKey(K key) {
    this.key = key;
  }

  @Override
  public void setValue(V value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return String.format("(%s, %s)", key, value);
  }
}