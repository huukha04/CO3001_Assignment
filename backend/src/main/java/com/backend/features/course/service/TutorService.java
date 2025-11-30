package com.backend.features.course.service;

import com.backend.features.course.dto.req.*;
import com.backend.features.course.entity.CourseSection;

import java.util.List;

public interface TutorService {
    String createSection(CreateSectionRequest request);
    List<CourseSection> getRegisteredSections();
    String createReport(CreateReportRequest request);
    Object getCourseSectionFeedbacks(Long sectionId);
    Object getAvailableSlotList();
    Object getProfile();
}
