package ru.tretyakov.core;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * TODO comment.
 * @author Rooter
 * @since 13.01.19
 **/

@ThreadSafe
public class Count {
    @GuardedBy("this")
    private int value;

    public void increment() {
        synchronized (this) {
            this.value++;
        }
    }

    public int get() {
        synchronized (this) {
            return this.value;
        }
    }
}