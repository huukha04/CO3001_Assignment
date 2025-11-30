/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.backend.features.user.service.impl;

/**
 *
 * @author khahu
 */
import org.springframework.stereotype.Service;


import com.backend.features.user.dto.res.*;
import com.backend.features.user.dto.req.*;
import com.backend.features.user.service.TutorService;

import lombok.RequiredArgsConstructor;

import com.backend.features.course.entity.CourseSection;
import com.backend.features.course.repository.CourseSectionRepository;
import com.backend.features.feedback.entity.SessionReport;
import com.backend.features.feedback.repository.SessionReportRepository;
import com.backend.features.schedule.entity.AvailableSlot;
import com.backend.features.schedule.repository.AvailableSlotRepository;


import java.util.List;


@Service
@RequiredArgsConstructor
public class TutorServiceImpl implements TutorService {

    private final CourseSectionRepository courseSectionRepository;  
    private final SessionReportRepository sessionReportRepository;
    private final AvailableSlotRepository availableSlotRepository;

    @Override
    public boolean createSection(CreateSectionRequest request) {
        try {
            CourseSection section = new CourseSection();
            section.setCourseName(request.getCourseName());
            section.setDayOfWeek(request.getDayOfWeek());
            section.setStartTime(request.getStartTime());
            section.setEndTime(request.getEndTime());
            section.setCapacity(request.getCapacity());
            section.setCurrentEnrollment(0);

            // Lưu vào MongoDB
            courseSectionRepository.save(section);

            return true; // thành công
        } catch (Exception e) {
            throw new RuntimeException("Failed to create course section", e);
        }
    }


    @Override
    public boolean createReport(CreateReportRequest request) {
        if (request.getSectionId() == null || request.getSectionId().isEmpty()) {
            throw new IllegalArgumentException("Section Id cannot be empty");
        }
        if (request.getContent() == null || request.getContent().isEmpty()) {
            throw new IllegalArgumentException("Report content cannot be empty");
        }

        try {
            SessionReport report = new SessionReport();
            report.setSectionId(request.getSectionId());
            report.setContent(request.getContent());

            sessionReportRepository.save(report);

            return true;
        } catch (Exception e) {
            throw new RuntimeException("Failed to create session report", e);
        }
    }

    @Override
    public boolean createSlot(CreateSlotRequest request) {
        // Validate dữ liệu
        if (request.getStart() == null || request.getEnd() == null) {
            throw new IllegalArgumentException("Start and End dates cannot be null");
        }
        if (request.getStart().after(request.getEnd())) {
            throw new IllegalArgumentException("Start date must be before End date");
        }
        if (request.getLocation() == null || request.getLocation().isEmpty()) {
            throw new IllegalArgumentException("Location cannot be empty");
        }

        try {
            // Tạo entity mới từ DTO
            AvailableSlot slot = new AvailableSlot();
            slot.setStartTime(request.getStart());
            slot.setEndTime(request.getEnd());
            slot.setLocation(request.getLocation());
            slot.setStatus("AVAILABLE"); 

            // Lưu vào MongoDB
            availableSlotRepository.save(slot);

            return true; // thành công
        } catch (Exception e) {
            throw new RuntimeException("Failed to create available slot", e);
        }
    }

    @Override
    public List<AvailableSlotResponse> getAvailableSlots() {
        return availableSlotRepository.findAll()
                .stream()
                .filter(slot -> "AVAILABLE".equalsIgnoreCase(slot.getStatus()))
                .map(slot -> {
                    AvailableSlotResponse res = new AvailableSlotResponse();
                    res.setSlotId(slot.getSlotId());
                    res.setStart(slot.getStartTime());
                    res.setEnd(slot.getEndTime());
                    res.setLocation(slot.getLocation());
                    res.setStatus(slot.getStatus());
                    return res;
                })
                .toList();
    }

    @Override
    public List<AvailableSlotResponse> getBookedMeetings() {
        return availableSlotRepository.findAll()
                .stream()
                .filter(slot -> "BOOKED".equalsIgnoreCase(slot.getStatus()))
                .map(slot -> {
                    AvailableSlotResponse res = new AvailableSlotResponse();
                    res.setSlotId(slot.getSlotId());
                    res.setStart(slot.getStartTime());
                    res.setEnd(slot.getEndTime());
                    res.setLocation(slot.getLocation());
                    res.setStatus(slot.getStatus());
                    return res;
                })
                .toList();
    }

}