package ua.com.youtube.utils;

import java.util.*;

public class Dictionary<K,V> implements Map<K,V> {
    private static final int DEFAULT_SIZE = 16;
    private LinkedList<Node<K, V>>[] table;
    private int size;

    public Dictionary() {
        table = new LinkedList[DEFAULT_SIZE];
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            table[i] = new LinkedList<>();
        }
    }

    @Override
    public V put(K key, V value) {
        if (timeToGrewUp()) grewUp();
        if (key == null) throw new NoSuchElementException("Key can not be null");
        if (containsKey(key)) remove(key);

        table[tableIndexByKey(key)].add(new Node<>(key, value));
        size++;
        return value;
    }

    private void grewUp() {
        Set<Entry<K, V>> entries = this.entrySet();
        table = new LinkedList[table.length * 2];
        for (int i = 0; i < table.length; i++) {
            table[i] = new LinkedList<>();
        }
        size = 0;
        for (Entry<K, V> entry : entries) {
            put(entry.getKey(), entry.getValue());
        }
    }

    private boolean timeToGrewUp() {
        return size >= (table.length / 4) * 3;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return findNodeByKey(key).isPresent();
    }

    @Override
    public boolean containsValue(Object value) {
        for (LinkedList<Node<K,V>> list : table) {
            if (list == null) continue;
            for (Node<K, V> node : list)
                if (node.getValue().equals(value))
                    return true;
        }
        return false;
    }

    @Override
    public V get(Object key) {
        return findNodeByKey(key).map(Node::getValue).orElse(null);
    }

    @Override
    public V remove(Object key) {
        Optional<Node<K, V>> optional = findNodeByKey(key);
        if (optional.isPresent()) {
            table[tableIndexByKey(key)].remove(optional.get());
            size--;
            return optional.get().getValue();
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        if (m == null) return;
        m.forEach(this::put);
    }

    @Override
    public String toString() {
        return "Dictionary{" +
                "table=" + Arrays.toString(table) +
                '}';
    }

    @Override
    public void clear() {
        table = new LinkedList[DEFAULT_SIZE];
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for (LinkedList<Node<K, V>> list : table) {
            if (list == null) continue;
            for (Node<K, V> node : list) set.add(node.getKey());
        }
        return set;
    }

    @Override
    public Collection<V> values() {
        ArrayList<V> list = new ArrayList<>();
        for (LinkedList<Node<K, V>> nodes : table) {
            if (nodes == null) continue;
            for (Node<K, V> node : nodes) list.add(node.getValue());
        }
        return list;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> set = new HashSet<>();
        Arrays.stream(table)
                .filter(list -> list.size() > 0)
                .forEach(set::addAll);
        return set;
    }

    private Optional<Node<K, V>> findNodeByKey(Object key) {
        return table[tableIndexByKey(key)]
                .stream()
                .filter(node -> node.getKey().hashCode() == key.hashCode())
                .filter(node -> node.getKey().equals(key))
                .findFirst();
    }

    private int tableIndexByKey(Object key) {
        return Math.abs(key.hashCode()) % table.length;
    }

    private record Node<K, V>(K key, V value) implements Entry<K, V> {
        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<K, V> node = (Node<K, V>) o;
            return Objects.equals(key, node.key) && Objects.equals(value, node.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }

        @Override
        public String toString() {
            return "{key=" + key +
                    ", value=" + value +
                    '}';
        }
    }
}
