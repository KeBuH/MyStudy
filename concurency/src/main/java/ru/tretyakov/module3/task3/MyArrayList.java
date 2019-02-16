package ru.tretyakov.module3.task3;

import java.io.Serializable;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * TODO comment.
 * @author Rooter
 * @since 03.02.19
 **/

public class MyArrayList<E> implements SimpleList<E>, Iterable {

    private int size;
    private int capacity = 3;
    private int modCount = 0;
    private Object[] container = new Object[capacity];

    @Override
    public int size() {
        return this.size;
    }

    public MyArrayList() {
    }

    public MyArrayList(final Object[] container, final int size) {
        this.container = container;
        this.size = size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator<E>(this.modCount);
    }

    private class MyIterator<E> implements Iterator<E>, Serializable {

        private int cursor;
        private int innner = 0;

        public MyIterator(int modCount) {
            this.innner = modCount;
        }

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public E next() {
            if (this.innner != modCount) {
                throw new ConcurrentModificationException("some error message");
            }
            return (E) container[this.cursor++];
        }
    }

    @Override
    public boolean add(final E o) {
        if (size == container.length) {
            container = grow();
        }
        this.container[size++] = o;
        this.modCount++;
        return true;
    }

    public E get(final int index) {
        try {
            return (E) this.container[index];
        } catch (Exception e) {
            throw new NoSuchElementException(e.getMessage());
        }
    }

    @Override
    public boolean contains(E value) {
        boolean result = false;
        for (Object o : this.container) {
            if (value.equals(o)) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean remove(final int index) {
        boolean operationResult = true;
        if (this.size == 0) {
            operationResult = false;
        }
        if (this.size < index) {
            throw new NoSuchElementException(String.valueOf(index));
        }
        Object[] result = new Object[container.length - 1];
        int focus = 0;
        for (int target = 0; target < this.size - 1; target++, focus++) {
            if (target == index) {
                result[target] = container[++focus];
            } else {
                result[target] = container[focus];
            }
        }
        this.size--;
        this.container = result;
        this.modCount++;
        return operationResult;
    }

    private Object[] grow() {
        return copy(container, new Object[container.length * 2]);
    }

    private Object[] copy(final Object[] from, final Object[] to) {
        System.arraycopy(from, 0, to, 0, from.length);
        return to;
    }

    public MyArrayList<E> clone() {
        return new MyArrayList<>(this.container.clone(), this.size);
    }
}
