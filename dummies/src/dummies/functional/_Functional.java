package dummies.functional;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Timer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class _Functional {
	
	public static void main(String ...args) {
		long end;
		long start = System.currentTimeMillis();
		Integer[] tab = {1, 4, 9, 12, 98, -10, 10};
		List<Integer> liste = Arrays.asList(tab);
		
		System.out.println("sumWithcondition " + sumWithcondition(liste, n -> true));
		//System.out.println("Is prime number: " + isPrime(12));
		end = System.currentTimeMillis();
		System.out.println("Execution time in seconds: " + (end - start));
		
		System.out.println("" + sumWithcondition(liste, n -> true));
		System.out.println("" + sumWithcondition(liste, n -> n%2 == 0));
		System.out.println("" + sumWithcondition(liste, n -> n > 5));
		System.out.println("findSquareOfMaxOdd " + findSquareOfMaxOdd());
		System.out.println("sumIteratorSup10 " + sumIterator(liste));
		
		manipulateChar();
		System.out.println("CloseToZero: " + closetToZero());
		method2();
		System.out.println("optional: " + findUniqueResource(Optional.ofNullable("")));
		
		
	}
	
	private static boolean isPrime(int number) {
		
		IntPredicate isprime = x -> number % x == 0;
		
		return number > 2 && IntStream.range(2, number).noneMatch(isprime);
	}
	
	private static boolean isPrimes(int number) {		
		if(number < 2) return false;
		for(int i=2; i<number; i++){
			if(number % i == 0) return false;
		}
		return true;
	}
	
	private static int findSquareOfMaxOdd() {
		//IntStream.rangeClosed(3, 11).forEach(System.out::println);
		return IntStream.range(3, 11).boxed().
				filter(n -> n%2 != 0).max(Comparator.naturalOrder()).map(x -> x *x ).get();
	}
	
	private static int findSquareOfMaxOdd(List<Integer> numbers) {
		return numbers.stream()
				.filter(_Functional::isOdd) 		//Predicate is functional interface and
				.filter(_Functional::isGreaterThan3)	// we are using lambdas to initialize it
				.filter(_Functional::isLessThan11)	// rather than anonymous inner classes
				.max(Comparator.naturalOrder())
				.map(i -> i * i)
				.get();
	}

	private static boolean isOdd(int i) {
		return i % 2 != 0;
	}
	
	private static boolean isGreaterThan3(int i){
		return i > 3;
	}
	
	private static boolean isLessThan11(int i){
		return i < 11;
	}
	
	private static int sumWithcondition(List<Integer> numbers, Predicate<Integer> predicate) {
		
		return numbers.parallelStream().filter(predicate).mapToInt(e -> e).sum();
		
	}
	
	private static int sumIterator(List<Integer> list) {
		return list.stream().filter(n -> n > 10).mapToInt(i -> i).sum();
	}
	
	 private static void manipulateChar() {
	        List<String> names = Arrays.asList("ABC", "CAB", "BCA");
	        String result = names.stream().reduce("", (a,b) -> a + b.charAt(1));
	        System.out.println(result);
	        
	        BiFunction<Integer, Integer, Integer> accumulator = (a, b) -> a + b;
	        
	        Integer[] tab = {1, 4, 9, 12, 98, -10, 10};
	        Integer res = 0;
	        for (Integer element : tab)
	            res = accumulator.apply(res, element);
	        System.out.println("RESULTAT: " + res);
	        
	        Arrays.stream(tab).reduce(0, (a, b) -> a + b);
	        
	 }
	 
	 private static int closetToZero() {
		 int[] numbers = {-2, 1, 2, 3, 4};
//		 BiFunction<Integer, Integer, Integer> acc = (a, b) -> Math.abs(a) < Math.abs(b) ? a : (Math.abs(a) == Math.abs(b)) ? Math.max(a, b) : b;
//		 
//		 Integer sum = 0;
//	        for (Integer element : numbers)
//	            sum = acc.apply(sum, element);
	        
	        return Arrays.stream(numbers)
			 .reduce((a, b) -> Math.abs(a) < Math.abs(b) ? a : (Math.abs(a) == Math.abs(b) ? Math.max(a, b) : b)).getAsInt();
	        
	        //return sum;
	        

	 }
	 
	 private static void method2() {
		 
		 Optional num = Stream.of(9, 5, 8, 7, 4, 9, 2, 11, 10, 3)
				 		.filter(n -> n > 10)
				 		.filter(n -> n % 5 == 0)
				 		.findFirst();
		 int sum = Stream.of(9, 5, 8, 7, 4, 9, 2, 11, 10, 3).collect(Collectors.averagingInt(x -> x)).intValue();
		 System.out.println("Optional Num: " + sum);
		 
		 Supplier<String> s = () -> "provide";
		 System.out.println("Supplier: " + s.get());
		 
		 Consumer<String> con = a -> System.out.print("Consumer: " + a);
		 con.accept("EKI");
		 
		 System.out.println();
	 }
	 
	 private static String findUniqueResource(Optional<String> id){
		 Supplier<String> s = () -> "Not provided";
		 return id.orElseGet(s);
	 }
}
