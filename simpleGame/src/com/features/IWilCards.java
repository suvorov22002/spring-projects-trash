package com.features;

import java.util.Collection;

public interface IWilCards {

    // subtype of super types
    double sum(Collection<? extends Number> numbers);

    //
    void append(Collection<? super Integer> integers, int n);

    double sumNumbers(Collection<Number> numbers, int n);

    /**
     * Get and Put Principle
     * *- Use an extends wildcard when you only get values out of a structure.
     * *- Use a super wildcard when you only put values into a structure.
     * *- And don’t use a wildcard when you both get and put.
     *
     * And there are two exceptions:
     * *- You cannot put anything into a type declared with an extends wildcard except for
     * the value null, which belongs to every reference type.
     * *- You cannot get anything out from a type declared with a super wildcard except for
     * a value of type Object, which is a super type of every reference type.
     */
}
