package com.features.generics;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Professor extends SchoolMember{
    Long id;
    String name;
    String function;

    @Override
    public double calculateFees() {
        return 0;
    }
}
