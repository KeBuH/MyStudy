package ru.tretyakov.module3.task2;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Threadsafe хранилище юзеров.
 * @author Rooter
 * @since 13.01.19
 **/

@ThreadSafe
public class UserStorage implements Storage {
    /**
     * контейнер юзеров.
     */
    @GuardedBy("this")
    private final List<User> storage = new ArrayList<>();

    /**
     * Добавляем нового юзера.
     * @param user новый юзер
     * @return результат операции
     */
    @Override
    public boolean add(final User user) {
        boolean result = false;
        synchronized (this) {
            if (null == checkUserExist(user.getId())) {
                storage.add(user);
                result = true;
            }
        }
        return result;
    }

    /**
     * Изменяем существующего юзера.
     * @param user юзер
     * @return реузльтат операции
     */
    @Override
    public boolean update(final User user) {
        boolean result = false;
        synchronized (this) {
            User temp = checkUserExist(user.getId());
            if (null != temp) {
                temp.setAmount(user.getAmount());
                result = true;
            }
        }
        return result;
    }

    /**
     * Удаляем юзера.
     * @param user юзер
     * @return результат операции
     */
    @Override
    public boolean delete(final User user) {
        boolean result = false;
        synchronized (this) {
            User temp = checkUserExist(user.getId());
            if (null != temp) {
                result = storage.remove(user);
            }
        }
        return result;
    }

    /**
     * Перевод средств со счета на счет.
     * @param fromId от кого
     * @param toId кому
     * @param amount сумма
     * @return резульатат операции
     */
    @Override
    public boolean transfer(int fromId, int toId, int amount) {
        boolean result = true;
        synchronized (this) {
            User from = checkUserExist(fromId);
            User to = checkUserExist(toId);
            if (null != from && null != to) {
                if (from.getAmount() < amount) {
                    result = false;
                } else {
                    from.setAmount(from.getAmount() - amount);
                    to.setAmount(to.getAmount() + amount);
                }
            }
        }
        return result;
    }

    /**
     * Провера наличия юзера в хранилище.
     * @param userId айди юзера
     * @return результат операции
     */
    private User checkUserExist(final int userId) {
        synchronized (this) {
            for (User curr : this.storage) {
                if (userId == curr.getId()) {
                    return curr;
                }
            }
        }
        return null;
    }
}
