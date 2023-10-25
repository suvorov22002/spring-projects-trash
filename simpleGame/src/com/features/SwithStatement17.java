package com.features;

import java.util.Arrays;
import java.util.List;

import com.features.Util.*;

public class SwithStatement17 {

    public static void main(String[] args) {
        List<String> symbols = Arrays.asList("Bordeau", "Monaco", "Toulouse", "Nice", "Lyon", "Paris");
        System.out.println(System.getProperty("java.runtime.version"));
        System.out.println("Comput Object length: " + getObjectLength(symbols));
        caseLabel(4);
        Point p = new Point(5, 9);
        System.out.println(testType(p));

    }

    private static int getObjectLength(Object obj) {

        return switch (obj) {
            case String s -> s.length();
            case List list && !list.isEmpty() -> list.size();
            case null -> 0;
            default -> -1;
        };
    }

    private static void caseLabel(int value) {
        switch (value) {
            case 1 -> System.out.println("one");
            case 2 -> System.out.println("two");
            case 3, 4 -> System.out.println("three or four");
        //    case n if n >= 5 && n <= 10 -> System.out.println("Between Five and Ten");
            default -> System.out.println("Invalid value");
        }
    }

    private static String testType(Object obj) {
        return switch (obj) {
            case null -> "Null value";
            case String s -> "It's a string";
            case Size e -> "Enum constant";
            case Point p -> "A record";
            case Employee e && "John".equals(e.getName()) -> "Conditional statement";
            case Employee em -> "Class of employee";
            case int[] ai -> "Array type";
            default -> "Unknow object";
        };
    }
    enum Size {
        SMALL, MEDIUM, LARGE;
    }
    static class Employee {
        private String name;
        private int salary;
        private int age;

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public int getSalary() {
            return salary;
        }
        public void setSalary(int salary) {
            this.salary = salary;
        }
        public int getAge() {
            return age;
        }
        public void setAge(int age) {
            this.age = age;
        }
    }
}
