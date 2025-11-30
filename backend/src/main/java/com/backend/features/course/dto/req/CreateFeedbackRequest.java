package com.backend.features.course.dto.req;

import lombok.Data;

@Data
public class CreateFeedbackRequest {
    private Long sectionId;
    private int rating;
    private String comment;
}
