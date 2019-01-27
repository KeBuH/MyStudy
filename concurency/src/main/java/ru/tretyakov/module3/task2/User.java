package ru.tretyakov.module3.task2;

import java.util.Objects;

/**
 * Сущность пользователя.
 * @author Rooter
 * @since 13.01.19
 **/

public class User {
    /**
     * айди юзера.
     */
    private int id;
    /**
     * Сумма на счету.
     */
    private int amount;

    /**
     * Контрктор юзера.
     * @param id айди.
     * @param amount сумма.
     */
    public User(final int id, final int amount) {
        this.id = id;
        this.amount = amount;
    }

    /**
     * Возвращает айди юзера.
     * @return айди.
     */
    public int getId() {
        return id;
    }

    /**
     * Задает айди юзеру.
     * @param id айди.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Возращает сумму со счета юзера.
     * @return сумма.
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Задает сумму на счету юзера.
     * @param amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * TODO cooment.
     * @param o todo comment
     * @return result
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    /**
     * TODO comment.
     * @return hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
