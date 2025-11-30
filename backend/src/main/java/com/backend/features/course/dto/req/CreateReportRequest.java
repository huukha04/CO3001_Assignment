package com.backend.features.course.dto.req;

import lombok.Data;

@Data
public class CreateReportRequest {
    private Long sectionId;
    private String content;
}
