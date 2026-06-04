package util;

import java.util.Iterator;
import java.util.LinkedList;

public class MyStack<T> implements Iterable<T> {

    private java.util.LinkedList<T> storage = new LinkedList<>();

    public MyStack() {
    }

    public int size() {
        return storage.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void push(T t) {
        storage.addLast(t);
    }

    public T pop() {
        if (isEmpty())
            throw new RuntimeException("Stack is empty");
        return storage.removeLast();
    }

    public T peek() {
        if (isEmpty())
            throw new RuntimeException("Stack is empty");
        return storage.peekLast();
    }

    public int search(T t) {
        return storage.indexOf(t);
    }

    @Override
    public String toString() {
        return storage.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return storage.iterator();
    }
}
