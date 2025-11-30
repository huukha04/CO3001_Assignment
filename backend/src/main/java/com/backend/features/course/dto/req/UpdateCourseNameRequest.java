package com.backend.features.course.dto.req;

import lombok.Data;

@Data
public class UpdateCourseNameRequest {
    private String sectionId;
    private String courseName;
}
