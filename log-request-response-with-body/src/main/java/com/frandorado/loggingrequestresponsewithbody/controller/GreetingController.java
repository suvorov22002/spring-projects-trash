package com.frandorado.loggingrequestresponsewithbody.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.frandorado.loggingrequestresponsewithbody.model.GreetingRequest;
import com.frandorado.loggingrequestresponsewithbody.model.GreetingResponse;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private final AtomicLong count = new AtomicLong();

    @GetMapping("greetings/{id}")
    public GreetingResponse getGreeting(@PathVariable("id") Long id) {
        return GreetingResponse.builder().id(String.valueOf(id)).message("Hello world!").build();
    }

    @GetMapping("greetings/all")
    public List<GreetingResponse> getGreeting() throws JsonProcessingException {
        List<GreetingResponse> greetingResponseList = new ArrayList<>();
        greetingResponseList.add(GreetingResponse.builder().id(String.valueOf(1L)).message("Hello world!").build());
        greetingResponseList.add(GreetingResponse.builder().id(String.valueOf(2L)).message("Hello RabbitMQ!").build());

        String json = "[{\"id\":\"1\",\"message\":\"Hello world!\"},{\"id\":\"2\",\"message\":\"Hello RabbitMQ!\"}]";

        ObjectMapper om = new ObjectMapper();
        List<GreetingResponse> h = om.readValue(json, new TypeReference<List<GreetingResponse>>(){});
        System.out.println("OM: " + h);
        h.forEach(System.out::println);
        System.out.println("Application Name: " +  ApplicationContext.class.getSimpleName());



        return greetingResponseList;
    }

//    @PostMapping("greetings")
//    public GreetingResponse createGreeting(@RequestBody GreetingRequest greetingRequest) {
//        return GreetingResponse.builder().id(count.incrementAndGet()).message(greetingRequest.getMessage()).build();
//    }
//
//    @DeleteMapping("greetings")
//    public GreetingResponse deleteGreeting(@RequestBody GreetingRequest greetingRequest) {
//        System.out.println("Deleting");
//        return GreetingResponse.builder().id(count.incrementAndGet()).message(greetingRequest.getMessage()).build();
//    }
}
