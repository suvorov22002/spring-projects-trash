package com.tutorialspoint.demo.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.DispatcherType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ProductServiceInterceptor implements HandlerInterceptor {

    private static Logger log = LoggerFactory.getLogger(ProductServiceInterceptor.class);
    @Autowired
    LoggingService loggingService;
    int count = 0;
    @Override
    public boolean preHandle
            (HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //System.out.println("URI: " + request.getRequestURI());
        System.out.println("STEP 1 >>>> Here we set requestId and send it to audit's service with status in progress ");
    //    String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        if (DispatcherType.REQUEST.name().equals(request.getDispatcherType().name())
                && request.getMethod().equals(HttpMethod.GET.name())) {
            loggingService.logRequest(request, null);
        }

        String uuid = this.getRandomString.apply(null);
        System.out.println("RequestId: " + uuid);
        //System.out.println("Method: " + request.getMethod());
        //System.out.println("Method URI: " + request.getRequestURI());

        //System.out.println("Pre Handle method is Calling: " + handler);
        MDC.put("requestId", uuid);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("URI: " + request.getRequestURI());
        //request = new ContentCachingRequestWrapper(request);
        System.out.println("STEP 2 >>>> Get the response of request and status, send it to audit's service with status updated");
        //System.out.println("Post Handle method is Calling");
        //System.out.println("requestId: "  + MDC.get("requestId"));
        //System.out.println("Response: " + response.getStatus());
    }
    @Override
    public void afterCompletion
            (HttpServletRequest request, HttpServletResponse response, Object
                    handler, Exception exception) throws Exception {

        //request = new ContentCachingRequestWrapper(request);
        System.out.println("STEP 3 >>>> Request and Response is completed");
        //MDC.remove("requestId");
    }

    public Function<Void, String> getRandomString = nothing ->
            UUID.randomUUID().toString().replaceAll("-","");

}
