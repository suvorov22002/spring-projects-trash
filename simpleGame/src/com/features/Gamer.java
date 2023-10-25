package com.features;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.UnaryOperator;


public class Gamer implements Function<String, String> {

    @Override
    public String apply(String s) {
        return "Bonjour " + s;
    }


    public static void main(String[] args) {
        Gamer g = new Gamer();

        System.out.println(g.apply("Diane Nama"));

        UnaryOperator<Integer> nbre = n -> 2 * n;
        System.out.println("double: " + nbre.andThen(x -> (x - 1)).compose(a -> (int)a-2).apply(4));

        List<Map<String, Object>> mapList = new ArrayList<>();
        mapList.add(new HashMap<>());

        System.out.println("mapList: " + mapList);

        String s1 = "abc";
        String s2 = "abc";
        System.out.println("s1 == s2 is:" + s1 == s2);

        char c = 65;
        System.out.println("c = " + c);

        String x = "abc";
        String y = "abc";
        String w = x.concat(y);
        System.out.println(x);
        System.out.println(w);

        int result = 31 * 1 + (int) (4 ^ (4 >>> 32));

        System.out.println((4 >>> 32));


    }

}
