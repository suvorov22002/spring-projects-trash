package com.tutorialspoint.demo.interceptor;

import java.time.LocalDateTime;

public record AuditTrail(String id, Object o, LocalDateTime local, String user, String status) {
}
