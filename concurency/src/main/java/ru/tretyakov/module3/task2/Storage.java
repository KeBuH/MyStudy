package ru.tretyakov.module3.task2;

/**
 * Интерфейс хранилища.
 *
 * @author Rooter
 * @since 13.01.19
 **/

public interface Storage {
    boolean add (User user);
    boolean update(User user);
    boolean delete(User user);
    boolean transfer(int fromId, int toId, int amount);

}
