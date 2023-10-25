package com.frandorado.loggingrequestresponsewithbody.service;


import com.tefo.library.commonutils.basestructure.dto.AuditTrailRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface LoggingService {
    
    void logRequest(HttpServletRequest httpServletRequest, Object body, AuditTrailRequest auditTrail);
    
    void logResponse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object body);
}
