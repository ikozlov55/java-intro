package exercises.ch27.base;

public interface MyMap<K, V> {
    /**
     * Remove all of the entries from this map
     */
    void clear();

    /**
     * Return true if the specified key is in the map
     */
    boolean containsKey(K key);

    /**
     * Return true if this map contains the specified value
     */
    boolean containsValue(V value);

    /**
     * Return a set of entries in the map
     */
    java.util.Set<Entry<K, V>> entrySet();

    /**
     * Return the first value that matches the specified key
     */
    V get(K key);

    /**
     * Return true if this map contains no entries
     */
    boolean isEmpty();

    /**
     * Return a set consisting of the keys in this map
     */
    java.util.Set<K> keySet();

    /**
     * Add an entry (key, value) into the map
     */
    V put(K key, V value);

    /**
     * Remove the entries for the specified key
     */
    void remove(K key);

    /**
     * Return the number of mappings in this map
     */
    int size();

    /**
     * Return a set consisting of the values in this map
     */
    java.util.Set<V> values();

    /**
     * Define inner class for Entry
     */
    class Entry<K, V> {
        K key;
        V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "[" + key + ", " + value + "]";
        }
    }
}
