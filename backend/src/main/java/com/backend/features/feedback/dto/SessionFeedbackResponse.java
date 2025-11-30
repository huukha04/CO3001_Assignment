package com.backend.features.feedback.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class SessionFeedbackResponse {
    private String feedbackId;
    private String sectionId;
    private int rating;
    private String comment; // Nội dung feedback
    private Date submitDate;
}