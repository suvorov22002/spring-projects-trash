package com.features;

import java.util.EmptyStackException;

public class Stack {

    public static void main(String[] args) {

    }

    private Object[] elements;
    private int size = 0;

    public Stack(int initialCapacity) {
        elements = new Object[initialCapacity];
    }

    public void push(Object object) {
        ensureCapacity();
        elements[size++] = object;
    }

    public Object pop() {
        if(size == 0) {
            throw new EmptyStackException();
        }

        return elements[--size];
    }

    private void ensureCapacity() {
        if(elements.length == size) {
            Object[] old = elements;
            elements = new Object[2 * size + 1];
            System.arraycopy(old, 0, elements, 0, size);
        }
    }
}
