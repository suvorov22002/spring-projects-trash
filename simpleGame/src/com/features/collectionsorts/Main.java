package com.features.collectionsorts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Main {

    private static  List<Employee> listEmployees;

    public static void main(String[] args) {

        listEmployees = new ArrayList<>();
        listEmployees.add(new Employee("Tom", 45, 80000));
        listEmployees.add(new Employee("Sam", 56, 75000));
        listEmployees.add(new Employee("Alex", 30, 120000));
        listEmployees.add(new Employee("Peter", 25, 60000));

        System.out.println("Before sorting: " + listEmployees);
        //Collections.sort(listEmployees);
        Collections.sort(listEmployees, new EmployeeAgeComparator());
        System.out.println("After sorting: " + listEmployees);
    }
}
