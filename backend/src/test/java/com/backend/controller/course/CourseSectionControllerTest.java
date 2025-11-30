package com.backend.controller.course;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.backend.common.exception.GlobalExceptionHandler;
import com.backend.features.course.controller.CourseSectionController;
import com.backend.features.course.dto.req.CancelSectionRequest;
import com.backend.features.course.dto.req.UpdateCapacityRequest;
import com.backend.features.course.dto.req.UpdateCourseNameRequest;
import com.backend.features.course.service.CourseSectionService;
import com.fasterxml.jackson.databind.ObjectMapper;

@DisplayName("CourseSectionController Tests")
class CourseSectionControllerTest {

    @Mock
    private CourseSectionService courseSectionService;

    @InjectMocks
    private CourseSectionController courseSectionController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(courseSectionController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("Should cancel section successfully")
    void testCancelSection_Success() throws Exception {
        // Arrange
        CancelSectionRequest request = new CancelSectionRequest();
        request.setSectionId("section-001");

        when(courseSectionService.cancelSection(any(CancelSectionRequest.class)))
                .thenReturn("Section cancelled successfully");

        // Act & Assert
        mockMvc.perform(post("/api/course/section/cancel")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("Section cancelled successfully"));

        verify(courseSectionService, times(1)).cancelSection(any(CancelSectionRequest.class));
    }

    @Test
    @DisplayName("Should return error when section not found for cancel")
    void testCancelSection_NotFound() throws Exception {
        // Arrange
        CancelSectionRequest request = new CancelSectionRequest();
        request.setSectionId("section-999");

        when(courseSectionService.cancelSection(any(CancelSectionRequest.class)))
                .thenThrow(new RuntimeException("Section not found"));

        // Act & Assert
        mockMvc.perform(post("/api/course/section/cancel")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().is5xxServerError());

        verify(courseSectionService, times(1)).cancelSection(any(CancelSectionRequest.class));
    }

    @Test
    @DisplayName("Should update capacity successfully")
    void testUpdateCapacity_Success() throws Exception {
        // Arrange
        UpdateCapacityRequest request = new UpdateCapacityRequest();
        request.setSectionId("section-001");

        when(courseSectionService.updateCapacity(any(UpdateCapacityRequest.class)))
                .thenReturn("Capacity updated successfully");

        // Act & Assert
        mockMvc.perform(post("/api/course/section/update-capacity")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("Capacity updated successfully"));

        verify(courseSectionService, times(1)).updateCapacity(any(UpdateCapacityRequest.class));
    }

    @Test
    @DisplayName("Should update course name successfully")
    void testUpdateCourseName_Success() throws Exception {
        // Arrange
        UpdateCourseNameRequest request = new UpdateCourseNameRequest();
        request.setSectionId("section-001");

        when(courseSectionService.updateCourseName(any(UpdateCourseNameRequest.class)))
                .thenReturn("Course name updated successfully");

        // Act & Assert
        mockMvc.perform(post("/api/course/section/update-name")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("Course name updated successfully"));

        verify(courseSectionService, times(1)).updateCourseName(any(UpdateCourseNameRequest.class));
    }

    @Test
    @DisplayName("Should get feedback list successfully")
    void testGetFeedbackList_Success() throws Exception {
        // Arrange
        String sectionId = "section-001";
        Map<String, Object> feedback = new HashMap<>();
        feedback.put("feedbackId", "feedback-001");
        feedback.put("rating", 5);

        when(courseSectionService.getFeedbackList(sectionId))
                .thenReturn(Arrays.asList(feedback));

        // Act & Assert
        mockMvc.perform(get("/api/course/section/{sectionId}/feedbacks", sectionId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].feedbackId").value("feedback-001"));

        verify(courseSectionService, times(1)).getFeedbackList(sectionId);
    }

    @Test
    @DisplayName("Should return empty feedback list")
    void testGetFeedbackList_Empty() throws Exception {
        // Arrange
        String sectionId = "section-999";
        when(courseSectionService.getFeedbackList(sectionId))
                .thenReturn(Arrays.asList());

        // Act & Assert
        mockMvc.perform(get("/api/course/section/{sectionId}/feedbacks", sectionId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));

        verify(courseSectionService, times(1)).getFeedbackList(sectionId);
    }

    @Test
    @DisplayName("Should get report list successfully")
    void testGetReportList_Success() throws Exception {
        // Arrange
        String sectionId = "section-001";
        Map<String, Object> report = new HashMap<>();
        report.put("reportId", "report-001");

        when(courseSectionService.getReportList(sectionId))
                .thenReturn(Arrays.asList(report));

        // Act & Assert
        mockMvc.perform(get("/api/course/section/{sectionId}/reports", sectionId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());

        verify(courseSectionService, times(1)).getReportList(sectionId);
    }

    @Test
    @DisplayName("Should get registered mentees list successfully")
    void testGetRegisteredMenteesList_Success() throws Exception {
        // Arrange
        String sectionId = "section-001";
        Map<String, Object> mentee = new HashMap<>();
        mentee.put("userId", "user-001");
        mentee.put("name", "John Doe");

        when(courseSectionService.getRegisteredMenteesList(sectionId))
                .thenReturn(Arrays.asList(mentee));

        // Act & Assert
        mockMvc.perform(get("/api/course/section/{sectionId}/mentees", sectionId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].userId").value("user-001"));

        verify(courseSectionService, times(1)).getRegisteredMenteesList(sectionId);
    }

    @Test
    @DisplayName("Should return empty mentees list")
    void testGetRegisteredMenteesList_Empty() throws Exception {
        // Arrange
        String sectionId = "section-999";
        when(courseSectionService.getRegisteredMenteesList(sectionId))
                .thenReturn(Arrays.asList());

        // Act & Assert
        mockMvc.perform(get("/api/course/section/{sectionId}/mentees", sectionId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));

        verify(courseSectionService, times(1)).getRegisteredMenteesList(sectionId);
    }

    @Test
    @DisplayName("Should handle service exception in getFeedbackList")
    void testGetFeedbackList_ServiceException() throws Exception {
        // Arrange
        String sectionId = "section-001";
        when(courseSectionService.getFeedbackList(sectionId))
                .thenThrow(new RuntimeException("Service error"));

        // Act & Assert
        mockMvc.perform(get("/api/course/section/{sectionId}/feedbacks", sectionId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());

        verify(courseSectionService, times(1)).getFeedbackList(sectionId);
    }
}
