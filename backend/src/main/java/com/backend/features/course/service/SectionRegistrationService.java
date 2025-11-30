package com.backend.features.course.service;

import com.backend.features.course.dto.req.*;

public interface SectionRegistrationService {

    String approveRegistration(ApproveRegistrationRequest request);

    String denyRegistration(DenyRegistrationRequest request);
}
