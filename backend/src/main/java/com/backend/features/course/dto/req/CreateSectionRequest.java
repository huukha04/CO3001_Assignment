package com.backend.features.course.dto.req;

import lombok.Data;

@Data
public class CreateSectionRequest {
    private String courseName;
    private String dayOfWeek;
    private String startTime;
    private String endTime;
    private int capacity;
}
