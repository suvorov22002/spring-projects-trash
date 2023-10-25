package com.features;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MacMtn {

    public static void main(String[] args) throws Exception {

        /*Vector<String> stringVector = new Vector<>();
        stringVector.add("Isabel");
        stringVector.add("Tenor");
        stringVector.add("Brice");
        stringVector.forEach(System.out::println);

        int i = 0;
        for (String s : stringVector) {
            if (i == 1) {
                stringVector.remove(s);
            }
            i++;
        }

        stringVector.forEach(System.out::println);*/

       /* CopyOnWriteArrayList<Integer> copyInteger = new CopyOnWriteArrayList<>();
        copyInteger.add(3);
        copyInteger.add(5);
        copyInteger.add(7);
        copyInteger.add(2);
        copyInteger.add(20);
        copyInteger.forEach(System.out::println);

        StackImplementation stringStack = new StackImplementation(10);
        stringStack.push("Bumpy");
        stringStack.push(3);
        stringStack.push('A');
        System.out.println("First element is: " + stringStack.pop());
        System.out.println("First element is: " + stringStack.pop());*/

        Set<String> stringSet = new HashSet<>();
        stringSet.add("Yahve");
        stringSet.add("Google");
        stringSet.add("chatGPT");
        stringSet.add("Bing");
        stringSet.add("IA");
        stringSet.add("Google");
        stringSet.forEach(System.out::println);
        System.out.println("***************************************");
        TreeSet<String> stringTreeSet = new TreeSet<>();
        stringTreeSet.add("Yahve");
        stringTreeSet.add("Google");
        stringTreeSet.add("chatGPT");
        stringTreeSet.add("Bing");
        stringTreeSet.add("IA");
        stringTreeSet.forEach(System.out::println);
        System.out.println("***************************************");
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add("Yahve");
        linkedHashSet.add("Google");
        linkedHashSet.add("chatGPT");
        linkedHashSet.add("Bing");
        linkedHashSet.add("IA");
        linkedHashSet.add("Google");
        linkedHashSet.forEach(System.out::println);


        Stream<String> nameStream = Stream.of("Alice", "Bob", "Chuck");
        nameStream.peek(System.out::println).toList();

        Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());

        Stream<String> stream1 = Stream.generate(() -> {return "abc";});
        stream1.limit(5).forEach(System.out::println);

        Stream<String> stream2 = Stream.iterate("xyz", i -> i);
        stream2.limit(10).forEach(System.out::println);

        IntStream is = "ABC".chars();
        is.forEach(System.out::println);

        Stream<Integer> intStream = Stream.of(1,2,3,4);
        Map<Integer, Integer> map = intStream.collect(Collectors.toMap(i->i, i-> i+10));
        System.out.println(map);

        intStream = Stream.of(1,2,3,4);
        Integer[] intArray = intStream.toArray(Integer[]::new);
        System.out.println(Arrays.toString(intArray));

        Stream<String> names = Stream.of("aBc", "d", "ef");
        names.map(String::toUpperCase).collect(Collectors.toList()).forEach(System.out::print);

    }

}
