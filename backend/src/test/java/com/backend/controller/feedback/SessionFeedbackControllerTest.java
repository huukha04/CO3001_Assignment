package com.backend.controller.feedback;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.backend.common.exception.GlobalExceptionHandler;
import com.backend.features.feedback.controller.SessionFeedbackController;
import com.backend.features.feedback.dto.SessionFeedbackResponse;
import com.backend.features.feedback.dto.UpdateFeedbackRequest;
import com.backend.features.feedback.service.SessionFeedbackService;
import com.fasterxml.jackson.databind.ObjectMapper;

@DisplayName("SessionFeedbackController Tests")
class SessionFeedbackControllerTest {

    @Mock
    private SessionFeedbackService feedbackService;

    @InjectMocks
    private SessionFeedbackController feedbackController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private SessionFeedbackResponse testResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(feedbackController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
        objectMapper = new ObjectMapper();

        testResponse = new SessionFeedbackResponse();
        testResponse.setFeedbackId("feedback-001");
        testResponse.setSectionId("section-001");
        testResponse.setRating(5);
        testResponse.setComment("Excellent session!");
        testResponse.setSubmitDate(new Date());
    }

    @Test
    @DisplayName("Should get feedbacks by section ID successfully")
    void testGetBySection_Success() throws Exception {
        // Arrange
        String sectionId = "section-001";
        SessionFeedbackResponse response2 = new SessionFeedbackResponse();
        response2.setFeedbackId("feedback-002");
        response2.setSectionId(sectionId);
        response2.setRating(4);

        List<SessionFeedbackResponse> responses = Arrays.asList(testResponse, response2);
        when(feedbackService.getFeedbacksBySection(sectionId)).thenReturn(responses);

        // Act & Assert
        mockMvc.perform(get("/api/feedbacks/section/{sectionId}", sectionId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].feedbackId").value("feedback-001"))
                .andExpect(jsonPath("$[0].rating").value(5))
                .andExpect(jsonPath("$[1].feedbackId").value("feedback-002"));

        verify(feedbackService, times(1)).getFeedbacksBySection(sectionId);
    }

    @Test
    @DisplayName("Should return empty list when no feedbacks found")
    void testGetBySection_Empty() throws Exception {
        // Arrange
        String sectionId = "section-999";
        when(feedbackService.getFeedbacksBySection(sectionId)).thenReturn(Arrays.asList());

        // Act & Assert
        mockMvc.perform(get("/api/feedbacks/section/{sectionId}", sectionId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));

        verify(feedbackService, times(1)).getFeedbacksBySection(sectionId);
    }

    @Test
    @DisplayName("Should update feedback successfully")
    void testUpdateFeedback_Success() throws Exception {
        // Arrange
        String feedbackId = "feedback-001";
        UpdateFeedbackRequest request = new UpdateFeedbackRequest();
        request.setComment("Updated comment");

        SessionFeedbackResponse updatedResponse = new SessionFeedbackResponse();
        updatedResponse.setFeedbackId(feedbackId);
        updatedResponse.setSectionId("section-001");
        updatedResponse.setRating(5);
        updatedResponse.setComment("Updated comment");
        updatedResponse.setSubmitDate(new Date());

        when(feedbackService.updateFeedback(eq(feedbackId), any(UpdateFeedbackRequest.class)))
                .thenReturn(updatedResponse);

        // Act & Assert
        mockMvc.perform(put("/api/feedbacks/{id}", feedbackId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.feedbackId").value(feedbackId))
                .andExpect(jsonPath("$.comment").value("Updated comment"))
                .andExpect(jsonPath("$.rating").value(5));

        verify(feedbackService, times(1)).updateFeedback(eq(feedbackId), any(UpdateFeedbackRequest.class));
    }

    @Test
    @DisplayName("Should throw exception when updating non-existent feedback")
    void testUpdateFeedback_NotFound() throws Exception {
        // Arrange
        String feedbackId = "feedback-999";
        UpdateFeedbackRequest request = new UpdateFeedbackRequest();
        request.setComment("Updated comment");

        when(feedbackService.updateFeedback(eq(feedbackId), any(UpdateFeedbackRequest.class)))
                .thenThrow(new RuntimeException("Feedback not found: " + feedbackId));

        // Act & Assert
        mockMvc.perform(put("/api/feedbacks/{id}", feedbackId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().is5xxServerError());

        verify(feedbackService, times(1)).updateFeedback(eq(feedbackId), any(UpdateFeedbackRequest.class));
    }

    @Test
    @DisplayName("Should delete feedback successfully")
    void testDeleteFeedback_Success() throws Exception {
        // Arrange
        String feedbackId = "feedback-001";
        doNothing().when(feedbackService).deleteFeedback(feedbackId);

        // Act & Assert
        mockMvc.perform(delete("/api/feedbacks/{id}", feedbackId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Deleted feedback successfully"));

        verify(feedbackService, times(1)).deleteFeedback(feedbackId);
    }

    @Test
    @DisplayName("Should throw exception when deleting non-existent feedback")
    void testDeleteFeedback_NotFound() throws Exception {
        // Arrange
        String feedbackId = "feedback-999";
        doThrow(new RuntimeException("Cannot delete. Feedback not found: " + feedbackId))
                .when(feedbackService).deleteFeedback(feedbackId);

        // Act & Assert
        mockMvc.perform(delete("/api/feedbacks/{id}", feedbackId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());

        verify(feedbackService, times(1)).deleteFeedback(feedbackId);
    }

    @Test
    @DisplayName("Should handle service exception in getBySection")
    void testGetBySection_ServiceException() throws Exception {
        // Arrange
        String sectionId = "section-001";
        when(feedbackService.getFeedbacksBySection(sectionId))
                .thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        mockMvc.perform(get("/api/feedbacks/section/{sectionId}", sectionId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());

        verify(feedbackService, times(1)).getFeedbacksBySection(sectionId);
    }

    @Test
    @DisplayName("Should handle null feedback comment")
    void testUpdateFeedback_NullComment() throws Exception {
        // Arrange
        String feedbackId = "feedback-001";
        UpdateFeedbackRequest request = new UpdateFeedbackRequest();
        request.setComment(null);

        SessionFeedbackResponse updatedResponse = new SessionFeedbackResponse();
        updatedResponse.setFeedbackId(feedbackId);
        updatedResponse.setComment(null);

        when(feedbackService.updateFeedback(eq(feedbackId), any(UpdateFeedbackRequest.class)))
                .thenReturn(updatedResponse);

        // Act & Assert
        mockMvc.perform(put("/api/feedbacks/{id}", feedbackId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        verify(feedbackService, times(1)).updateFeedback(eq(feedbackId), any(UpdateFeedbackRequest.class));
    }
}
