package com.features.collectionsorts;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Employee implements Comparable<Employee>{

    String name;
    int age;
    int salary;

    @Override
    public int compareTo(Employee o) {
        return o.salary - this.salary;
    }

    @Override
    public String toString() {
        return String.format("(%s, %d, %d)", name, age, salary);
    }
}
