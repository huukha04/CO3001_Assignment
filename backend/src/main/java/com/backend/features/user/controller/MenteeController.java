/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.backend.features.user.controller;

/**
 *
 * @author khahu
 */
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.features.user.dto.req.*;

import com.backend.features.user.service.MenteeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/mentee")
@RequiredArgsConstructor
public class MenteeController {

    private final MenteeService menteeService;

    @PostMapping("/section/register")
    public String registerSection(@RequestBody RegisterSectionRequest request) {
        return menteeService.registerSection(request);
    }

    @PostMapping("/section/cancel")
    public String cancelRegistration(@RequestBody RegisterSectionRequest request) {
        return menteeService.cancelRegistration(request);
    }

    @PostMapping("/feedback/create")
    public String createFeedback(@RequestBody FeedbackRequest request) {
        return menteeService.createFeedback(request);
    }

    @GetMapping("/slots/{tutorId}")
    public Object getAvailableSlots(@PathVariable String tutorId) {
        return menteeService.getAvailableSlots(tutorId);
    }

    @PostMapping("/slot/book")
    public String bookSlot(@RequestBody BookSlotRequest request) {
        return menteeService.bookSlot(request);
    }

    @PostMapping("/slot/cancel")
    public String cancelSlot(@RequestBody BookSlotRequest request) {
        return menteeService.cancelSlot(request);
    }
}