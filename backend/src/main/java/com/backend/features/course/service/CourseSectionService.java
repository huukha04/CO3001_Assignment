package com.backend.features.course.service;

import com.backend.features.course.dto.req.*;

public interface CourseSectionService {

    String cancelSection(CancelSectionRequest request);

    String updateCapacity(UpdateCapacityRequest request);

    String updateCourseName(UpdateCourseNameRequest request);

    Object getFeedbackList(String sectionId);

    Object getReportList(String sectionId);

    Object getRegisteredMenteesList(String sectionId);
}
