package util;

public class MyLinkedList<T> implements MyList<T> {

    private int size = 0;
    private Node<T> head = null;
    private Node<T> tail = null;

    private static class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        public Node(T data, Node<T> prev, Node<T> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(T t) {
        return indexOf(t) != -1;
    }

    @Override
    public void add(T t) {
        addLast(t);
    }

    @Override
    public void add(int index, T t) {
        if (index < 0 || index > size()) throw new IllegalArgumentException("Index input must be within list size");

        if (isEmpty()) {
            head = tail = new Node<>(t, null, null);
            size++;
        } else if (index == 0) {
            addFirst(t);
        } else if (index == size()) {
            addLast(t);
        } else {
            if (index < size() / 2) {
                int count = 0;
                Node<T> trav = head;
                while (trav != null) {
                    if (count == index) {
                        Node<T> temp = new Node<>(t, trav.prev, trav);
                        trav.prev.next = temp;
                        trav.prev = temp;

                        temp = null;
                        break;
                    }
                    trav = trav.next;
                    count++;
                }
            } else {
                int count = size() - 1;
                Node<T> trav = tail;
                while (trav != null) {
                    if (count == index) {
                        Node<T> temp = new Node<>(t, trav.prev, trav);
                        trav.prev.next = temp;
                        trav.prev = temp;

                        temp = null;
                        break;
                    }
                    trav = trav.prev;
                    count--;
                }
            }
            size++;
        }
    }

    public void addFirst(T t) {
        if (isEmpty()) head = tail = new Node<>(t, null, null);
        else {
            head.prev = new Node<>(t, null, head);
            head = head.prev;
        }
        size++;
    }

    public void addLast(T t) {
        if (isEmpty()) head = tail = new Node<>(t, null, null);
        else {
            tail.next = new Node<>(t, tail, null);
            tail = tail.next;
        }
        size++;
    }

    @Override
    public void clear() {
        Node<T> temp = head;
        while (temp != null) {
            Node<T> next = temp.next;
            temp.next = null;
            temp.data = null;
            temp = next;
        }
        head = null;
        size = 0;
    }

    @Override
    public void removeAll(T t) {
        if (isEmpty()) throw new RuntimeException("Empty list");

        Node<T> trav = head;
        while (trav != null) {
            Node<T> next = trav.next;

            if (trav.data.equals(t)) {
                delete(trav);
            }
            trav = next;
        }
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= size()) throw new IllegalArgumentException("Index input must be within list size");
        if (isEmpty()) throw new RuntimeException("Empty list");

        if (index == 0) {
            removeFirst();
        } else if (index == size() - 1) {
            removeLast();
        } else {
            int count = 0;
            Node<T> trav = head;

            while (trav != null) {
                if (count == index) {
                    delete(trav);
                    break;
                }
                trav = trav.next;
                count++;
            }
        }
    }

    public void removeFirst() {
        delete(head);
    }

    public void removeLast() {
        delete(tail);
    }

    @Override
    public int indexOf(T t) {
        int index = -1;

        if (isEmpty()) return index;

        Node<T> trav = head;
        int temp = 0;

        while (trav != null) {
            if (trav.data.equals(t)) {
                index = temp;
                break;
            }
            temp++;
            trav = trav.next;
        }
        return index;
    }

    @Override
    public int lastIndexOf(T t) {
        int index = -1;

        if (isEmpty()) return index;

        Node<T> trav = tail;
        int temp = size() - 1;

        while (trav != null) {
            if (trav.data.equals(t)) {
                index = temp;
                break;
            }
            temp--;
            trav = trav.prev;
        }
        return index;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T update(int index, T t) {
        if (index < 0 || index >= size()) throw new IllegalArgumentException("Index input must be within list size");

        if (isEmpty()) throw new RuntimeException("List is empty");

        if (index < size() / 2) {
            int count = 0;
            Node<T> trav = head;
            while (trav != null) {
                if (count == index) {
                    trav.data = t;
                    break;
                }
                trav = trav.next;
                count++;
            }
        } else {
            int count = size() - 1;
            Node<T> trav = tail;
            while (trav != null) {
                if (count == index) {
                    trav.data = t;
                    break;
                }
                trav = trav.prev;
                count--;
            }
        }
        return t;
    }

    @Override
    public T get(int index) {
        return getNode(index).data;
    }

    @Override
    public T getFirst() {
        if (isEmpty()) throw new RuntimeException("Empty list");
        return head.data;
    }

    @Override
    public T getLast() {
        if (isEmpty()) throw new RuntimeException("Empty list");
        Node<T> trav = tail;
        return trav.data;
    }

    @Override
    public MyLinkedList<T> subList(int fromIndex, int toIndex) {
        if ((fromIndex < 0 || fromIndex >= size()) || (toIndex < 0 || toIndex >= size()))
            throw new IllegalArgumentException("indexes must be within list");

        if ((fromIndex > toIndex))
            throw new IllegalArgumentException("start index cannot be greater that end index");

        MyLinkedList<T> subList = new MyLinkedList<>();

        for (Node<T> node = getNode(fromIndex); node != getNode(toIndex); node = node.next) subList.add(node.data);

        return subList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        if (!isEmpty()) {
            Node<T> trav = head;
            while (trav != null) {
                sb.append(trav).append(", ");
                trav = trav.next;
            }
            sb.deleteCharAt(sb.length() - 1).deleteCharAt(sb.length() - 1);
        }
        return sb.append("}").toString();
    }

    private void delete(Node<T> node) {
        Node<T> prev = node.prev;
        Node<T> next = node.next;

        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
            node.prev = null;
        }

        if (next == null) {
            tail = prev;
        } else {
            next.prev = prev;
            node.next = null;
        }

        node.data = null;

        size--;
    }

    private Node<T> getNode(int index) {
        if (index < 0 || index >= size()) throw new IllegalArgumentException("Index input must be within list size");

        if (isEmpty()) throw new RuntimeException("List is empty");

        if (index == 0) getFirst();
        if (index == size() - 1) getLast();

        Node<T> trav = head;
        int temp = 0;

        while (trav != null) {
            if (temp == index) {
                break;
            }
            temp++;
            trav = trav.next;
        }
        return trav;
    }
}
