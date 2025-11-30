package com.backend.features.feedback.controller;

import com.backend.features.feedback.dto.UpdateFeedbackRequest;
import com.backend.features.feedback.dto.SessionFeedbackResponse;
import com.backend.features.feedback.service.SessionFeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedbacks") 
@RequiredArgsConstructor
public class SessionFeedbackController {

    private final SessionFeedbackService feedbackService;

    @GetMapping("/section/{sectionId}")
    public ResponseEntity<List<SessionFeedbackResponse>> getBySection(@PathVariable("sectionId") String sectionId) {
        return ResponseEntity.ok(feedbackService.getFeedbacksBySection(sectionId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SessionFeedbackResponse> updateFeedback(
            @PathVariable("id") String id,
            @RequestBody UpdateFeedbackRequest request) { 
        return ResponseEntity.ok(feedbackService.updateFeedback(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFeedback(@PathVariable("id") String id) {
        feedbackService.deleteFeedback(id);
        return ResponseEntity.ok("Deleted feedback successfully");
    }
}