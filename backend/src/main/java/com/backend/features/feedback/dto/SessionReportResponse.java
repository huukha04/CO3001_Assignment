package com.backend.features.feedback.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class SessionReportResponse {
    private String reportId;
    private String sectionId;
    private String content; // Nội dung report
    private Date createdDate;
    private String tutorId;
}