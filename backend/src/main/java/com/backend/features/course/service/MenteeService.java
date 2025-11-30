package com.backend.features.course.service;

import com.backend.features.course.dto.req.*;
import com.backend.features.course.entity.CourseSection;

import java.util.List;

public interface MenteeService {
    String registerSection(RegisterSectionRequest request);
    String cancelRegistration(CancelSectionRequest request);
    String createFeedback(CreateFeedbackRequest request);
    List<CourseSection> getCourseSectionList();
    Object getTutorList();
    Object getProfile();
    String bookSlot(BookSlotRequest request);
    String cancelSlot(CancelSlotRequest request);
}
