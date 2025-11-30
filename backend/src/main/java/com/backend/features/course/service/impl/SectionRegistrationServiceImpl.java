package com.backend.features.course.service.impl;

import org.springframework.stereotype.Service;

import com.backend.features.course.dto.req.ApproveRegistrationRequest;
import com.backend.features.course.dto.req.DenyRegistrationRequest;
import com.backend.features.course.entity.SectionRegistration;
import com.backend.features.course.repository.SectionRegistrationRepository;
import com.backend.features.course.service.SectionRegistrationService;

import lombok.RequiredArgsConstructor;

@Service("courseSectionRegistrationService")
@RequiredArgsConstructor
public class SectionRegistrationServiceImpl implements SectionRegistrationService {

    private final SectionRegistrationRepository registrationRepo;

    @Override
    public String approveRegistration(ApproveRegistrationRequest request) {
        String sectionId = request.getSectionId();
        String menteeId = request.getMenteeId();
        if (sectionId == null || menteeId == null) {
            throw new IllegalArgumentException("Section Id and Mentee Id cannot be null");
        }
        SectionRegistration reg = registrationRepo.findBySectionIdAndMenteeId(sectionId, menteeId)
                .orElseThrow(() -> new RuntimeException("Registration not found"));
        reg.approveRegistration();
        registrationRepo.save(reg);
        return "Approved registration for mentee: " + menteeId;
    }

    @Override
    public String denyRegistration(DenyRegistrationRequest request) {
        String sectionId = request.getSectionId();
        String menteeId = request.getMenteeId();
        if (sectionId == null || menteeId == null) {
            throw new IllegalArgumentException("Section Id and Mentee Id cannot be null");
        }
        SectionRegistration reg = registrationRepo.findBySectionIdAndMenteeId(sectionId, menteeId)
                .orElseThrow(() -> new RuntimeException("Registration not found"));
        reg.denyRegistration();
        registrationRepo.save(reg);
        return "Denied registration for mentee: " + menteeId;
    }
}
