package com.features;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class _Functional {

    public static void main(String[] args) {
        //testMethod2();
        manipulateChar();
    }

    private static void testMethod() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("My runnable");
            }
        };

        r.run();
    }

    private static void testMethod2() {
        Runnable r = () -> System.out.println("My runnable 2");
        r.run();
    }

    private static boolean isPrime(int number) {
        return number > 1 && IntStream.range(2, number).noneMatch(x -> number % x == 0);
    }

    private static void manipulateChar() {
        List<String> names = Arrays.asList("ABC", "CAB", "BCA");
        String result = names.stream().reduce("", (a,b) -> a + b.charAt(1));
        System.out.println(result);
    }

}
