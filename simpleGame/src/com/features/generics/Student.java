package com.features.generics;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student extends SchoolMember{
    Long id;
    String name;
    int matricule;

    @Override
    public double calculateFees() {
        return 0;
    }
}
