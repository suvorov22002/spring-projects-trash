package com.features;

public class GFG {
    public static void main(String[] args)
            throws InterruptedException
    {

        // creating an thread object
        MyThread t = new MyThread();
        Thread t1 = new Thread(t, "t1");

        // starting thread t1
        t1.start();
        Thread t2 = new Thread(t, "t2");

        // starting thread t2
        t2.start();

        // calling join method on thread t1
        t1.join();

        // calling join method on thread t1
        t2.join();

        // displaying the count
        System.out.println("count=" + t.getCount());
    }
}
