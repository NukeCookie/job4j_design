package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        int index = getIndex(key);
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    private int getIndex(K key) {
        int hCode = key == null ? 0 : key.hashCode();
        return indexFor(hash(hCode));
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (table.length - 1);
    }

    private void expand() {
        MapEntry<K, V>[] oldTable = table;
        capacity *= 2;
        table = new MapEntry[capacity];
        for (MapEntry<K, V> el : oldTable) {
            if (el != null) {
                table[getIndex(el.key)] = el;
            }
        }
    }

    @Override
    public V get(K key) {
        V value = null;
        MapEntry<K, V> el = table[getIndex(key)];
        if (el != null) {
            if (key == null) {
                value = el.value;
            } else if (el.key.hashCode() == key.hashCode()
                && el.key.equals(key)) {
                value = el.value;
            }
        }
        return value;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int index = getIndex(key);
        MapEntry<K, V> el = table[index];
        if (el != null) {
            if (key == null) {
                table[index] = null;
                count--;
                modCount++;
                rsl = true;
            } else if (el.key.hashCode() == key.hashCode()
                && el.key.equals(key)) {
                table[index] = null;
                count--;
                modCount++;
                rsl = true;
            }
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            private int expectedModCount = modCount;
            private int point = 0;
            MapEntry<K, V>[] tIterator = table;

            @Override
            public boolean hasNext() {
                boolean rsl = false;
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (point < tIterator.length) {
                    while (tIterator[point] == null && point < tIterator.length - 1) {
                        point++;
                    }
                    rsl = tIterator[point] != null;
                }
                return rsl;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return tIterator[point++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

}