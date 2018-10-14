package ru.tretyakov.storage;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Objects;

/**
 * TODO.
 * author Rooter
 * since 23.09.18
 **/

@ThreadSafe
public class UserStorage {
    /** Container of users. */
    private User[] users;
    /** Current size of container. */
    private int size;
    /** Constructor with initializing container capacity. */
    public UserStorage(final int capacity) {
        this.users = new User[capacity];
    }

    /**
     * This method add user entity to container.
     * @param user to add
     * @return result of operation
     */
    public synchronized boolean add(final User user) {
        boolean result = false;
        if (size < this.users.length) {
            result = true;
            this.users[size++] = user;
        }
        return result;
    }

    /**
     * This method return user entity by id.
     * @param id of user
     * @return entity
     * @throws NullPointerException if user not found
     */
    public User get(final int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        throw new NullPointerException("Required user not found");
    }

    /**
     * This method update user by id.
     * @param user to update
     * @return false if user not found
     */
    public synchronized boolean update(final User user) {
        boolean result = false;
        for (int index = 0; index < this.size; index++) {
            if (users[index].getId() == user.getId()) {
                users[index] = user;
                result = true;
            }
        }
        return result;
    }

    /**
     * This method delete user entity from container.
     * @param user to delte
     * @return false if user not found
     */
    public synchronized boolean delete(final User user) {
        boolean result = false;
        for (int index = 0; index < users.length; index++) {
            if (users[index].getId() == user.getId()) {
                while (index != users.length - 1) {
                    users[index] = users[index + 1];
                    index++;
                }
                result = true;
                size--;
                break;
            }
        }
        return result;
    }

    /**
     * TODO comment.
     * @param fromId todo comment
     * @param toId todo comment
     * @param amount todo comment
     * @return todo comment
     */
    public synchronized boolean transfer(int fromId, int toId, int amount) {
        int temp;
        User sender = get(fromId);
        User consumer = get(toId);
        if (sender.getAmount() < amount) {
            throw new IllegalArgumentException("Not enough amount");
        } else {
            temp = sender.getAmount() - amount;
            sender.setAmount(temp);
            temp = consumer.getAmount() + amount;
            consumer.setAmount(temp);
        }
        return false;
    }

    /**
     * This method return size of the container.
     * @return int
     */
    public int size() {
        return this.size;
    }

    /**
     * Class describe user entity
     */
    public static class User {
        /**
         * user id, amount.
         */
        private int id, amount;

        /**
         * Constructor of user.
         * @param id of user
         * @param amount of user
         */
        public User(final int id, final int amount) {
            this.id = id;
            this.amount = amount;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return id == user.id && amount == user.amount;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, amount);
        }
    }
}
