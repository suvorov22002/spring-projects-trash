package com.features.generics;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class BoundedGenericMethods {

    public static void main(String[] args) {
        List<Double> doubles = Arrays.asList(3.14, 1.68, 2.94);
        List<String> cols = Arrays.asList("Perlado", "TioDeLaBota", "Vinossol", "Merlot");
        System.out.println("Occurrence: " + count(doubles, 2.0));
    }
    private static <T extends Number> int count(Collection<T> cols, T item) {

        int count = 0;

        if(item == null) {
            for(T element : cols) {
                if(element == null) count++;
            }
        }
        else{
            for(T element : cols) {
                if(item.equals(element)) count++;
            }
        }

        return count;
    }

    private static int count2(Collection<? extends Number> cols, Number item) {

        int count = 0;

        if(item == null) {
            for(Number element : cols) {
                if(element == null) count++;
            }
        }
        else{
            for(Number element : cols) {
                if(item.equals(element)) count++;
            }
        }

        return count;
    }
}
