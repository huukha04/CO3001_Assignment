package com.backend.features.course.service.impl;

import com.backend.features.course.dto.req.*;
import com.backend.features.course.entity.CourseSection;
import com.backend.features.course.repository.CourseSectionRepository;
import com.backend.features.course.service.TutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("courseTutorService")
@RequiredArgsConstructor
public class TutorServiceImpl implements TutorService {

    private final CourseSectionRepository courseSectionRepository;

    @Override
    public String createSection(CreateSectionRequest request) {
        if (request.getCourseName() == null) {
            throw new IllegalArgumentException("Course name cannot be null");
        }
        
        CourseSection section = new CourseSection();
        section.setCourseName(request.getCourseName());
        section.setDayOfWeek(request.getDayOfWeek());
        section.setStartTime(request.getStartTime());
        section.setEndTime(request.getEndTime());
        section.setCapacity(request.getCapacity());
        section.setCurrentEnrollment(0);
        section.setCancelled(false);
        
        courseSectionRepository.save(section);
        return "Created section: " + request.getCourseName();
    }

    @Override
    public List<CourseSection> getRegisteredSections() {
        return courseSectionRepository.findAll();
    }

    @Override
    public String createReport(CreateReportRequest request) {
        Long sectionId = request.getSectionId();
        if (sectionId == null) {
            throw new IllegalArgumentException("Section ID cannot be null");
        }
        
        // TODO: Save report to database
        return "Report created for section ID: " + sectionId;
    }

    @Override
    public Object getCourseSectionFeedbacks(Long sectionId) {
        if (sectionId == null) {
            throw new IllegalArgumentException("Section ID cannot be null");
        }
        
        // TODO: Retrieve feedbacks from database
        return null;
    }

    @Override
    public Object getAvailableSlotList() {
        // TODO: Retrieve available slots from database
        return null;
    }

    @Override
    public Object getProfile() {
        // TODO: Get current tutor profile from authentication context
        return null;
    }
}
