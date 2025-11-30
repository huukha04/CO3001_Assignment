/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.backend.features.user.service.impl;

/**
 *
 * @author khahu
 */


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.backend.features.feedback.entity.SessionFeedback;
import com.backend.features.feedback.repository.SessionFeedbackRepository;
import com.backend.features.user.dto.res.FeedbackResponse;
import com.backend.features.user.service.AdminService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    
    private final SessionFeedbackRepository feedbackRepository;

    @Override
    public List<FeedbackResponse> viewSectionFeedback(String sectionId) {
        List<SessionFeedback> feedbacks = feedbackRepository.findBySectionId(sectionId);

        return feedbacks.stream()
                .map(f -> {
                    FeedbackResponse res = new FeedbackResponse();
                    res.setFeedbackId(f.getFeedbackId());
                    res.setSectionId(f.getSectionId());
                    res.setRating(f.getRating());
                    res.setComment(f.getComment());
                    return res;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Object viewProgramAttendant() {
        // TODO
        return "Attendant list";
    }

    @Override
    public int countProgramAttendant() {
        // TODO
        return 42;
    }
}