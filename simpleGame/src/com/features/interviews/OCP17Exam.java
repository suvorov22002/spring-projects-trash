package com.features.interviews;

import java.sql.*;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class OCP17Exam {

    public static void main(String[] args) {
//        snippet1();
//        snippet2();
//        snippet3();
//        snippet4();
        /*var a = 15;
        var b = 10;
        snippet5(a, b);
        System.out.println("var a = " + a);*/
        snippet7();
    }

    private static void snippet1() {
        
//        int score1 = 8, score2 = 3;
//        char myScore = 7;
//        System.out.println("myScore: " + myScore);
//        var goal = switch (myScore) {
//            default -> {if(10 > score1) yield "unknow";}
//            case score1 -> "great";
//            case 2,4,6 -> "good";
//            case score2, 0 -> {
//                var bad = "bad";
//            }
//        };
//        System.out.println("Goal: " + goal);
    }

    private static void snippet2() {
        int moon = 9, star = 2 + 2 * 3;
        float sun = star>10 ? 1 : 3;
        double jupiter = (sun + moon) - 1.0f;
        int mars = --moon <= 8 ? 2 : 3;
        System.out.println(sun+", "+jupiter+", "+mars);
    }

    private static void snippet3() {
        List<Integer> data = new ArrayList<>();
        IntStream.range(0, 100)/*.parallel().peek(p -> System.out.println(p + " > " + Thread.currentThread().getName()))*/
                .forEach(data::add);
        System.out.println(data.size());

    }

    private static void snippet4() {
        Predicate<String> empty = String::isEmpty;
        Predicate<String> notEmpty = empty.negate();

        var result = Stream.generate(() -> "").filter(notEmpty).peek(System.out::println).collect(Collectors.groupingBy(k -> k))
                .entrySet().stream().map(Map.Entry::getValue)
                .flatMap(Collection::stream)
                .collect(Collectors.partitioningBy(notEmpty));

        System.out.println("Result: " + result);
    }

    private static void snippet5(int x, int addAmount) {
        x = x + addAmount;
    }

    private static void snippet7() {
        int[] array = {6, 9, 8};
        System.out.println("B: " + Arrays.binarySearch(array, 9));
        System.out.println("C: " + Arrays.compare(array, new int[]{6,9,8}));
        System.out.println("M: " + Arrays.mismatch(array, new int[]{6,9,8}));
    }

    private static void snippet12() throws SQLException {
        try(
                Connection a = DriverManager.getConnection("url", "username", "password");
                PreparedStatement p = a.prepareStatement("request");
                ResultSet r = p.executeQuery();
                ){

            String val = r.getString(1);
            System.out.println(val);

        }

    }

    private static void snippet13() {
        Set<? extends RuntimeException> mySet = new TreeSet<NullPointerException>();
        var var = 3;

    }
}
