package com.tutorialspoint.demo.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

@Service
public class LoggingServiceImpl implements LoggingService{
    @Override
    public void logRequest(HttpServletRequest httpServletRequest, Object body) {

    }

    @Override
    public void logResponse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object body) {

    }
}
