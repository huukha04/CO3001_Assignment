package com.backend.features.feedback.service;

import com.backend.features.feedback.dto.UpdateFeedbackRequest;
import com.backend.features.feedback.dto.SessionFeedbackResponse;
import com.backend.features.feedback.entity.SessionFeedback;
import com.backend.features.feedback.repository.SessionFeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SessionFeedbackService {

    private final SessionFeedbackRepository feedbackRepository;

    public List<SessionFeedbackResponse> getFeedbacksBySection(String sectionId) {
        List<SessionFeedback> entities = feedbackRepository.findBySectionId(sectionId);

        return entities.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public SessionFeedbackResponse updateFeedback(String feedbackId, UpdateFeedbackRequest request) {
        SessionFeedback feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new RuntimeException("Feedback not found: " + feedbackId));

        feedback.setComment(request.getComment());
        
        SessionFeedback updatedFeedback = feedbackRepository.save(feedback);
        
        return mapToResponse(updatedFeedback);
    }

    public void deleteFeedback(String feedbackId) {
        if (!feedbackRepository.existsById(feedbackId)) {
            throw new RuntimeException("Cannot delete. Feedback not found: " + feedbackId);
        }
        feedbackRepository.deleteById(feedbackId);
    }

    private SessionFeedbackResponse mapToResponse(SessionFeedback entity) {
        SessionFeedbackResponse response = new SessionFeedbackResponse();
        response.setFeedbackId(entity.getFeedbackId());
        response.setSectionId(entity.getSectionId());
        response.setRating(entity.getRating());
        response.setComment(entity.getComment());
        response.setSubmitDate(entity.getSubmitDate());
        return response;
    }
}