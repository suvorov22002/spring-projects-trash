package com.features.interviews;

import java.math.BigDecimal;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Java8code {

    static List<Integer> listOfIntegers = Arrays.asList(71, 18, 42, 21, 67, 32, 95, 14, 56, 87);
    static List<String> listOfStrings = Arrays.asList("Java", "Python", "C#", "Java", "Kotlin", "Python");
    static String inputString = "Java Concept Of The Day";
    static List<String> stationeryList = Arrays.asList("Pen", "Eraser", "Note Book", "Pen", "Pencil", "Stapler", "Note Book", "Pencil");
    static List<Double> decimalList = Arrays.asList(12.45, 23.58, 17.13, 42.89, 33.78, 71.85, 56.98, 21.12);
    static List<String> listOfString = Arrays.asList("Facebook", "Twitter", "YouTube", "WhatsApp", "LinkedIn");
    static List<Integer> listOfInteger = Arrays.asList(45, 12, 56, 15, 24, 75, 31, 89);
    static int[] a = new int[] {4, 2, 5, 1};
    static int[] b = new int[] {8, 1, 9, 5};
    static String stra = "RaceCar";
    static String strb = "CarRace";
    static List<Integer> list1 = Arrays.asList(71, 21, 34, 89, 56, 28);
    static  List<Integer> list2 = Arrays.asList(12, 56, 17, 21, 94, 34);
    static List<String> listOfString2 = Arrays.asList("Pen", "Eraser", "Note Book", "Pen", "Pencil", "Pen", "Note Book", "Pencil");
    static List<String> listOfString3 = Arrays.asList("One", "2wo", "3hree", "Four", "5ive", "Six");
    static List<Integer> listOfInteger3 = Arrays.asList(111, 222, 333, 111, 555, 333, 777, 222);
    static String inputString2 = "Java Concept Of The Day".replaceAll("\\s+", "").toLowerCase();
    static List<String> listOfStrings2 = Arrays.asList("One", "Two", "Three", "Four", "Five", "Six");


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // separateOddEven(listOfIntegers);
        // removeDuplicate(listOfStrings);
        // frequencyOfCharacterInString(inputString);
         frequenceOfElementInCollection(stationeryList);
        // sortInReverseOrder(decimalList);
        // joinStrings(listOfString);
        // multipleOfFive(listOfInteger);
        // multipleOf5(listOfInteger);
        // findMinMaxOfList(listOfInteger);
        // merge2Arrays(a, b);
        // merge2ArraysWithoutDuplicate(a, b);
        // find3MaximumAnd3Minimum(listOfInteger);
        // checkAnagramm(stra, strb);
        // findSumOfAllDigitOfNumber(15623);
        // secondLargest(listOfInteger);
        // rangeStringByLength(listOfStrings);
        // sumAndAverage(listOfInteger);
        // commonElementBetween2Array(list1, list2);
//         reverseEachWord(inputString);
        // sumOf10FirstNaturalNumber();
        // reverseIntegerArray(a);
        // first10EvenNumber();
        // mostRepeatedElement(listOfString2);
         checkPalindrome("aa");
        // findFirstdigit(listOfString3);
        // extractDuplicate(listOfInteger3);
        // findDuplicateCharacter(inputString2);
        // findFirstDuplicateCharacter(inputString2);
        // firstNonRepeatedElement(inputString2);
        // fibonnaciSeries();
        //firstOddNumber();
        // lastElementOfArray(listOfStrings2);
        //getAge(LocalDate.of(1984, 9,27));
        // System.out.println("Is Anagrame: " + isAnagram(stra, strb));
        localTimeDate();

        Supplier<String> place = () -> "Viena";
        Places places = new Places();
        populate(places, place);
        Consumer<Places> consumer = (p -> System.out.println(p.location));

        completeSkillValue();


       int sum = Stream.of(2,4,6,8,10).collect(Collectors.summingInt(i->i));
        System.out.println("sum 1: " + sum);
        sum = IntStream.rangeClosed(1,10).filter(i -> i % 2 == 0).sum();
        System.out.println("sum 2: " + sum);


        System.out.printf("%b, %b, %b ", " ".isBlank(), "Test".isBlank(), "".isBlank());

    }

    /**
     * This take a collection and separate odd and even
     * @param collection
     */
    private static void separateOddEven(List<Integer> collection) {

        Map<Boolean, List<Integer>> oddEvenNumbersMap = collection.stream()
                .collect(Collectors.partitioningBy(i -> i % 2 == 0));

        Set<Map.Entry<Boolean, List<Integer>>> entrySet = oddEvenNumbersMap.entrySet();
        // Collection<List<Integer>> listValeurs = oddEvenNumbersMap.values();
        // Collection<Boolean> listCle = oddEvenNumbersMap.keySet();
        // System.out.println(oddEvenNumbersMap);
        System.out.println(entrySet);

        for(Map.Entry<Boolean, List<Integer>> entry : entrySet) {

            System.out.println("--------------");
            if (entry.getKey())
            {
                System.out.print("Even Numbers:");
            }
            else
            {
                System.out.print("Odd Numbers:");
            }

            List<Integer> list = entry.getValue();
            System.out.println(" " + list);
            System.out.println("--------------");
        }
    }

    /**
     * Remove duplicate element inside the collection
     * @param collection
     * @param <T>
     */
    private static <T> void removeDuplicate(Collection<T> collection) {
        System.out.println("With possible duplicates: " + collection);
        System.out.print("Without duplacte: ");
        List<T> uniqueElements = collection.stream().distinct().collect(Collectors.toList());
        System.out.print("Without duplicate: " + uniqueElements);
    }

    /**
     * Frequency of a character in a string
     * @param chainWord
     */
    private static void frequencyOfCharacterInString(String chainWord) {

        Map<Character, Long> charCountMap = chainWord.toUpperCase().chars().mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        System.out.println("Frequency in word: " + charCountMap);
    }

    /**
     * Count the frequency of string in list of strings
     * @param collection
     * @param <T>
     */
    private static <T> void frequenceOfElementInCollection(Collection<T> collection) {

        Map<T, Long> stringCountMap = collection.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        System.out.println("Frequency in list String: " + stringCountMap);

    }

    /**
     * Sort collection in reverse order
     * @param numbers
     */
    private static void sortInReverseOrder(Collection<? extends Number> numbers) {

        Collection<? extends Number> collection = numbers.stream().sorted(Collections.reverseOrder())
                .toList();

        System.out.println("Reversed order: " + collection);
    }

    /**
     * join list of strings
     * @param collection
     */
    private static void joinStrings(Collection<? extends String> collection) {

        String joiningString = collection.stream().collect(Collectors.joining(",", "[", "]"));
        System.out.println(joiningString);
    }

    /**
     * Search all multiple of 5 in a given list of numbers
     * @param numbers
     */
    private static void multipleOfFive(List<Integer> numbers) {

        List<Integer> multiple5 = numbers.stream().filter(i -> i%5 == 0).collect(Collectors.toList());
        System.out.println("Multiple de 5: " + multiple5);
    }

    private static void multipleOf5(List<Integer> numbers) {

        Map<Boolean, List<Integer>> multiple5 = numbers.stream().collect(Collectors.partitioningBy(i -> i%5 == 0));
        Set<Map.Entry<Boolean, List<Integer>>> entrySet = multiple5.entrySet();

        for(Map.Entry<Boolean, List<Integer>> entry : entrySet) {

            if(entry.getKey()){
                System.out.println("Multiple de 5: " + entry.getValue());
            }
            else{
                System.out.println("Pas Multiple de 5: " + entry.getValue());
            }

        }

    }

    /**
     * Find min and max element from the list
     * @param collection
     */
    private static void findMinMaxOfList(Collection<Integer> collection) {

        int min = collection.stream().min(Comparator.naturalOrder()).get();
        int max = collection.stream().max(Comparator.naturalOrder()).get();

        System.out.println("Min of collection: " + min);
        System.out.println("Max of collection: " + max);
    }

    /**
     * Concat two array of integer and sort
     * @param arr
     * @param brr
     */
    private static void merge2Arrays(int[] arr, int[] brr) {

        int[] crr = IntStream.concat(Arrays.stream(arr), Arrays.stream(brr)).sorted().toArray();
        System.out.println("First array: " + Arrays.toString(arr));
        System.out.println("Second array: " + Arrays.toString(brr));
        System.out.println("Merged array: " + Arrays.toString(crr));
        Arrays.stream(crr)
                //.mapToObj(i -> (int)i)
                .boxed()
                //.peek(System.out::println)
                .sorted(Collections.reverseOrder())
                .forEach(System.out::print);
    }

    /**
     * Concate two arrays of integers and sort with unique element
     * @param arr
     * @param brr
     */
    private static void merge2ArraysWithoutDuplicate(int[] arr, int[] brr) {

        int[] crr = IntStream.concat(Arrays.stream(arr), Arrays.stream(brr)).sorted().peek(System.out::print).distinct().toArray();
        System.out.println();
        System.out.println("Merged unique: " + Arrays.toString(crr));
    }

    /**
     * 3 maximum and 3 minimum from collection of number
     * @param collection
     */
    private static void find3MaximumAnd3Minimum(Collection<? extends Number> collection) {


        List<? extends Number> minimums = collection.stream().sorted().limit(3).collect(Collectors.toList());
        System.out.println("** Minimum: " + minimums);

        List<? extends Number> maximums = collection.stream().sorted(Collections.reverseOrder()).limit(3).collect(Collectors.toList());
        System.out.println("** Maximum: " + maximums);
    }

    /**
     * verified if two strings are annagrams
     * @param stra
     * @param strb
     */
    private static void checkAnagramm(String stra, String strb) {

        stra = Stream.of(stra.split("")).map(String::toUpperCase).peek(System.out::print).sorted().collect(Collectors.joining());
        System.out.println();
        strb = Stream.of(strb.split("")).map(String::toUpperCase).peek(System.out::print).sorted().collect(Collectors.joining());
        System.out.println();
        if(stra.equals(strb)) {
            System.out.println("Strings are Anagram");
        }
        else{
            System.out.println("Strings are not anagram");
        }
    }

    /**
     * sum of digits of given number
     * @param num
     */
    private static void findSumOfAllDigitOfNumber(Number num) {

        int sum = Stream.of(String.valueOf(num).split("")).peek(System.out::print)
                .collect(Collectors.summingInt(i -> Integer.parseInt(i)));
        System.out.println();
        System.out.println("La somme des digits est: " + sum);
    }

    /**
     * Find second largest number in collection of numbers
     * @param collection
     */
    private static void secondLargest(Collection<? extends Number> collection) {

        Number second = collection.stream().sorted(Collections.reverseOrder()).skip(1).findFirst().get();
        System.out.println("Second largest: " + second);
    }

    /**
     * range list of string in increasing length of string
     * @param collection
     */
    private static void rangeStringByLength(List<String> collection) {

        List<String> inputString = collection.stream().distinct().sorted(Comparator.comparing(String::length)).toList();

        System.out.println("Ranged string: " + inputString);
    }

    /**
     * Sum and average of total element in list of numbers
     * @param collection
     */
    private static void sumAndAverage(Collection<? extends Number> collection) {

        Number sum = collection.stream().mapToDouble(Number::doubleValue).sum();
        sum = collection.stream().collect(Collectors.summingInt(Number::intValue));

        Number average = collection.stream().mapToDouble(Number::doubleValue).average().getAsDouble();
        average = collection.stream().collect(Collectors.averagingDouble(Number::doubleValue));

        System.out.println("Total sum: " + sum);
        System.out.println("Average: " + average);
    }

    private static void commonElementBetween2Array(List<Integer> number1, List<Integer> number2) {

//        Stream<Integer> uniqueNumber1 = number1.stream().distinct();
//        Stream<Integer> uniqueNumber2 = number2.stream().distinct();
//
//        Stream<Integer> concatStream = Stream.concat(uniqueNumber1, uniqueNumber2);
//
//        Map<Integer, Long> integerLongMap = concatStream.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
//        Set<Map.Entry<Integer, Long>> entrySet = integerLongMap.entrySet();
//
//        for(Map.Entry<Integer, Long> entry : entrySet) {
//
//            if(entry.getValue() > 1) {
//                System.out.println("Element communs: " + entry.getKey());
//            }
//
//        }
          List<Integer> listCommon = number1.stream()
                  .filter(number2::contains)
                  .toList();
          System.out.println("Element communs: " + listCommon);
    }

    /**
     * reverse each word in the string
     * @param inputString
     */
    private static void reverseEachWord(String inputString) {

        String reverseWord = Arrays.stream(inputString.split(" ")).map(str -> new StringBuffer(str).reverse())
                .collect(Collectors.joining(" "));

        System.out.println("Reversed word: " + reverseWord);
    }

    /**
     * Sum of 10 first non null natural number
     */
    private static void sumOf10FirstNaturalNumber() {

        int sum = IntStream.rangeClosed(1, 10).reduce(0, Integer::sum);
        System.out.println("Sum of first 10 natural number: " + sum);
    }

    /**
     * reverse array of integer
     * @param numbers
     */
    private static void reverseIntegerArray(int[] numbers) {

        System.out.println("Reverse array: " + Arrays.toString(a));
        int[] reverseArray = IntStream.rangeClosed(1, numbers.length).map(a -> numbers[numbers.length - a]).toArray();

        System.out.println("Reverse array: " + Arrays.toString(reverseArray));
    }

    /**
     * first even number
     */
    private static void first10EvenNumber() {

        int[] evenNumber = IntStream.rangeClosed(0,9).map(n -> n * 2).toArray();
        System.out.println("10 First even number: " + Arrays.toString(evenNumber));
    }

    /**
     * search most repeated element in the collection
     * @param collection
     * @param <T>
     */
    private static <T> void mostRepeatedElement(Collection<T> collection) {

        Map<T, Long> elementCountMap = collection.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Map.Entry<T, Long> mostFrequentElement = elementCountMap.entrySet().stream().max(Map.Entry.comparingByValue()).orElse(null);
        assert mostFrequentElement != null;
        System.out.println("The most repeated element is: " + mostFrequentElement.getKey());
        System.out.println("Count : "+mostFrequentElement.getValue());
    }

    private static void checkPalindrome(String inputString) {

        //String reverseString = new StringBuffer(inputString).reverse().toString();

        boolean isItPalindrome = IntStream.range(0, inputString.length()/2).
                noneMatch(i -> inputString.charAt(i) != inputString.charAt(inputString.length() - i -1));

      //  if(reverseString.equals(inputString)){
        if(isItPalindrome){
            System.out.println("Le mot est un palindrome");
        }
        else{
            System.out.println("Le mot n'est pas un palindrome");
        }
        String[] str = {"99.35", "1.10"};
        BigDecimal sum = Arrays. stream(str).map(BigDecimal::new).reduce(BigDecimal.ZERO, BigDecimal::add);


        System.out.println("sume "+sum);

    }

    private static void findFirstdigit(List<String> collection) {

        List<String> digitString = collection.stream().filter(str -> Character.isDigit(str.charAt(0))).toList();
        System.out.println("First Digit: " + digitString);

    }

    /**
     * extract duplicate element in the collection
     * @param collection
     */
    private static void extractDuplicate(List<Integer> collection) {

        Set<Integer> uniqueElement = new HashSet<>();

        Set<Integer> duplicateElement = collection.stream().filter(e -> !uniqueElement.add(e)).collect(Collectors.toSet());
        System.out.println("Duplicate Element: " + duplicateElement);
    }

    /**
     * duplicate character in the string
     * @param str
     */
    private static void findDuplicateCharacter(String str) {

        Set<String> uniqueChar = new HashSet<>();
        Set<String> duplicateChar = Arrays.stream(str.split("")).filter(e -> !uniqueChar.add(e)).collect(Collectors.toSet());
        System.out.println("Duplicate character: " + duplicateChar);
    }

    /**
     * first duplicate character in the string
     * @param str
     */
    private static void findFirstDuplicateCharacter(String str) {

//        Set<String> uniqueChar = new HashSet<>();
//        String duplicateChar = Arrays.stream(str.split("")).filter(e -> !uniqueChar.add(e)).findFirst().get();
//        System.out.println("First Duplicate character: " + duplicateChar);

        Map<String, Long> charCountMap =
                Arrays.stream(str.split(""))
                        .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));
        System.out.println(charCountMap);
        String firstRepeatedChar = charCountMap.entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1)
                .map(entry -> entry.getKey())
                .findFirst()
                .get();

        System.out.println(firstRepeatedChar);
    }

    /**
     * first non repeated character in the string
     * @param str
     */
    private static void firstNonRepeatedElement(String str) {

        Map<String, Long> stringCountMap = Arrays.stream(str.split(""))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));

        String firstNonRepeated = stringCountMap.entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1)
                .map(entry -> entry.getKey())
                .findFirst()
                .get();

        System.out.println("First non repeated element: " + firstNonRepeated);
    }

    /**
     * first 10 elements of Fibonnaci series
     */
    private static void fibonnaciSeries() {

        Stream.iterate(new int[] {0,1}, f -> new int[] {f[1], f[0] + f[1]})
                .limit(10)
                .map(f -> f[0])
                .forEach(e -> System.out.print(e + " "));

        /*Stream.iterate(new int[] {1}, f -> new int[] {1+f[0], f[0]*f[0]})
                .skip(1)
                .limit(10)
                .map(f -> f[1])
                .forEach(e -> System.out.print(e + " "));*/

    }

    /**
     * first 10 odds numbers
     */
    private static void firstOddNumber() {

        int[] oddArr = IntStream.rangeClosed(0,9).map(n -> 2*n + 1).toArray();
        System.out.println("First 10 odd numbers: " + Arrays.toString(oddArr));

        Stream.iterate(new int[] {0}, f -> new int[] {1+f[0], 1+ 2*f[0]})
                .skip(1)
                .limit(10)
                .map(f -> f[1])
                .forEach(e -> System.out.print(e + " "));
    }

    /**
     * Last element of an collection
     * @param collection
     * @param <T>
     */
    private static <T> void lastElementOfArray(List<T> collection) {

        T element = collection.stream().skip(collection.size()-1).findFirst().get();
        System.out.println("Last element: " + element);

    }

    private static void getAge(LocalDate birthday) {

        LocalDate today = LocalDate.now();
        System.out.println(ChronoUnit.YEARS.between(birthday, today));
    }

    static boolean isAnagram(String a, String b) {
        // Complete the function
        Map<String, Long> countfrqMap1 = Arrays.stream(a.split("")).map(String::toUpperCase)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        Map<String, Long> countfrqMap2 = Arrays.stream(b.split("")).map(String::toUpperCase)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        // return countfrqMap1.equals(countfrqMap2);
         return countfrqMap1.entrySet().stream()
                .allMatch(e -> e.getValue().equals(countfrqMap2.get(e.getKey())));
    }

    private static void completeSkillValue() throws ExecutionException, InterruptedException {

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> "Hello");

        CompletableFuture<Void> c1 = completableFuture.thenAccept(s -> System.out.println(s + " World"));
        c1.get();

        CompletableFuture<Void> future = completableFuture.thenRun(() -> System.out.println("Computation finished."));
        future.get();

        CompletableFuture<String> c2 = CompletableFuture.supplyAsync(() -> {
            try{
                Thread.sleep(1000);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
            return "A B C";
        });
        c2.complete("Hello World");
        System.out.println("2: " + c2.get());
    }

    private static void localTimeDate() {
        LocalDateTime localDateTime = LocalDateTime
                .parse("2023-07-04 14:06", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        ZoneId zoneId = ZoneId.of("+02:00");
        ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
        System.out.println(zonedDateTime.withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime());

        TemporalAdjuster temporalAdjuster = t -> t.plus(Period.ofDays(14));

    }

    static class Places {
        String location;
    }
    static <R> void populate(Places t, Supplier<R> supplier) {
        t.location = String.valueOf(supplier.get());
    }


}
