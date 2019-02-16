package ru.tretyakov.module3.task2;

import org.junit.Test;
import ru.tretyakov.module3.task3.MyArrayList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MySafeArrayListTest {

    private MyArrayList<Bean> list = new MyArrayList<>();

    @Test
    public void test() {
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

    class Bean implements Serializable {

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

    @Test
    public void test2() {
        CopyOnWriteArrayList<Bean> list = new CopyOnWriteArrayList<>();
        list.add(new Bean("AAAA"));
        list.add(new Bean("AAAA"));
        list.add(new Bean("AAAA"));
        list.add(new Bean("AAAA"));

        Iterator<Bean> it = list.iterator();

        list.get(0).setValue("BBBB");
        list.remove(0);

        while (it.hasNext()) {
            System.out.println(it.next().getValue());
        }
        System.out.println("-------------------");
        list.forEach(v -> System.out.println(v.getValue()));
     }

     @Test
    public void test3() {
        List<Bean> list = new ArrayList();
        list.add(new Bean("First"));
         ArrayList<Bean> list2 = (ArrayList<Bean>) ((ArrayList<Bean>) list).clone();


         System.out.println(list);
         System.out.println(list2);
         System.out.println();
         System.out.println(list.get(0));
         System.out.println(list2.get(0));
    }
}