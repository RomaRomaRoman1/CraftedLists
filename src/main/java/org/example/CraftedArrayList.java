package org.example;

import java.util.Arrays;
import java.util.List;

public class CraftedArrayList<E> {
    private static final long serialVersionUID = 19082024L;
    private transient Object[] elementsData;
    private int sizeArray;
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_DATA = {};

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

    public CraftedArrayList() {
        this.elementsData = EMPTY_DATA;
    }

    // Конструктор с коллекцией, реализуем без использования методов Collection
    public CraftedArrayList(CraftedArrayList<? extends E> collection) {
        Object[] inputData = collection.toArray();
        if ((sizeArray = inputData.length) != 0) {
            elementsData = Arrays.copyOf(inputData, sizeArray, Object[].class);
        } else {
            elementsData = EMPTY_DATA;
        }
    }

    public int size() {
        return sizeArray;
    }

    public boolean isEmpty() {
        return sizeArray == 0;
    }

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

    public Object[] toArray() {
        return Arrays.copyOf(elementsData, sizeArray);
    }

    public boolean add(E e) {
        if (sizeArray == elementsData.length) {
            int newCapacity = elementsData.length == 0 ? DEFAULT_CAPACITY : elementsData.length + DEFAULT_CAPACITY;
            elementsData = Arrays.copyOf(elementsData, newCapacity);
        }
        elementsData[sizeArray++] = e;
        return true;
    }

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

    public void clear() {
        for (int i = 0; i < sizeArray; i++) {
            elementsData[i] = null;
        }
        sizeArray = 0;
    }

    public E get(int index) {
        if (index >= sizeArray || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + sizeArray);
        }
        return (E) elementsData[index];
    }

    public E set(int index, E element) {
        if (index >= sizeArray || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + sizeArray);
        }
        E oldValue = (E) elementsData[index];
        elementsData[index] = element;
        return oldValue;
    }

    public List<E> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > sizeArray || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException("fromIndex: " + fromIndex + ", toIndex: " + toIndex);
        }
        Object[] subArray = Arrays.copyOfRange(elementsData, fromIndex, toIndex);
        return (List<E>) Arrays.asList((E[]) subArray);
    }

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

    private void removeAt(int index) {
        int numMoved = sizeArray - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elementsData, index + 1, elementsData, index, numMoved);
        }
        elementsData[--sizeArray] = null;
    }

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