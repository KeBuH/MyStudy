package ru.tretyakov.storage;

import org.junit.Test;
import ru.tretyakov.storage.UserStorage.User;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserStorageTest {

    @Test
    public void whenAddNewUserThenGetItFromContainer() {
        UserStorage storage = new UserStorage(10);
        User user = new User(1, 100);
        storage.add(user);
        assertThat(user, is(storage.get(1)));
    }

    @Test
    public void whenUpdateUserThenGetItFromContainer() {
        UserStorage storage = new UserStorage(10);
        User user = new User(1, 100);
        storage.add(user);
        user.setAmount(300);
        storage.update(user);
        assertThat(user.getAmount(), is(300));
    }

    @Test
    public void whenDeleteThenStorageIsEmpty() {
        UserStorage storage = new UserStorage(10);
        User user = new User(1, 100);
        storage.add(user);
        assertThat(storage.size(), is(1));
        storage.delete(user);
        assertThat(storage.size(), is(0));
    }

    @Test
    public void whenTransferThenIncrementAmountFromConsumer() {
        UserStorage storage = new UserStorage(10);
        User sender = new User(1, 100);
        User consumer = new User(2, 0);
        storage.add(sender);
        storage.add(consumer);
        storage.transfer(1,2,20);
        assertThat(storage.get(1).getAmount(), is(80));
        assertThat(storage.get(2).getAmount(), is(20));
    }

}