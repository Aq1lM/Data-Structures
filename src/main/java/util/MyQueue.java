package util;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class MyQueue<T> implements Iterable<T> {

    private final java.util.LinkedList<T> storage = new LinkedList<>();

    public MyQueue() {
    }

    public int size() {
        return storage.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean add(T t) {
        storage.addLast(t);
        return true;
    }

    public boolean offer(T t) {
        if (exists(t)) return false;

        return add(t);
    }

    public T remove() {
        if (isEmpty())
            throw new NoSuchElementException("Queue is empty");

        return storage.removeFirst();
    }

    public T poll() {
        if (isEmpty()) return null;

        return remove();
    }

    public T getFirst() {
        if (isEmpty())
            throw new NoSuchElementException("Queue is empty");
        return storage.getFirst();
    }

    public T peek() {
        if (isEmpty()) return null;

        return getFirst();
    }

    @Override
    public Iterator<T> iterator() {
        return storage.iterator();
    }

    @Override
    public String toString() {
        return storage.toString();
    }

    private boolean exists(T t) {
        return storage.contains(t);
    }
}
