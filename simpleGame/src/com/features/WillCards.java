package com.features;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class WillCards implements IWilCards{

    static  List<Integer> integers = Arrays.asList(2, 4, 6);
    static  List<Double> doubles = Arrays.asList(3.14, 1.68, 2.94);
    static  List<Number> numbers = Arrays.<Number>asList(2, 4, 6, 3.14, 1.68, 2.94);

    public static void main(String[] args) {
        WillCards willCards = new WillCards();
        System.out.println("Sum of integers: " + willCards.sum(integers));
        System.out.println("Sum of doubles: " + willCards.sum(doubles));
        System.out.println("Sum of numbers: " + willCards.sum(numbers));

        List<? extends Number> numbers = new ArrayList<Integer>();
        numbers.add(null);

        Collection<Number> inumbers = new ArrayList<Number>();
        System.out.println("Often: " + willCards.sumNumbers(inumbers, 10));

    }
    @Override
    public double sum(Collection<? extends Number> numbers) {
        return numbers.stream().mapToDouble(Number::doubleValue).reduce(0, Double::sum);
//        double result = 0.0;
//        for (Number num : numbers) result += num.doubleValue();
//        return result;
    }

    @Override
    public void append(Collection<? super Integer> integers, int n) {
        for(int i=0; i<n; i++) {
            integers.add(i);
        }
    }

    @Override
    public double sumNumbers(Collection<Number> numbers, int n) {
        append(numbers, n);
        return sum(numbers);
    }


}
