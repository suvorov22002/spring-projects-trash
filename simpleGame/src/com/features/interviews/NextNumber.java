package com.features.interviews;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class NextNumber {

    public static void main(String[] args) {
        System.out.println(next(654321));
    }

    private static int next(int n)
    {
        int newNumber = -1;

        List<Character> startChars;
        List<Character> newChars = new ArrayList<>();
        List<Character> commonChars = null;

        startChars = String.valueOf(n).chars().mapToObj(c -> (char) c).toList();

        while (commonChars == null || !commonChars.isEmpty())
        {
            if (n < Math.pow(2, 31) || n > 0)
            {
                newNumber = n + 1;
                newChars.clear();
                newChars = String.valueOf(newNumber).chars().mapToObj(c -> (char) c).collect(toList());
                commonChars = startChars.stream().distinct().filter(newChars::contains).toList();
                n++;
            }
        }

        return newNumber;
    }
}
