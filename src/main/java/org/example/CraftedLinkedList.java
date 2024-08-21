package org.example;

import java.util.*;

public class CraftedLinkedList<E> {
    private int size = 0;
    private MyNode<E> first;
    private MyNode<E> last;
    /**
     * Внутренний класс, представляющий узел в связанном списке.
     * @param <T> тип данных, хранящихся в узле
     */
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
    /**
     * Добавляет элемент в начало списка.
     *
     * @param e элемент для добавления
     */
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
    /**
     * Добавляет элемент в конец списка.
     *
     * @param e элемент для добавления
     */
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
    /**
     * Удаляет и возвращает первый элемент из списка.
     *
     * @return первый элемент списка
     * @throws NoSuchElementException если список пуст
     */
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
    /**
     * Удаляет и возвращает последний элемент из списка.
     *
     * @return последний элемент списка
     * @throws NoSuchElementException если список пуст
     */
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
    /**
     * Возвращает элемент, находящийся по указанному индексу.
     *
     * @param index индекс элемента, который нужно вернуть
     * @return элемент, находящийся по указанному индексу
     * @throws IndexOutOfBoundsException если индекс выходит за границы списка
     */
    public E get(int index) {
        checkElementIndex(index);
        return node(index).data;
    }
    /**
     * Заменяет элемент по указанному индексу на указанный элемент.
     *
     * @param index индекс заменяемого элемента
     * @param element элемент, который будет установлен
     * @throws IndexOutOfBoundsException если индекс выходит за границы списка
     */
    public void set(int index, E element) {
        checkElementIndex(index);
        node(index).data = element;
    }
    /**
     * Возвращает часть списка от указанного начального индекса (включительно) до конечного индекса (исключительно).
     *
     * @param fromIndex начальный индекс (включительно)
     * @param toIndex конечный индекс (исключительно)
     * @return новый список, содержащий элементы из указанного диапазона
     * @throws IndexOutOfBoundsException если индексы выходят за границы списка
     */
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
    /**
     * Возвращает количество элементов в списке.
     *
     * @return количество элементов в списке
     */
    public int size() {
        return size;
    }
    /**
     * Очищает список, удаляя все элементы.
     */
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
    /**
     * Возвращает узел, находящийся по указанному индексу.
     *
     * @param index индекс узла
     * @return узел, находящийся по указанному индексу
     */
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
    /**
     * Проверяет, находится ли индекс в допустимом диапазоне.
     *
     * @param index индекс для проверки
     * @throws IndexOutOfBoundsException если индекс выходит за границы списка
     */
    private void checkElementIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
}
