package org.example;

import java.util.Arrays;
import java.util.List;

public class CraftedArrayList<E> {
    private static final long serialVersionUID = 19082024L;
    private transient Object[] elementsData;
    private int sizeArray;
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_DATA = {};
    /**
     * Создает новый CraftedArrayList с указанной начальной емкостью.
     *
     * @param initialCapacity начальная емкость списка
     * @throws IllegalArgumentException если начальная емкость отрицательна
     */
    public CraftedArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementsData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementsData = EMPTY_DATA;
        } else {
            throw new IllegalArgumentException("Размер нашего списка должен быть положительным! Это число: " +
                    initialCapacity + " не подходит");
        }
    }
    /**
     * Создает новый CraftedArrayList с начальной емкостью 0.
     */
    public CraftedArrayList() {
        this.elementsData = EMPTY_DATA;
    }

    /**
     * Создает новый CraftedArrayList, содержащий элементы из переданной коллекции.
     *
     * @param collection коллекция элементов, которые будут добавлены в новый список
     */
    public CraftedArrayList(CraftedArrayList<? extends E> collection) {
        Object[] inputData = collection.toArray();
        if ((sizeArray = inputData.length) != 0) {
            elementsData = Arrays.copyOf(inputData, sizeArray, Object[].class);
        } else {
            elementsData = EMPTY_DATA;
        }
    }
    /**
     * Возвращает количество элементов в этом списке.
     *
     * @return количество элементов в списке
     */
    public int size() {
        return sizeArray;
    }
    /**
     * Проверяет, является ли список пустым.
     *
     * @return true, если список пуст, иначе false
     */
    public boolean isEmpty() {
        return sizeArray == 0;
    }
    /**
     * Проверяет, содержит ли список указанный элемент.
     *
     * @param o элемент для проверки наличия в списке
     * @return true, если элемент присутствует в списке, иначе false
     */
    public boolean contains(Object o) {
        if (sizeArray == 0) {
            return false;
        }
        for (int i = 0; i < sizeArray; i++) {
            if (o.equals(elementsData[i])) {
                return true;
            }
        }
        return false;
    }
    /**
     * Возвращает массив, содержащий все элементы в этом списке.
     *
     * @return массив, содержащий все элементы в списке
     */
    public Object[] toArray() {
        return Arrays.copyOf(elementsData, sizeArray);
    }
    /**
     * Добавляет указанный элемент в конец этого списка.
     *
     * @param e элемент, который необходимо добавить
     * @return true, если элемент был успешно добавлен
     */
    public boolean add(E e) {
        if (sizeArray == elementsData.length) {
            int newCapacity = elementsData.length == 0 ? DEFAULT_CAPACITY : elementsData.length + DEFAULT_CAPACITY;
            elementsData = Arrays.copyOf(elementsData, newCapacity);
        }
        elementsData[sizeArray++] = e;
        return true;
    }
    /**
     * Удаляет первое вхождение указанного элемента из этого списка, если он присутствует.
     *
     * @param o элемент, который необходимо удалить
     * @return true, если элемент был успешно удален
     */
    public boolean remove(Object o) {
        int i = 0;
        if (o == null) {
            for (; i < sizeArray; i++) {
                if (elementsData[i] == null) {
                    break;
                }
            }
        } else {
            for (; i < sizeArray; i++) {
                if (o.equals(elementsData[i])) {
                    break;
                }
            }
        }

        if (i == sizeArray) {
            return false;
        }

        System.arraycopy(elementsData, i + 1, elementsData, i, sizeArray - i - 1);
        elementsData[--sizeArray] = null;
        return true;
    }
    /**
     * Удаляет все элементы из этого списка.
     */
    public void clear() {
        for (int i = 0; i < sizeArray; i++) {
            elementsData[i] = null;
        }
        sizeArray = 0;
    }
    /**
     * Возвращает элемент, находящийся по указанному индексу.
     *
     * @param index индекс элемента, который нужно вернуть
     * @return элемент, находящийся по указанному индексу
     * @throws IndexOutOfBoundsException если индекс выходит за границы списка
     */
    public E get(int index) {
        if (index >= sizeArray || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + sizeArray);
        }
        return (E) elementsData[index];
    }
    /**
     * Заменяет элемент по указанному индексу на указанный элемент.
     *
     * @param index индекс заменяемого элемента
     * @param element элемент, который будет установлен
     * @return элемент, который был заменен
     * @throws IndexOutOfBoundsException если индекс выходит за границы списка
     */
    public E set(int index, E element) {
        if (index >= sizeArray || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + sizeArray);
        }
        E oldValue = (E) elementsData[index];
        elementsData[index] = element;
        return oldValue;
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
        if (fromIndex < 0 || toIndex > sizeArray || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException("fromIndex: " + fromIndex + ", toIndex: " + toIndex);
        }
        Object[] subArray = Arrays.copyOfRange(elementsData, fromIndex, toIndex);
        return (List<E>) Arrays.asList((E[]) subArray);
    }
    /**
     * Добавляет все элементы из указанной коллекции в этот список.
     *
     * @param collection коллекция элементов для добавления
     * @return true, если список был изменен в результате добавления
     */
    public boolean addAll(CraftedArrayList<? extends E> collection) {
        if (collection == null || collection.isEmpty()) {
            return false;
        }

        Object[] inputData = collection.toArray();
        int numNew = inputData.length;

        if (numNew > elementsData.length - sizeArray) {
            int newCapacity = sizeArray + numNew + DEFAULT_CAPACITY;
            elementsData = Arrays.copyOf(elementsData, newCapacity);
        }

        System.arraycopy(inputData, 0, elementsData, sizeArray, numNew);
        sizeArray += numNew;

        return true;
    }
    /**
     * Удаляет из этого списка все элементы, содержащиеся в указанной коллекции.
     *
     * @param c коллекция элементов, которые нужно удалить
     * @return true, если список был изменен в результате удаления
     */
    public boolean removeAll(CraftedArrayList<?> c) {
        boolean modified = false;

        for (int i = 0; i < sizeArray; i++) {
            if (c.contains(elementsData[i])) {
                removeAt(i);
                i--;
                modified = true;
            }
        }

        return modified;
    }
    /**
     * Удаляет элемент из списка по указанному индексу.
     *
     * @param index индекс удаляемого элемента
     */
    private void removeAt(int index) {
        int numMoved = sizeArray - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elementsData, index + 1, elementsData, index, numMoved);
        }
        elementsData[--sizeArray] = null;
    }
    /**
     * Удаляет из этого списка все элементы, кроме тех, которые содержатся в указанной коллекции.
     *
     * @param c коллекция элементов, которые нужно сохранить
     * @return true, если список был изменен в результате операции
     */
    public boolean retainAll(CraftedArrayList<?> c) {
        boolean modified = false;

        for (int i = 0; i < sizeArray; i++) {
            if (!c.contains(elementsData[i])) {
                removeAt(i);
                i--;
                modified = true;
            }
        }

        return modified;
    }
}
