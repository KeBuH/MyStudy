package ru.tretyakov.module3.task1;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Счетчик.
 * @author Rooter
 * @since 13.01.19
 **/

@ThreadSafe
public class Count {
    @GuardedBy("this")
    private int value;

    /**
     * Увеличивает вчетчик на еденицу
     */
    public void increment() {
        synchronized (this) {
            this.value++;
        }
    }

    /**
     * Возвращаем значение счетчика.
     * @return значение.
     */
    public int get() {
        synchronized (this) {
            return this.value;
        }
    }
}