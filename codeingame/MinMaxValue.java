package com.codingame;

/**
 * 
 * @author rodrigue_toukam
 * @author rodrigue_junior
 *
 */
public class MinMaxValue {
	
	
	public static void main(String[] args) {
		int maxVal = Integer.MAX_VALUE;
		int minVal = Integer.MIN_VALUE;

		int array[] = {51, 24, 19, 5, 37, 76, 61, 99, 101, 36};
		maxVal = array[0];
		minVal = array[0];

		for (int nombre:array)
			System.out.print(nombre+" ");

		for(int i = 0; i < array.length; i++){
			if(array[i] < minVal)
				minVal = array[i];
			if(array[i] > maxVal)
				maxVal = array[i];
		}

		System.out.print("\nValeur minimale = "+minVal);
		System.out.println("\nValeur maximale = "+maxVal);
		
		int b = 5;
		int a = 2;
		int c = 5/2;
		System.out.println(c);
		
		int d = 0;
		System.out.println("D = " + (d++));
		System.out.println("D = " + (d++));
	}
}
