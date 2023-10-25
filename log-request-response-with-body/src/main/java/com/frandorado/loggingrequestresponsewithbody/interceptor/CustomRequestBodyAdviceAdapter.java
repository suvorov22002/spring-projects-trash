package com.frandorado.loggingrequestresponsewithbody.interceptor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.frandorado.loggingrequestresponsewithbody.bootstrap.Utils;
import com.frandorado.loggingrequestresponsewithbody.service.LoggingService;
import com.frandorado.loggingrequestresponsewithbody.service.LoggingServiceImpl;
import com.tefo.library.commonutils.basestructure.dto.AuditTrailRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.lang.reflect.Type;
import java.util.Date;

@ControllerAdvice
public class CustomRequestBodyAdviceAdapter extends RequestBodyAdviceAdapter {
    
    @Autowired
    LoggingService loggingService;
    
    @Autowired
    HttpServletRequest httpServletRequest;

    
    @Override
    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }
    
    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
            Class<? extends HttpMessageConverter<?>> converterType) {

        String uuid = Utils.getRandomString.apply(null);
        System.out.println("RequestID-2: " + uuid);

        LoggingServiceImpl.auditTrail = new AuditTrailRequest();
        LoggingServiceImpl.auditTrail.setUuid(uuid);
        LoggingServiceImpl.auditTrail.setOperation(httpServletRequest.getMethod());
        LoggingServiceImpl.auditTrail.setCreatedAt(new Date());
        LoggingServiceImpl.auditTrail.setIndex(body.getClass().getSimpleName());

        MDC.put("requestID", uuid);
        loggingService.logRequest(httpServletRequest, body, LoggingServiceImpl.auditTrail);

        return super.afterBodyRead(body, inputMessage, parameter, targetType, converterType);
    }
}
