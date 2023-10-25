package com.tutorialspoint.demo.interceptor;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionController {

    @PostMapping(value = "/ask", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Answer ask(@RequestBody Question question) {
        System.out.println("In controller method send to Audit");

        Answer answer = new Answer();
        answer.setAnswerMessage("I don't know!");
        answer.setQuestion(question);
        return answer;
    }

}
