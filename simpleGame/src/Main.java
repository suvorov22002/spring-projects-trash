import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import static java.util.AbstractMap.SimpleEntry;
import static java.util.Map.Entry.*;
import static java.util.stream.Collectors.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // Press Alt+Entrée with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.println(System.getProperty("java.runtime.version"));


        int l = 0;
        List<String> symbols = Arrays.asList("AAPL", "GOOG", "IBM", "JAVA", "MSFT");
        System.out.println("Hello and welcome! "+symbols);
        Map<String, Float> mapWeather = new HashMap<>();
        float m2[][] = {
                {6.2f, 14.1f, 2.3f, 5.7f, 8.2f },
                {8.01f, 11.1f, 10.3f, 14.3f, 7.8f},
                {6.6f, 10.2f, 13.4f, 15.7f, 8.05f}
        };
        int rows = m2.length;
        int columns = m2[0].length;
        float sum = 0f;
        //System.out.println("Length rows: " + rows);
        //System.out.println("Length columns: " + columns);
        for(int i=0; i<columns;i++){
            sum = 0f;
            for(int j=0; j<rows;j++){
                System.out.println("rows value: ["+j+","+ i+"] = " +  + m2[j][i]);
                sum += m2[j][i];
            }
            mapWeather.put(symbols.get(l++), sum);
        }
        System.out.println("Hello and welcome! "+mapWeather);
        //Map<String, Float> mapWeather = new HashMap<>();

        //List<Float> price = Arrays.asList(12.32f, 18.03f, 17.96f, 22.12f, 11.32f);
        int i = 0;

        Map<String, Float> topStock = getTopStock(mapWeather);

        System.out.println("map after sorting by values: " + topStock);
        topStock = getTopStockDesc(mapWeather);
        System.out.println("map after sorting by values in decreasing order: " + topStock);

        SimpleEntry<String, Float> topStocks = getTopStocks(symbols, mapWeather);
        System.out.println("map after parallel: " + topStocks);
        textWordFreq();

        int[] arr = {1, 1, 2, 8, 9, 8, 4, 7, 4, 9, 1};
        for (Integer s : arr
        ) {
            System.out.print(" "+s);
        }
        System.out.println();
        int[] arrOut = Arrays.stream(arr).distinct().toArray();
        for (Integer s : arrOut
             ) {
            System.out.print(" "+s);
        }
        System.out.println();
        int max = Arrays.stream(arr).max().getAsInt();
        System.out.println("Max Element: " + max);

        LinkedList<Person> linkedList = new LinkedList<>();
        Deque<Person> deque = new ArrayDeque<>();

        System.out.println("Object length: " + getLength(symbols));
    }

    record Person(String name, int age) {}

    private static  Map<String, Float> getTopStock(Map<String, Float> mapWeather){

        return mapWeather.entrySet().stream()
                .sorted(comparingByValue()).limit(3)
                .collect(
                        toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                LinkedHashMap::new)
                );
    }

    private static  Map<String, Float> getTopStockDesc(Map<String, Float> mapWeather){

        return mapWeather.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(
                        toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                LinkedHashMap::new)
                );
    }

    private static SimpleEntry<String, Float> getTopStocks(List<String> symbols, Map<String, Float> mapWeather) {
        return symbols.parallelStream()
                .map(symbol -> getYearEndClosing(symbol, mapWeather.get(symbol)))
                .reduce((highest, price) -> price.getValue() < highest.getValue() ? price : highest)
                .get();
    }

    private static SimpleEntry<String, Float> getYearEndClosing(String symbol, float year) {
        try {
            //String uri = "http://ichart.finance.yahoo.com/table.csv?s=" + symbol + "&a=11&b=01&c=" + year + "&d=11&e=31&f=" + year + "&g=m";
            //List<String> data = IOUtils.readLines(new URL(uri).openStream()).stream().map(String::trim).collect(Collectors.toList());
            return new SimpleEntry<>(symbol, year);
        } catch (Exception e) {
            return new SimpleEntry<>(symbol, 0F);
        }
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

    private static int getLength(Object obj) {

        return switch(obj) {
            case String s -> s.length();
            case List list && !list.isEmpty() -> list.size();
            case null -> 0;
            default -> -1;
        };

    }
}