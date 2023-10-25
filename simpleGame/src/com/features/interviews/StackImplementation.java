package com.features.interviews;

import java.util.EmptyStackException;
import java.util.Stack;
import java.util.Vector;

public class StackImplementation extends Vector<Object> {

    /*public static void main(String[] args) {
        Stack<String> STACK = new Stack<String>();

        // Use add() method to add elements
        STACK.push("Welcome");
        STACK.push("To");
        STACK.push("Geeks");
        STACK.push("For");
        STACK.push("Geeks");

        // Displaying the StackImplementation
        System.out.println("Initial StackImplementation: " + STACK);

        // Removing elements using pop() method
        System.out.println("Popped element: " +
                STACK.pop());
        System.out.println("Popped element: " +
                STACK.pop());

        // Displaying the StackImplementation after pop operation
        System.out.println("StackImplementation after pop operation "
                + STACK);
    }*/

    private Object[ ]  elements;
    private int size = 0;

    public StackImplementation(int initialCapacity) {
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

        Object el =  elements[--size];

        removeElementAt(size);

        return el;
    }

    private void ensureCapacity() {

        if(elements.length == size){
            Object[] old = elements;
            elements = new Object[2*size + 1];
            System.arraycopy(old, 0, elements, 0, size);
        }

    }
}
