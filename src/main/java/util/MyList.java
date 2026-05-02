package util;

public interface MyList<T> {

    boolean isEmpty();

    boolean contains(T t);

    void add(T t);

    void add(int index, T t);

    void clear();

    void removeAll(T t);

    void remove(int index);

    int indexOf(T t);

    int lastIndexOf(T t);

    int size();

    T update(int index, T t);

    T get(int index);

    T getFirst();

    T getLast();

    MyList<T> subList(int fromIndex, int toIndex);

}
