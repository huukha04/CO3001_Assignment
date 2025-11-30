package com.backend.features.course.controller;

import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import com.backend.features.course.dto.req.*;
import com.backend.features.course.service.CourseSectionService;

@RestController
@RequestMapping("/api/course/section")
@RequiredArgsConstructor
public class CourseSectionController {

    private final CourseSectionService courseSectionService;

    @PostMapping("/cancel")
    public String cancelSection(@RequestBody CancelSectionRequest request) {
        return courseSectionService.cancelSection(request);
    }

    @PostMapping("/update-capacity")
    public String updateCapacity(@RequestBody UpdateCapacityRequest request) {
        return courseSectionService.updateCapacity(request);
    }

    @PostMapping("/update-name")
    public String updateCourseName(@RequestBody UpdateCourseNameRequest request) {
        return courseSectionService.updateCourseName(request);
    }

    @GetMapping("/{sectionId}/feedbacks")
    public Object getFeedbackList(@PathVariable String sectionId) {
        return courseSectionService.getFeedbackList(sectionId);
    }

    @GetMapping("/{sectionId}/reports")
    public Object getReportList(@PathVariable String sectionId) {
        return courseSectionService.getReportList(sectionId);
    }

    @GetMapping("/{sectionId}/mentees")
    public Object getRegisteredMenteesList(@PathVariable String sectionId) {
        return courseSectionService.getRegisteredMenteesList(sectionId);
    }
}
