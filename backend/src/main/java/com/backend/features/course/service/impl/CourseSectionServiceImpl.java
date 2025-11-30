package com.backend.features.course.service.impl;

import org.springframework.stereotype.Service;

import com.backend.features.course.dto.req.CancelSectionRequest;
import com.backend.features.course.dto.req.UpdateCapacityRequest;
import com.backend.features.course.dto.req.UpdateCourseNameRequest;
import com.backend.features.course.entity.CourseSection;
import com.backend.features.course.repository.CourseSectionRepository;
import com.backend.features.course.service.CourseSectionService;

import lombok.RequiredArgsConstructor;

@Service("courseCourseSectionService")
@RequiredArgsConstructor
public class CourseSectionServiceImpl implements CourseSectionService {

    private final CourseSectionRepository courseRepo;

    @Override
    public String cancelSection(CancelSectionRequest request) {
        String sectionId = request.getSectionId();
        if (sectionId == null) {
            throw new IllegalArgumentException("Section Id cannot be null");
        }
        CourseSection section = courseRepo.findById(sectionId)
                .orElseThrow(() -> new RuntimeException("Section not found"));
        section.cancelSection();
        courseRepo.save(section);
        return "Cancelled section: " + sectionId;
    }

    @Override
    public String updateCapacity(UpdateCapacityRequest request) {
        String sectionId = request.getSectionId();
        if (sectionId == null) {
            throw new IllegalArgumentException("Section Id cannot be null");
        }
        CourseSection section = courseRepo.findById(sectionId)
                .orElseThrow(() -> new RuntimeException("Section not found"));
        section.updateCapacity(request.getCapacity());
        courseRepo.save(section);
        return "Updated capacity for section: " + sectionId;
    }

    @Override
    public String updateCourseName(UpdateCourseNameRequest request) {
        String sectionId = request.getSectionId();
        if (sectionId == null) {
            throw new IllegalArgumentException("Section Id cannot be null");
        }
        CourseSection section = courseRepo.findById(sectionId)
                .orElseThrow(() -> new RuntimeException("Section not found"));
        section.updateCourseName(request.getCourseName());
        courseRepo.save(section);
        return "Updated course name for section: " + sectionId;
    }

    @Override
    public Object getFeedbackList(String sectionId) {
        if (sectionId == null) {
            throw new IllegalArgumentException("Section Id cannot be null");
        }
        CourseSection section = courseRepo.findById(sectionId)
                .orElseThrow(() -> new RuntimeException("Section not found"));
        return section.getFeedbackList();
    }

    @Override
    public Object getReportList(String sectionId) {
        if (sectionId == null) {
            throw new IllegalArgumentException("Section Id cannot be null");
        }
        CourseSection section = courseRepo.findById(sectionId)
                .orElseThrow(() -> new RuntimeException("Section not found"));
        return section.getReportList();
    }

    @Override
    public Object getRegisteredMenteesList(String sectionId) {
        if (sectionId == null) {
            throw new IllegalArgumentException("Section Id cannot be null");
        }
        CourseSection section = courseRepo.findById(sectionId)
                .orElseThrow(() -> new RuntimeException("Section not found"));
        return section.getRegisteredMenteesList();
    }
}
