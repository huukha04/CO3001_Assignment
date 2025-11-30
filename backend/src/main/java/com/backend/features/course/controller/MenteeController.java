package com.backend.features.course.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.features.course.dto.req.BookSlotRequest;
import com.backend.features.course.dto.req.CancelSectionRequest;
import com.backend.features.course.dto.req.CancelSlotRequest;
import com.backend.features.course.dto.req.CreateFeedbackRequest;
import com.backend.features.course.dto.req.RegisterSectionRequest;
import com.backend.features.course.entity.CourseSection;
import com.backend.features.course.service.MenteeService;

import lombok.RequiredArgsConstructor;

@RestController("courseMenteeController") // custom bean name
@RequestMapping("/api/course/mentee")
@RequiredArgsConstructor
public class MenteeController {

    private final MenteeService menteeService;

    @PostMapping("/register-section")
    public String registerSection(@RequestBody RegisterSectionRequest request) {
        return menteeService.registerSection(request);
    }

    @PostMapping("/cancel-section")
    public String cancelSection(@RequestBody CancelSectionRequest request) {
        return menteeService.cancelRegistration(request);
    }

    @PostMapping("/feedback")
    public String createFeedback(@RequestBody CreateFeedbackRequest request) {
        return menteeService.createFeedback(request);
    }

    @GetMapping("/sections")
    public List<CourseSection> getCourseSectionList() {
        return menteeService.getCourseSectionList();
    }

    @GetMapping("/tutors")
    public Object getTutorList() {
        return menteeService.getTutorList();
    }

    @GetMapping("/profile")
    public Object getProfile() {
        return menteeService.getProfile();
    }

    @PostMapping("/book-slot")
    public String bookSlot(@RequestBody BookSlotRequest request) {
        return menteeService.bookSlot(request);
    }

    @PostMapping("/cancel-slot")
    public String cancelSlot(@RequestBody CancelSlotRequest request) {
        return menteeService.cancelSlot(request);
    }
}
