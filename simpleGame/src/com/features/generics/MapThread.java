package com.features.generics;

import java.util.HashMap;
import java.util.Map;

public class MapThread<T extends Number, U extends Runnable> {

    private Map<T, U> map = new HashMap<>();

    private void put(T num, U thread) {
        this.map.put(num, thread);

    }
}
