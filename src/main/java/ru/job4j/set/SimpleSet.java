package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Set<T> {
    private final SimpleArrayList<T> set = new SimpleArrayList<>(0);

    @Override
    public boolean add(T value) {
        boolean ret = !contains(value);
        if (ret) {
            set.add(value);
        }
        return ret;
    }

    @Override
    public boolean contains(T value) {
        boolean ret = false;
        for (T t : this) {
            if (Objects.equals(t, value)) {
                ret = true;
                break;
            }
        }
        return ret;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}
