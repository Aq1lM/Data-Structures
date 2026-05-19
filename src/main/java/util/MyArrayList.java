package util;

@SuppressWarnings("unchecked")
public class MyArrayList<T> implements MyList<T> {

    private int size = 0;
    private int capacity = 0;
    private T[] storage;

    /**
     * <p>constructor of the list that helps to create list with the given capacity</p>
     *
     * @param capacity initial capacity of the created list
     */
    public MyArrayList(int capacity) {
        this.capacity = capacity;

        storage = (T[]) new Object[capacity];
    }

    /**
     * <p>constructor of the list that helps to create list with the default (10) capacity</p>
     */
    public MyArrayList() {
        this(10);
    }

    /**
     * <p>return true, if the list has not any element</p>
     *
     * @return list emptiness
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * <p>return true, if the param exists in list</p>
     *
     * @param t element that checked if it exists
     * @return element existence
     */
    @Override
    public boolean contains(T t) {
        return indexOf(t) != -1;
    }

    /**
     * <p>add the param to list</p>
     *
     * @param t element that must be added
     */
    @Override
    public void add(T t) {
        if (size() >= capacity) capacity = capacity == 0 ? 1 : capacity * 2;

        T[] newStorage = (T[]) new Object[size() + 1];

        System.arraycopy(storage, 0, newStorage, 0, size());

        newStorage[size++] = t;
        storage = newStorage;
    }

    /**
     * <p>add param to specified index in list</p>
     *
     * @param index index that element must be added there
     * @param t     added element
     */
    @Override
    public void add(int index, T t) {
        if (index < 0 || index > size()) throw new IllegalArgumentException("Index input must be within list size");
        if (index == size()) add(t);
        else {
            T[] newStorage = (T[]) new Object[size() + 1];
            for (int i = 0, j = 0; i < size(); i++, j++) {
                if (j == index) {
                    newStorage[j] = t;
                    i--;
                } else {
                    newStorage[j] = storage[i];
                }
            }
            storage = newStorage;
            size++;
        }
    }

    /**
     * <p>make the list entirely empty</p>
     */
    @Override
    public void clear() {
        for (T t : storage) t = null;
        size = capacity = 0;
    }

    /**
     * <p>remove all the elements that equals() to param</p>
     *
     * @param t element that must be removed
     */
    @Override
    public void removeAll(T t) {
        for (int i = 0; i < size(); i++) {
            if (storage[i].equals(t)) remove(i);
        }
    }

    /**
     * <p>remove the specified indexed element</p>
     *
     * @param index index of element that must be removed
     */
    @Override
    public void remove(int index) {
        if (index < 0 || index >= size()) throw new IllegalArgumentException("Index input must be within list size");

        T[] newStorage = (T[]) new Object[size - 1];

        for (int i = 0, j = 0; i < size(); i++, j++) {
            if (i == index) j--;
            else newStorage[j] = storage[i];
        }
        storage = newStorage;
        size--;
    }

    /**
     * <p>return the size of list</p>
     *
     * @return size of list
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * <p>return the index of the first element that equals() to param</p>
     * <p>if the element does not exist in the list, it returns -1</p>
     *
     * @param t found element
     * @return index of the element
     */
    @Override
    public int indexOf(T t) {
        int index = -1;
        for (int i = 0; i < storage.length; ++i) {
            if (storage[i].equals(t)) {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * <p>return the index of the last element that equals() to param</p>
     * <p>if the element does not exist in the list, it returns -1</p>
     *
     * @param t found element
     * @return last index of element
     */
    @Override
    public int lastIndexOf(T t) {
        int index = -1;
        for (int i = storage.length - 1; i >= 0; --i) {
            if (storage[i].equals(t)) {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * <p>update the value of the specified indexed element</p>
     *
     * @param index updated index
     * @param t     updated value that placed at the index
     * @return T updated version of data
     */
    @Override
    public T update(int index, T t) {
        if (index < 0 || index >= size()) throw new IllegalArgumentException("Index input must be within list size");

        storage[index] = t;

        return t;
    }

    /**
     * <p>return the element at the specified index</p>
     *
     * @param index index of the element to return
     * @return return the element at the mentioned index
     */
    @Override
    public T get(int index) {
        if (index < 0 || index >= size()) throw new IllegalArgumentException("Index input must be within list size");

        return storage[index];
    }

    /**
     * <p>return the first element of list</p>
     *
     * @return return the first element
     */
    @Override
    public T getFirst() {
        return get(0);
    }

    /**
     * <p>return the last element of list</p>
     *
     * @return return the last element
     */
    @Override
    public T getLast() {
        return get(size() - 1);
    }

    /**
     * <p>return the list that within fromIndex and toIndex</p>
     *
     * @param fromIndex start index in the main list
     * @param toIndex   end index in the main list
     * @return new partial piece of the list
     */
    @Override
    public MyList<T> subList(int fromIndex, int toIndex) {
        if ((fromIndex < 0 || fromIndex >= size()) && (toIndex < 0 || toIndex >= size()))
            throw new IllegalArgumentException("Index input must be within list size");
        if (fromIndex > toIndex) throw new IllegalArgumentException("Start index cannot be greater than end index");

        MyList<T> subList = new MyArrayList<>(toIndex - fromIndex);

        for (int i = fromIndex; i < toIndex; i++) {
            subList.add(get(i));
        }
        return subList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");

        if (!isEmpty()) {
            for (int i = 0; i < size() - 1; i++) {
                sb.append(get(i)).append(", ");
            }
            sb.append(getLast());
        }
        return sb.append("}").toString();
    }
}
