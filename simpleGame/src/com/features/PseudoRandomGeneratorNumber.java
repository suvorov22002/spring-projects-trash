package com.features;

import java.util.SplittableRandom;

public class PseudoRandomGeneratorNumber {
    public static void main(String[] args) {
        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE;
        SplittableRandom splittableRandom = new SplittableRandom();
        int random = splittableRandom.nextInt();
        System.out.println("Random: " + random);
        System.out.println("Random bound: " + splittableRandom.split().nextInt(10, 20));

        SplittableRandom random1 = new SplittableRandom();
        SplittableRandom random2 = random1.split();
        System.out.println("Random 1: " + random1.nextInt());
        System.out.println("Random 2: " + random2.nextInt());

    }

}
