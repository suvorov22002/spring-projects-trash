package com.features.futures;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ComputeFutur {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        Future<String> completeFuture = calculAsync();
//        System.out.println(completeFuture.get());

        combinedMultiFuture();
//        handleFutureException();
//
//        System.out.println("Arrondi: " + Math.floor(10000/3));
//        System.out.println("Arrondi: " + Math.ceil(5000/3));

    }


    private static Future<String> calculAsync() throws InterruptedException {

        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(() -> {
            Thread.sleep(10000);
            completableFuture.complete("Hello");
            return null;
        });

        return completableFuture;
    }

    private static void combinedMultiFuture() throws ExecutionException, InterruptedException {

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "Beautiful");
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> "World");

        CompletableFuture<Void> combined = CompletableFuture.allOf(future1, future2, future3);
        combined.get();

       String result = Stream.of(future1, future2, future3).map(CompletableFuture::join).collect(Collectors.joining(" "));
        System.out.println(result);
    }

    private static void handleFutureException() throws ExecutionException, InterruptedException {

        String name = null;
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            if(name == null) {
                throw new RuntimeException("Computation error, no name provided");
            }
            return "Hello, " + name;
        }).handle((s,t) -> s != null ? s : "Hello Stranger");

        String result = completableFuture.get();
        System.out.println(result);
    }
}
