package com.features.linkin;

public class Children extends Parent{
    public static void main(String[] args) {
        System.out.println("Start");
        Children c = new Children();
        System.out.println("End");
    }
    public Children() {
        System.out.println("Children");
    }
    static {
        System.out.println("Static anonymous block for children");
    }
    {
        System.out.println("Non static anonymous block for children");
    }
}
