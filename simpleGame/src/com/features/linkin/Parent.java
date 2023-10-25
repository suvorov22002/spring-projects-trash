package com.features.linkin;

public class Parent {

    public Parent(){
        System.out.println("Parent");
    }
    static{
        System.out.println("Static anonymous block for parent");
    }
    {
        System.out.println("non static Anonymous block for parent");
    }
}
