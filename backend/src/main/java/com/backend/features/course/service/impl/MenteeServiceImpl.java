package com.backend.features.course.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.features.course.dto.req.BookSlotRequest;
import com.backend.features.course.dto.req.CancelSectionRequest;
import com.backend.features.course.dto.req.CancelSlotRequest;
import com.backend.features.course.dto.req.CreateFeedbackRequest;
import com.backend.features.course.dto.req.RegisterSectionRequest;
import com.backend.features.course.entity.CourseSection;
import com.backend.features.course.entity.SectionRegistration;
import com.backend.features.course.repository.CourseSectionRepository;
import com.backend.features.course.repository.SectionRegistrationRepository;
import com.backend.features.course.service.MenteeService;
import com.backend.features.user.entity.Tutor;
import com.backend.features.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service("courseMenteeService")
@RequiredArgsConstructor
public class MenteeServiceImpl implements MenteeService {

    private final CourseSectionRepository courseSectionRepository;
    private final SectionRegistrationRepository sectionRegistrationRepository;
    private final UserRepository userRepository;

    @Override
    public String registerSection(RegisterSectionRequest request) {
        Long sectionId = request.getSectionId();
        if (sectionId == null) {
            throw new IllegalArgumentException("Section ID cannot be null");
        }
        
        // Create section registration
        SectionRegistration registration = new SectionRegistration();
        registration.setSectionId(sectionId.toString());
        registration.setRegisterDate(new Date());
        registration.setRegisterStatus(false);
        registration.setApproved(false);
        
        sectionRegistrationRepository.save(registration);
        return "Registered section with ID: " + sectionId;
    }

    @Override
    public String cancelRegistration(CancelSectionRequest request) {
        String sectionId = request.getSectionId();
        if (sectionId == null) {
            throw new IllegalArgumentException("Section ID cannot be null");
        }
        
        // TODO: Implement cancellation logic
        return "Cancelled section with ID: " + sectionId;
    }

    @Override
    public String createFeedback(CreateFeedbackRequest request) {
        Long sectionId = request.getSectionId();
        int rating = request.getRating();
        
        if (sectionId == null) {
            throw new IllegalArgumentException("Section ID cannot be null");
        }
        
        // TODO: Save feedback to database
        return "Feedback created for section ID " + sectionId + 
               " with rating " + rating;
    }

    @Override
    public List<CourseSection> getCourseSectionList() {
        return courseSectionRepository.findAll();
    }

    @Override
    public Object getTutorList() {
        // Get all tutors from user repository
        List<Object> tutors = new ArrayList<>();
        userRepository.findAll().forEach(user -> {
            if (user instanceof Tutor) {
                tutors.add(user);
            }
        });
        return tutors;
    }

    @Override
    public Object getProfile() {
        // TODO: Get current mentee profile from authentication context
        return null;
    }

    @Override
    public String bookSlot(BookSlotRequest request) {
        Long slotId = request.getSlotId();
        if (slotId == null) {
            throw new IllegalArgumentException("Slot ID cannot be null");
        }
        
        // TODO: Implement slot booking logic
        return "Booked slot with ID: " + slotId;
    }

    @Override
    public String cancelSlot(CancelSlotRequest request) {
        Long slotId = request.getSlotId();
        if (slotId == null) {
            throw new IllegalArgumentException("Slot ID cannot be null");
        }
        
        // TODO: Implement slot cancellation logic
        return "Cancelled slot with ID: " + slotId;
    }
}
