package com.codingame;

import java.util.Arrays;

public class CloseToZero {
	
	public static void main(String[] args) {
        int[] str = {2,3,-2,-1,1};
        int close = Arrays.stream(str).filter(i -> i != 0)
                .reduce((a, b) -> Math.abs(a) < Math.abs(b) ? a : (Math.abs(a) == Math.abs(b) ? Math.max(a, b) : b))
                .getAsInt();
                //.ifPresent(System.out::println);
        
        	System.out.println(close);
    }
	
}
