package com.features.multithreading;

public class ThreadExample extends Thread{


    public static void main(String[] args) {

        ThreadExample threadExample = new ThreadExample();
        threadExample.start();
    }

    public void run() {
        System.out.println("Running Thread");
    }



}
