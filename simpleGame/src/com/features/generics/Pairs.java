package com.features.generics;

import lombok.Getter;

@Getter
public class Pairs<T, U> {

    T first;
    U second;

    public Pairs(T first, U second) {
        this.first = first;
        this.second = second;
    }

}
