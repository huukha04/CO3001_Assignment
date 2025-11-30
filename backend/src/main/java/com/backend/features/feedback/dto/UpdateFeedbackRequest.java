package com.backend.features.feedback.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateFeedbackRequest {
    private String comment;
}