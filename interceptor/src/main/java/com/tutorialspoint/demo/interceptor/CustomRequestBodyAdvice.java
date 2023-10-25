package com.tutorialspoint.demo.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@ControllerAdvice
public class CustomRequestBodyAdvice implements RequestBodyAdvice {

    @Autowired
    HttpServletRequest httpServletRequest;

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        System.out.println("In supports() method of " + getClass().getSimpleName());
        return methodParameter.getContainingClass() == QuestionController.class
                && targetType.getTypeName() == Question.class.getTypeName();
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
                                           Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        System.out.println("In beforeBodyRead() method of " + getClass().getSimpleName());
        System.out.println("Method: " + httpServletRequest.getMethod());
        return inputMessage;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
                                Class<? extends HttpMessageConverter<?>> converterType) {
        System.out.println("In afterBodyRead() method of " + getClass().getSimpleName());

        AuditEntity auditEntity = new AuditEntity();
        if (body instanceof Question question) {
            question.setDate(new Date());
            auditEntity.setUser("Current Log User");
            auditEntity.setOperation(Objects.requireNonNull(parameter.getMethod()).getName());
            auditEntity.setDateTime(LocalDateTime.now());
            //auditEntity.setObject(question);
            question.setAuditEntity(auditEntity);
            return question;

        }

        return body;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
                                  Class<? extends HttpMessageConverter<?>> converterType) {
        System.out.println("In handleEmptyBody() method of " + getClass().getSimpleName());
        return body;
    }
}
