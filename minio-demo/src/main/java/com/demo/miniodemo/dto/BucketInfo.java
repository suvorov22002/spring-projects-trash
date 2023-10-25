package com.demo.miniodemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
public class BucketInfo {
    private String name;
    private ZonedDateTime creationDate;

}
