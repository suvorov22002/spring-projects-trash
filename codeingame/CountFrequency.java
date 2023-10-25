package com.codingame;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class CountFrequency {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		textWordFreq();
		text3WordFreq();
		wordFreqV2();
	}
	
	public static void textWordFreq() {
	    String text = "Ann while Bob had had had had had had had had had had had a better effect on on the teacher";
	    ConcurrentMap<String, Integer> freqMap =
	            Arrays.asList(text.split("[\\s.]"))
	                    .parallelStream()
	                    .filter(s -> !s.isEmpty())
	                    .collect(Collectors.toConcurrentMap(w -> w.toLowerCase(), w -> 1, Integer::sum));
	    System.out.println(freqMap.toString());
	}
	
	public static void text3WordFreq() {
	    String text = "Ann while Bob had had had had had had had had had had had a better effect on on the teacher";
	    ConcurrentMap<String, Integer> freqMap =
	            Arrays.asList(text.split("[\\s.]"))
	                    .parallelStream()
	                    .filter(s -> !s.isEmpty())
	                    .collect(Collectors.toConcurrentMap(w -> w.toLowerCase(), w -> 1, Integer::sum));
	    System.out.println(freqMap.toString());

	    //Priority queue that uses frequency as the comparator and size as 3
	    PriorityQueue<String> pq = new PriorityQueue<>(Comparator.comparingInt(freqMap::get));  
	    for(String key: freqMap.keySet()) {
	        pq.add(key);    
	        if(pq.size() > 3) {
	            pq.poll();  
	        }
	    }
	    System.out.println("Top 3 words by occurrences  : " + pq);
	}
	
	public static void wordFreqV2() {
	    String text = "Ann while Bob had had had had had had had had had had had a better effect on on the teacher";
	    Map<String, Integer> freqMap = new HashMap<>();
	    Arrays.asList(text.split("[\\s.]")).forEach(s -> {
	        freqMap.compute(s, (s1, count) -> count == null ? 1 : count + 1);
	    });
	    System.out.println(freqMap.toString());
	}
}
