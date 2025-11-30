/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.backend.features.user.service;

/**
 *
 * @author khahu
 */
import com.backend.features.user.dto.req.*;

public interface TutorService {
    boolean createSection(CreateSectionRequest request);
    boolean createReport(CreateReportRequest request);
    boolean createSlot(CreateSlotRequest request);
    Object getAvailableSlots();
    Object getBookedMeetings();
}
