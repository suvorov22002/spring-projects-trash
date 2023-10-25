package com.features.linkin;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Collect {

    public static void main(String[] args) {
        List<String> words = List.of("Apple", "Bat", "Cat","Orange");
        List<String> wordsAL = new ArrayList<>(words);
        System.out.println(wordsAL);
        for (int i=0;i< wordsAL.size();i++) {
            System.out.println("Size = " + wordsAL.size() + " - " + wordsAL.get(i));

            if(wordsAL.get(i).endsWith("at")){

                wordsAL.remove(wordsAL.get(i));

            }
            System.out.println("i= " + i + " > "+wordsAL);
        }
        System.out.println(wordsAL);

        StringBuilder sb1 = new StringBuilder("hello");
        StringBuilder sb2 = new StringBuilder("hello");

        if(sb1.equals(sb2)) {
            System.out.println("Equal");
        } else {
            System.out.println("Not Equal");
        }

        Person a = new Person("Bob", 20);
        Person b = new Person("Mike", 40);
        Person c = new Person("Bob", 20);

        System.out.println(a.equals(c));

        IntStream.iterate(0, i -> i + 100)
                .limit(1000 / 100)
                .forEach(i -> System.out.println("This is number " + i));

        IntStream.range(0, 10).forEach(i -> System.out.printf("Iteration: %d%n", i));

        int x = 5; int y = 7;
        int z = x++ + ++y - --x + y--;
        System.out.println(z);

    }
    record Person(String name, int age){}
}
