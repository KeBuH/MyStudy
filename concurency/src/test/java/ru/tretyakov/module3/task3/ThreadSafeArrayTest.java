package ru.tretyakov.module3.task3;

import org.junit.Test;

import java.util.Iterator;


public class ThreadSafeArrayTest {

    @Test
    public void test() throws CloneNotSupportedException {
        ThreadSafeArray<Bean> list = new ThreadSafeArray<>();
        list.add(new Bean("AAAA"));
        list.add(new Bean("BBBB"));
        list.add(new Bean("CCCC"));
        list.add(new Bean("DDDD"));

        Iterator<Bean> it = list.iterator();

        list.get(0).setValue("BBBB");
        list.remove(0);

        while (it.hasNext()) {
            System.out.println(it.next().getValue());
        }
        System.out.println("AFTER REMOVE...");
        for(int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getValue());
        }
    }

    class Bean {
        private String value;
        public Bean(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
        public void setValue(String value) {
            this.value = value;
        }
    }

}