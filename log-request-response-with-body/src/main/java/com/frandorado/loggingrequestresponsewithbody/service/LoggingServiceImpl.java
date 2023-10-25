package com.frandorado.loggingrequestresponsewithbody.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.frandorado.loggingrequestresponsewithbody.bootstrap.RabbitMQMessageProducer;
import com.tefo.library.commonutils.basestructure.dto.AuditTrailRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
@Log
public class LoggingServiceImpl implements LoggingService {

    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    public static AuditTrailRequest auditTrail;
    @Override
    public void logRequest(HttpServletRequest httpServletRequest, Object body, AuditTrailRequest auditTrail) {

        auditTrail.setUri(httpServletRequest.getRequestURI());

        try {
            auditTrail.setObject(new ObjectMapper().writeValueAsString(body));
        } catch (JsonProcessingException e) {
            auditTrail.setObject("");
        }


        StringBuilder stringBuilder = new StringBuilder();
        Map<String, String> parameters = buildParametersMap(httpServletRequest);
        
        stringBuilder.append("REQUEST ");
        stringBuilder.append("method=[").append(httpServletRequest.getMethod()).append("] ");
        stringBuilder.append("path=[").append(httpServletRequest.getRequestURI()).append("] ");
        stringBuilder.append("headers=[").append(buildHeadersMap(httpServletRequest)).append("] ");
        
        if (!parameters.isEmpty()) {
            stringBuilder.append("parameters=[").append(parameters).append("] ");
        }
        
        if (body != null) {
            stringBuilder.append("body=[" + body + "]");
        }
        
        log.info(stringBuilder.toString());

        rabbitMQMessageProducer.publish(
                auditTrail,
                "internal.exchange",
                "internal.notification.routing-key"
        );

    }
    
    @Override
    public void logResponse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object body) {
        StringBuilder stringBuilder = new StringBuilder();
        
        stringBuilder.append("RESPONSE ");
        stringBuilder.append("method=[").append(httpServletRequest.getMethod()).append("] ");
        stringBuilder.append("path=[").append(httpServletRequest.getRequestURI()).append("] ");
        stringBuilder.append("responseHeaders=[").append(buildHeadersMap(httpServletResponse)).append("] ");
        stringBuilder.append("responseBody=[").append(body).append("] ");

        try {
            auditTrail.setObject(new ObjectMapper().writeValueAsString(body));
        } catch (JsonProcessingException e) {
            auditTrail.setObject("");
        }

        log.info(stringBuilder.toString());
        System.out.println("Audit in response: " + auditTrail.getUuid());
        System.out.println("Audit in response object: " + auditTrail.getObject());
        System.out.println("Audit in response status: " + httpServletResponse.getStatus());
        System.out.println("RequestID-4: " + MDC.get("requestID"));

        auditTrail.setCode(httpServletResponse.getStatus());
        rabbitMQMessageProducer.publish(
                auditTrail,
                "internal.exchange",
                "internal.notification.routing-key"
        );

    }
    
    private Map<String, String> buildParametersMap(HttpServletRequest httpServletRequest) {
        Map<String, String> resultMap = new HashMap<>();
        Enumeration<String> parameterNames = httpServletRequest.getParameterNames();
        
        while (parameterNames.hasMoreElements()) {
            String key = parameterNames.nextElement();
            String value = httpServletRequest.getParameter(key);
            resultMap.put(key, value);
        }
        
        return resultMap;
    }
    
    private Map<String, String> buildHeadersMap(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        
        return map;
    }
    
    private Map<String, String> buildHeadersMap(HttpServletResponse response) {
        Map<String, String> map = new HashMap<>();
        
        Collection<String> headerNames = response.getHeaderNames();
        for (String header : headerNames) {
            map.put(header, response.getHeader(header));
        }
        
        return map;
    }
}
