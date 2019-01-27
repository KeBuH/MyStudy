package ru.tretyakov.module3.task2;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class UserStorageTest {

    private Storage storage = new UserStorage();

    @Test
    public void userStorageTest() {
        User toDelete = new User(1,400);
        boolean result = storage.add(toDelete);
        result = storage.add(new User(2, 500));
        assertThat(result, is(true));
        result = storage.add(new User(3,400));
        assertThat(result, is(true));
        result = storage.add(new User(4,400));
        assertThat(result, is(true));
        result = storage.delete(toDelete);
        assertThat(result, is(true));
        result = storage.transfer(2,3,600);
        assertThat(result, is(false));
        result = storage.transfer(2,3,400);
        assertThat(result, is(true));
    }
}