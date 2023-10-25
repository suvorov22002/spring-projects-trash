package com.frandorado.loggingrequestresponsewithbody.interceptor;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.frandorado.loggingrequestresponsewithbody.bootstrap.Utils;
import com.frandorado.loggingrequestresponsewithbody.service.LoggingService;
import com.frandorado.loggingrequestresponsewithbody.service.LoggingServiceImpl;
import com.tefo.library.commonutils.basestructure.dto.AuditTrailRequest;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Component
public class LogInterceptor implements HandlerInterceptor {
    
    @Autowired
    LoggingService loggingService;

    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){

        if (DispatcherType.REQUEST.name().equals(request.getDispatcherType().name()) && request.getMethod().equals(HttpMethod.GET.name())) {

            String uuid = Utils.getRandomString.apply(null);
            System.out.println("RequestID-1: " + uuid);

            LoggingServiceImpl.auditTrail = new AuditTrailRequest();
            LoggingServiceImpl.auditTrail.setUuid(uuid);
            LoggingServiceImpl.auditTrail.setOperation(request.getMethod());
            LoggingServiceImpl.auditTrail.setCreatedAt(new Date());
            LoggingServiceImpl.auditTrail.setIndex("auditget");

            MDC.put("requestID", uuid);

            loggingService.logRequest(request, null, LoggingServiceImpl.auditTrail);
        }
        
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("RequestID-3: " + MDC.get("requestID"));

    }



}
