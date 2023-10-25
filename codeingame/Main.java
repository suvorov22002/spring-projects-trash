package com.codingame;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {
		String numref;
		Integer num = ((Integer)155)+1;
		String age = "00001";
		String cuti = "MIKI";
		String annee = "2022";
		int numord;
		//num = 155;
		String toForamt = "0000010500000";
		
		//System.out.println(String.format("%05d", num));
		numref = "N° " + String.format("%05d", num) + "/First Bank/" + age.substring(2) + "/" + cuti + "/" + annee.substring(2);
		System.out.println("1: "+numref);
	//	numref = numref.substring(0, 3) + String.format("%05d", (Integer.parseInt(numref.substring(3, 8).trim()) + 1)) + numref.substring(8);
	//	System.out.println("2: "+numref);
		
		numord = Integer.parseInt(numref.substring(3, 8).trim());
		
		System.out.println(numord);
		
		
		//		System.out.println(Integer.parseInt(toForamt.split(",")[0]));
		
//		int[] ints = {10,14,28,201,280};
//		long sum = 0;
//		Integer MAX_VALUE = 2147483647;
//		
//		long max = Long.MIN_VALUE;
//		long min = Long.MAX_VALUE;
//		
//		
//		List<Integer> arr = Arrays.asList(256741038, 623958417, 467905213, 714532089, 938071625);
//		
//		System.out.println(Collections.frequency(arr, 623958417));
//		Coding c = new Coding();
//		System.out.println(c.exist(ints, 10));
//
//		int tab[] = {1, 2, 3, 4, 5, 6, 7};
//		int val = 4;
//		int l = tab.length-1;
//		int res = c.binarySearch(tab,0,l,val);  
//		if (res != -1)
//			System.out.println("L'élément se trouve à l'index: " + res);
//		else
//			System.out.println("L'élément n'existe pas!");
//		
//		for(int i=0; i<arr.size(); i++) {
//			sum = sum + arr.get(i).longValue();
//			min = Math.min(min, arr.get(i));
//			max = Math.max(max, arr.get(i));
//		}
//		
//		System.out.println((sum - max) + " " + (sum - min));
//		String[] time = "hh:MM:ssAM".substring(0,8).split(":");
//		System.out.println(1/2);
//		
//		List<Integer> ar = Arrays.asList(10, 20, 20, 10, 10, 30, 50, 10, 20);
////		Set<Integer> set = new HashSet<>(ar);
////		System.out.println("set: "+set.size());
////		int numpair=0;
////		for(Integer s : set) {
////			System.out.println(Collections.frequency(ar, s));
////			numpair = Collections.frequency(ar, s)/2 > 0 ? Collections.frequency(ar, s)/2 : 0;
////			System.out.println(numpair);
////			
////		}
//		
//		final int numpair;
//		Map<Integer, Long> counts = ar.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
//		System.out.println(counts.size());
//		counts.forEach((k, v) -> System.out.println((k + ":" + v)));
//		counts.forEach((k, v) -> {
//			if(v/2 > 0) {
//				numpair = (int) (numpair + v/2);
//			}
//		});
		
		String code = "1|083000000053345|23032022|AFRI_ISSU|PAIE FACTURE 20158428691240823032022|15473129|D";
		String[] arrayCode = code.split("\\|");
		for(String str : arrayCode) {
			System.out.println(str);
		}
		
	}

}
