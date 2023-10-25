package com.features;

import java.util.Arrays;

public class SolutionRange {

    public static void main(String[] args) {
        int[] tab = {1, 4, 9, 12, 98, -10, 10};
        int[] tabs = {30, 4, 9, 12, 98, -10, 10};
        System.out.println("Somme range = " + sumRange(tab));
        System.out.println("Sommes range = " + sumRange(tabs));


        System.out.println("Sommes square = " +  putInSquare(tab));
        System.out.println("Sommes stream square = " +  putStreamInSquare(tab));
    }

    static int sumRange(int[] ints) {
        return Arrays.stream(ints).filter(val -> val >= 10 && val <= 100).sum();
    }

    static int putInSquare(int[] ints) {
        int sum = 0;
        for(Integer n : ints) {
            if (n % 2 == 0) {
                sum += n * n;
            }
        }

        return sum;
    }

    static int putStreamInSquare(int[] ints) {
        int sum;
        sum = Arrays.stream(ints).filter(x -> x%2 == 0).map(t -> t*t).sum();
        return sum;
    }

}
