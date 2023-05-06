package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {
    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    @SuppressWarnings("unchecked")
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean ret = false;
        int hash = hash(hashCode(key));
        int i = indexFor(hash);
        if (table[i] == null) {
            table[i] = new MapEntry<>(key, value, hash);
            modCount++;
            count++;
            if (count >= (int) (capacity * LOAD_FACTOR)) {
                expand();
            }
            ret = true;
        }
        return ret;
    }

    private int hashCode(K key) {
        return (key == null ? 0 : key.hashCode());
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return table.length - 1 & hash;
    }

    private void expand() {
        table = Arrays.copyOf(table, 2 * table.length);
        for (K k : this) {
            int i = indexFor(hash(hashCode(k)));
            if (i >= capacity) {
                table[i] = table[i - capacity];
                table[i - capacity] = null;
            }
        }
        capacity <<= 1;
    }

    private boolean keyExists(K key, int hash, int i) {
        return (table[i] != null
                && hash == table[i].hash
                && (Objects.equals(key, table[i].key)));
    }

    @Override
    public V get(K key) {
        int hash = hash(hashCode(key));
        int i = indexFor(hash);
        return keyExists(key, hash, i) ? table[i].value : null;
    }

    @Override
    public boolean remove(K key) {
        boolean ret = false;
        int hash = hash(hashCode(key));
        int i = indexFor(hash);
        if (keyExists(key, hash, i)) {
            table[i].key = null;
            table[i].value = null;
            table[i] = null;
            modCount++;
            count--;
            ret = true;
        }
        return ret;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            int cursor;
            final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                while (cursor < capacity && table[cursor] == null) {
                    cursor++;
                }
                return cursor < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[cursor++].key;
            }
        };
    }

    private static class MapEntry<K, V> {
        K key;
        V value;
        int hash;

        public MapEntry(K key, V value, int hash) {
            this.key = key;
            this.value = value;
            this.hash = hash;
        }
    }
}
