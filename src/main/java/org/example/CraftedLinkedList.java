package org.example;

import java.util.*;

public class CraftedLinkedList<E> {
    private int size = 0;
    private MyNode<E> first;
    private MyNode<E> last;

    private class MyNode<T> {
        T data;
        MyNode<T> next;
        MyNode<T> prev;

        MyNode(MyNode<T> prev, T data, MyNode<T> next) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }

    public void addFirst(E e) {
        final MyNode<E> f = first;
        final MyNode<E> newNode = new MyNode<>(null, e, f);

        first = newNode;
        if (f == null) {
            last = newNode;
        } else {
            f.prev = newNode;
        }
        size++;
    }

    public void addLast(E e) {
        final MyNode<E> l = last;
        final MyNode<E> newNode = new MyNode<>(l, e, null);

        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    public E removeFirst() {
        if (first == null) {
            throw new NoSuchElementException("List is empty");
        }

        MyNode<E> oldFirst = first;
        E element = oldFirst.data;
        first = oldFirst.next;

        if (first == null) {
            last = null;
        } else {
            first.prev = null;
        }

        size--;
        oldFirst.next = null;
        oldFirst.prev = null;
        return element;
    }

    public E removeLast() {
        if (last == null) {
            throw new NoSuchElementException("List is empty");
        }

        MyNode<E> oldLast = last;
        E element = oldLast.data;
        last = oldLast.prev;

        if (last == null) {
            first = null;
        } else {
            last.next = null;
        }

        size--;
        return element;
    }

    public E get(int index) {
        checkElementIndex(index);
        return node(index).data;
    }

    public void set(int index, E element) {
        checkElementIndex(index);
        node(index).data = element;
    }

    public List<E> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > size || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException();
        }

        List<E> subList = new ArrayList<>();
        for (int i = fromIndex; i < toIndex; i++) {
            subList.add(get(i));
        }
        return subList;
    }

    public int size() {
        return size;
    }

    public void clear() {
        MyNode<E> current = first;
        while (current != null) {
            MyNode<E> next = current.next;
            current.data = null;
            current.next = null;
            current.prev = null;
            current = next;
        }
        first = last = null;
        size = 0;
    }

    private MyNode<E> node(int index) {
        if (index < (size >> 1)) {
            MyNode<E> x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x;
        } else {
            MyNode<E> x = last;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
            return x;
        }
    }

    private void checkElementIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
}
