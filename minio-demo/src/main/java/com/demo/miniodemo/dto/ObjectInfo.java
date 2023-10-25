package com.demo.miniodemo.dto;

import lombok.Data;

@Data
public class ObjectInfo {
    private String fileName;
    private long size;
    private String contentType;
    private String lastModified;
    private String url;
}
