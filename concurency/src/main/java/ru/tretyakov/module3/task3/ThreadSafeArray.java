package ru.tretyakov.module3.task3;


import java.util.Iterator;



/**
 * TODO comment.
 * @author Rooter
 * @since 03.02.19
 **/

public class ThreadSafeArray<E> implements SimpleList<E> {

    private final MyArrayList<E> container = new MyArrayList<>();

    @Override
    public boolean add(final E value) {
        synchronized (this) {
            return this.container.add(value);
        }
    }

    @Override
    public E get(int index) {
        synchronized (this) {
            return this.container.get(index);
        }
    }

    @Override
    public int size() {
        synchronized (this) {
            return this.container.size();
        }
    }

    @Override
    public boolean remove(final int index) {
        synchronized (this) {
            return this.container.remove(index);
        }
    }

    @Override
    public boolean contains(E value) {
        synchronized (this) {
            return this.container.contains(value);
        }
    }

    @Override
    public Iterator<E> iterator() {
        synchronized (this) {
            return clone().iterator();
        }
    }

    @Override
    public boolean isEmpty() {
        synchronized (this) {
            return this.container.isEmpty();
        }
    }

    public MyArrayList<E> clone() {
        synchronized (this) {
            return this.container.clone();
        }
    }
}
