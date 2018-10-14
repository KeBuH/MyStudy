package ru.tretyakov.counter;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * TODO comment.
 * author Rooter
 * since 23.09.18
 **/

@ThreadSafe
public class Count {
    /**
     * TODO comment.
     */
    @GuardedBy("this")
    private int value;

    /**
     * TODO comment.
     */
    public synchronized void increment() {
        this.value++;
    }

    /**
     * TODO comment.
     * @return todo comment
     */
    public synchronized int get() {
        return this.value;
    }
}
