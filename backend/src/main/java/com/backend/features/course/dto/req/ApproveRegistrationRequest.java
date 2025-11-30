package com.backend.features.course.dto.req;

import lombok.Data;
import java.util.Date;

@Data
public class ApproveRegistrationRequest {
    private String sectionId;
    private String menteeId;
    private Date registerDate;
}
