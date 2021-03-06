package ru.tretyakov.module3.task3;


import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;



/**
 * TODO comment.
 * @author Rooter
 * @since 03.02.19
 **/

@ThreadSafe
public class ThreadSafeArray<E> implements SimpleList<E>, Iterable {

    @GuardedBy("this")
    private final MyArrayList<E> container = new MyArrayList<>();

    @Override
    public boolean add(final E value) {
        synchronized (this) {
            return this.container.add(value);
        }
    }

    @Override
    public E get(final int index) {
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
    public boolean contains(final E value) {
        synchronized (this) {
            return this.container.contains(value);
        }
    }

    @Override
    public Iterator<E> iterator() {
        synchronized (this) {
            return cloneContainer().iterator();
        }
    }

    @Override
    public boolean isEmpty() {
        synchronized (this) {
            return this.container.isEmpty();
        }
    }

    private MyArrayList<E> cloneContainer() {
        synchronized (this) {
            return this.container.clone();
        }
    }
}
