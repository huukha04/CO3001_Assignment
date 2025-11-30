/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.backend.features.user.service;

/**
 *
 * @author khahu
 */
import com.backend.features.user.dto.req.*;

public interface MenteeService {
    String registerSection(RegisterSectionRequest request);
    String cancelRegistration(RegisterSectionRequest request);
    String createFeedback(FeedbackRequest request);
    Object getAvailableSlots(String tutorId);
    String bookSlot(BookSlotRequest request);
    String cancelSlot(BookSlotRequest request);
}
