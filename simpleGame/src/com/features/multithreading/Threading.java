package com.features.multithreading;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Threading implements Runnable{

    private static AtomicInteger counter = new AtomicInteger(0);
    public static void main(String[] args) {
        Threading threading = new Threading();
        Thread t = new Thread(threading);
        t.start();

        HashMap m = new HashMap();
        Object o1 = new Object();
        Object o2 = o1;

        m.put(o1, 1);
        m.put(o2, 2);
        System.out.println("Object value: " + m.get(o1));

        System.out.println("Atomic value init: " + getCounter());
        increment();
        System.out.println("Atomic value: " + getCounter());

        var $num = (Integer)null;
        System.out.println($num);

        int[] google = {71, 18, 42, 21, 67, 32, 95, 14, 56, 87};
        System.out.println("First array: " + Arrays.toString(google));
        System.out.println("Simply array: " + Arrays.toString(Arrays.stream(google, 1, 4).toArray()));

        List<Integer> listOfNumbers = Arrays.asList(1, 2, 3, 4);
        int sum = listOfNumbers.parallelStream().peek(p -> System.out.println(p + " > " + Thread.currentThread().getName()))
                .reduce(0, Integer::sum) + 5;
        System.out.println("parralel sum: " + sum);

    }

    @Override
    public void run() {
        System.out.println("New Thread has been created and is running.");
    }

    public static int getCounter() {
        return counter.get();
    }

    private static void increment() {
        counter.incrementAndGet();
    }
}
