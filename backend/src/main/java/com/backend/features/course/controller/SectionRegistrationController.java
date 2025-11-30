package com.backend.features.course.controller;

import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import com.backend.features.course.dto.req.*;
import com.backend.features.course.service.SectionRegistrationService;

@RestController
@RequestMapping("/api/course/registration")
@RequiredArgsConstructor
public class SectionRegistrationController {

    private final SectionRegistrationService registrationService;

    @PostMapping("/approve")
    public String approveRegistration(@RequestBody ApproveRegistrationRequest request) {
        return registrationService.approveRegistration(request);
    }

    @PostMapping("/deny")
    public String denyRegistration(@RequestBody DenyRegistrationRequest request) {
        return registrationService.denyRegistration(request);
    }
}
