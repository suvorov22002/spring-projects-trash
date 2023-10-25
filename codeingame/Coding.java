package com.codingame;

import java.util.Arrays;

public class Coding {
	
	public boolean exist(int[] ints, int k) {
		if(ints.length == 0) return false;
		if(ints[0] == k) return true;
		return Arrays.binarySearch(ints, k) > 0;
	}

	public static int binarySearch(int tab[], int f, int l, int val){
		if (l >= f){
			int mid = f + (l - f)/2;
			if (tab[mid] == val){
				return mid;
			}
			if (tab[mid] > val){
				//recherche dans le sous-tableau à gauche
				return binarySearch(tab, f, mid-1, val); 
			}else{
				//recherche dans le sous-tableau à droite
				return binarySearch(tab, mid+1, l, val); 
			}
		}
		return -1;
	}
}
