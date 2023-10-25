package com.features.linkin;

public class Main {

    public static void main(String[] args) {
        Vehicle vehicle = new Car();
        vehicle.move();

        System.out.println("Tricky ---> " + trickyString());
    }

    private static String trickyString() {
        String str = "Java";
        return str.concat("Programming");
    }
}
