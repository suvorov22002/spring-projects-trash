package com.features.collectionsorts;

import java.util.Comparator;

/**
 * This comparator compares two employees by their ages.
 * @author vasilievitch suvorov
 *
 */
public class EmployeeAgeComparator implements Comparator<Employee> {

    @Override
    public int compare(Employee o1, Employee o2) {
        return o1.getAge() - o2.getAge();
    }
}
