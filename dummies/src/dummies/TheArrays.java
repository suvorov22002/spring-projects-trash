package dummies;

import java.util.Arrays;

public class TheArrays {
	
	public static void main(String[] args) {
		
		String[] colors = new String[5];
		colors[0] = "blue";
		colors[1] = "yellow";
		colors[2] = "red";
		colors[3] = "green";
		colors[4] = "orange";
		
		Arrays.stream(colors).forEach(System.out::println);
		
	}
}
