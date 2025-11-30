package com.backend.features.course.dto.req;

import lombok.Data;

@Data
public class UpdateCapacityRequest {
    private String sectionId;
    private int capacity;
}
