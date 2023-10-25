package com.features.generics;

import lombok.Getter;

@Getter
public class Color<R, G, B> {
    R red;
    G green;
    B blue;

    public Color(R red, G green, B blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }
}
