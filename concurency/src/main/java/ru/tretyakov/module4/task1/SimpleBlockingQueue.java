package ru.tretyakov.module4.task1;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

/**
 * TODO comment.
 * @author Rooter
 * @since 17.02.19
 **/

@ThreadSafe
public class SimpleBlockingQueue<T> {

    private int capacity = 0;

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();

    public SimpleBlockingQueue() {
    }

    public SimpleBlockingQueue(final int capacity) {
        this.capacity = capacity;
    }

    public void offer(final T value) {
        synchronized (this) {
            while (this.queue.size() == capacity) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
            this.queue.offer(value);
            this.notify();
        }
    }

    public T poll() throws InterruptedException {
        synchronized (this) {
            while (this.queue.size() == 0) {
                this.wait();
            }
            this.notify();
            return this.queue.poll();
        }
    }

    public boolean isEmpty() {
        synchronized (this) {
            return this.queue.isEmpty();
        }
    }
}
