package com.features;

import java.util.concurrent.atomic.AtomicInteger;

public class MyThread extends Thread{

    // declaring a count variable
    //private int count;
    private AtomicInteger count = new AtomicInteger();

    public void run()
    {
        // calculating the count
        for (int i = 1; i <= 5; i++) {

            try {
                Thread.sleep(i * 100);
            //    count++;
                count.incrementAndGet();
            }
            catch (InterruptedException
                    e) { // throwing an exception
                System.out.println(e);
            }
        }
    }
    // returning the count value
    public AtomicInteger getCount() { return this.count; }

}
