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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.features.user.dto.req.*;
import com.backend.features.user.service.TutorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/tutor")
@RequiredArgsConstructor
public class TutorController {

    private final TutorService tutorService;

    @PostMapping("/section/create")
    public boolean createSection(@RequestBody CreateSectionRequest request) {
        return tutorService.createSection(request);
    }

    @PostMapping("/report/create")
    public boolean createReport(@RequestBody CreateReportRequest request) {
        return tutorService.createReport(request);
    }

    @PostMapping("/slot/create")
    public boolean createSlot(@RequestBody CreateSlotRequest request) {
        return tutorService.createSlot(request);
    }

    @GetMapping("/slots")
    public Object getAvailableSlotList() {
        return tutorService.getAvailableSlots();
    }

    @GetMapping("/meetings")
    public Object getBookedMeetingList() {
        return tutorService.getBookedMeetings();
    }
}