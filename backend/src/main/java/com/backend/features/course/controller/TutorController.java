package com.backend.features.course.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.features.course.dto.req.CreateReportRequest;
import com.backend.features.course.dto.req.CreateSectionRequest;
import com.backend.features.course.entity.CourseSection;
import com.backend.features.course.service.TutorService;

import lombok.RequiredArgsConstructor;

@RestController("courseTutorController") // custom bean name
@RequestMapping("/api/sourse/tutor")
@RequiredArgsConstructor
public class TutorController {

    private final TutorService tutorService;

    @PostMapping("/create-section")
    public String createSection(@RequestBody CreateSectionRequest request) {
        return tutorService.createSection(request);
    }

    @GetMapping("/sections")
    public List<CourseSection> getRegisteredSections() {
        return tutorService.getRegisteredSections();
    }

    @PostMapping("/report")
    public String createReport(@RequestBody CreateReportRequest request) {
        return tutorService.createReport(request);
    }

    @GetMapping("/section-feedbacks/{sectionId}")
    public Object getCourseSectionFeedbacks(@PathVariable Long sectionId) {
        return tutorService.getCourseSectionFeedbacks(sectionId);
    }

    @GetMapping("/available-slots")
    public Object getAvailableSlotList() {
        return tutorService.getAvailableSlotList();
    }

    @GetMapping("/profile")
    public Object getProfile() {
        return tutorService.getProfile();
    }
}
