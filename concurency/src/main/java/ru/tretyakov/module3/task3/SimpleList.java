package ru.tretyakov.module3.task3;

import java.util.Iterator;

/**
 * TODO comment.
 *
 * @author Rooter
 * @since 03.02.19
 **/

public interface SimpleList<E> {
    int size();
    boolean add(E value);
    E get(int index);
    boolean remove(int index);
    boolean contains(E value);
    Iterator<E> iterator() throws CloneNotSupportedException;
    boolean isEmpty();
}
