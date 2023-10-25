package com.features.spliterator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java8Iterator {

    public static void main(String[] args) {

        //Spliterator from an array
        String[] languageArray = new String[] {"Java", "C", "C++", "Python", "Scala", "Kotlin"};
        Spliterator<String> spliteratorOfArray = Arrays.spliterator(languageArray);

        //Spliterator from a List
        List<String> languageList = Arrays.asList("Java", "C", "C++", "Python", "Scala", "Kotlin");
        Spliterator<String> spliteratorOfList = languageList.spliterator();

        //Spliterator from a Stream
        Stream<String> languageStream = Stream.of("Java", "C", "C++", "Python", "Scala", "Kotlin");
        Spliterator<String> spliteratorOfStream = languageStream.spliterator();

        System.out.println("--- Estimate size ---");
        System.out.println(spliteratorOfStream.estimateSize());
        System.out.println("--- Try advance ---");
        spliteratorOfStream.tryAdvance(System.out::println);
        System.out.println("--- Estimate size ---");
        System.out.println(spliteratorOfStream.estimateSize());
        System.out.println("--- For each of remainining ---");
        spliteratorOfStream.forEachRemaining(System.out::println);
        System.out.println("--- Estimate size ---");
        System.out.println(spliteratorOfStream.estimateSize());
        System.out.println("--- Exact size ---");
        System.out.println(spliteratorOfList.getExactSizeIfKnown());
        spliteratorOfList.tryAdvance(System.out::println);
        System.out.println("--- Exact size ---");
        System.out.println(spliteratorOfList.getExactSizeIfKnown());

        Predicate<String> pr = x -> x.length() > 4;

        List<String> filterList = Stream.of("Java", "C", "C++", "Python", "Scala", "Kotlin").filter(pr).toList();
        System.out.println("Filter List: " + filterList);

        String o = "Java ehancement proposal";
        Position p = new Position(12, 8);
        Character c = 'A';
//        testRecordPattern(c);

        Shape shape = new Rectangle(new Position(0,5), new Position(10, 5));
        shape = new Circle(new Position(2,1), 3);
        shapeDebugger(shape);
    }

    public record Position(int x, int y) {}
    public record Path(Position from, Position to) {}
    public sealed interface Shape permits Rectangle, Circle {}
    public record Rectangle(Position topLeft, Position bottomRight) implements Shape {}
    public record Circle(Position center, int radius) implements Shape {}
    public sealed interface Direction permits CompassDirection, VerticalDirection {}
    public enum CompassDirection implements Direction {NORTH,SOUTH,EAST,WEST}
    public enum VerticalDirection implements Direction {UP,DOWN}

    private static void testRecordPattern(Object o) {

        //Record pattern for instanceof
        if(o instanceof Position p) {
            System.out.printf("This object has position %s %s%n", p.x(), p.y());
        }
        else if(o instanceof String s) {
            System.out.printf("This object is an instance of %s%n", s);
        }
        else{
            System.out.printf("o is something else: %s%n", o);
        }
        
        //Record pattern for switch
        switch (o){
            case Position p -> System.out.printf("This object has position %s %s%n", p.x(), p.y());
            case String s -> System.out.printf("This object is an instance of %s%n", s);
            default -> throw new IllegalStateException("Unexpected value: " + o);
        }
    }

    private static void shapeDebugger(Shape shape) {

        switch(shape) {
            case Rectangle r -> System.out.printf("Rectange topleft =  %s , bottomRight = %s%n ", r.topLeft(), r.bottomRight());
            case Circle c -> System.out.printf("Circle center = %s, radius = %s%n ", c.center(), c.radius());
        }
        StringBuilder sb = new StringBuilder();
        sb.append(0x1f600);
        System.out.println(sb.toString());
        Collectors.toUnmodifiableSet();
    }
    
    private static void controlTravelLine(Direction d) {
        switch (d){
            case CompassDirection s && s == CompassDirection.NORTH -> System.out.println("Flying vers le nord");
            case CompassDirection s && s == CompassDirection.WEST -> System.out.println("Flying vers l'ouest");
            case CompassDirection s && s == CompassDirection.SOUTH -> System.out.println("Flying vers le sud");
            case CompassDirection s && s == CompassDirection.EAST -> System.out.println("Flying vers l'est");
            case VerticalDirection v && v == VerticalDirection.DOWN -> System.out.println("vers le bas");
            case VerticalDirection v && v == VerticalDirection.UP -> System.out.println("vers le haut");
            default -> throw new IllegalStateException("Unexpected value: " + d);
        }

//        StringBuilder sb = new StringBuilder();
//        sb.repeat("Hello ", 2);
//        sb.repeat(0x1f600, 5);
//        sb.repeat('!', 3);
        System.out.println(0x1f600);
    }
}
