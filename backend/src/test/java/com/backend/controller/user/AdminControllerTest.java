package com.backend.controller.user;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.backend.common.exception.GlobalExceptionHandler;
import com.backend.features.user.controller.AdminController;
import com.backend.features.user.service.AdminService;

@DisplayName("AdminController Tests")
class AdminControllerTest {

    @Mock
    private AdminService adminService;

    @InjectMocks
    private AdminController adminController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(adminController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    @DisplayName("Should view section feedback successfully")
    void testViewSectionFeedback_Success() throws Exception {
        // Arrange
        String sectionId = "section-001";
        Map<String, Object> feedbackData = new HashMap<>();
        feedbackData.put("feedbackId", "feedback-001");
        feedbackData.put("rating", 5);
        feedbackData.put("comment", "Great session!");

        when(adminService.viewSectionFeedback(sectionId)).thenReturn(Arrays.asList(feedbackData));

        // Act & Assert
        mockMvc.perform(get("/api/admin/feedback/{sectionId}", sectionId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].feedbackId").value("feedback-001"))
                .andExpect(jsonPath("$[0].rating").value(5));

        verify(adminService, times(1)).viewSectionFeedback(sectionId);
    }

    @Test
    @DisplayName("Should return empty list when no feedback exists")
    void testViewSectionFeedback_Empty() throws Exception {
        // Arrange
        String sectionId = "section-999";
        when(adminService.viewSectionFeedback(sectionId)).thenReturn(Arrays.asList());

        // Act & Assert
        mockMvc.perform(get("/api/admin/feedback/{sectionId}", sectionId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));

        verify(adminService, times(1)).viewSectionFeedback(sectionId);
    }

    @Test
    @DisplayName("Should view program attendants successfully")
    void testViewProgramAttendant_Success() throws Exception {
        // Arrange
        Map<String, Object> attendantData = new HashMap<>();
        attendantData.put("userId", "user-001");
        attendantData.put("name", "John Doe");
        attendantData.put("status", "active");

        when(adminService.viewProgramAttendant()).thenReturn(Arrays.asList(attendantData));

        // Act & Assert
        mockMvc.perform(get("/api/admin/attendants")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].userId").value("user-001"))
                .andExpect(jsonPath("$[0].name").value("John Doe"));

        verify(adminService, times(1)).viewProgramAttendant();
    }

    @Test
    @DisplayName("Should return empty list when no attendants")
    void testViewProgramAttendant_Empty() throws Exception {
        // Arrange
        when(adminService.viewProgramAttendant()).thenReturn(Arrays.asList());

        // Act & Assert
        mockMvc.perform(get("/api/admin/attendants")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));

        verify(adminService, times(1)).viewProgramAttendant();
    }

    @Test
    @DisplayName("Should count program attendants successfully")
    void testCountProgramAttendant_Success() throws Exception {
        // Arrange
        int expectedCount = 25;
        when(adminService.countProgramAttendant()).thenReturn(expectedCount);

        // Act & Assert
        mockMvc.perform(get("/api/admin/attendants/count")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("25"));

        verify(adminService, times(1)).countProgramAttendant();
    }

    @Test
    @DisplayName("Should return zero when no attendants exist")
    void testCountProgramAttendant_Zero() throws Exception {
        // Arrange
        when(adminService.countProgramAttendant()).thenReturn(0);

        // Act & Assert
        mockMvc.perform(get("/api/admin/attendants/count")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("0"));

        verify(adminService, times(1)).countProgramAttendant();
    }

    @Test
    @DisplayName("Should handle service exception in viewSectionFeedback")
    void testViewSectionFeedback_ServiceException() throws Exception {
        // Arrange
        String sectionId = "section-001";
        when(adminService.viewSectionFeedback(sectionId))
                .thenThrow(new RuntimeException("Service error"));

        // Act & Assert
        mockMvc.perform(get("/api/admin/feedback/{sectionId}", sectionId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());

        verify(adminService, times(1)).viewSectionFeedback(sectionId);
    }

    @Test
    @DisplayName("Should handle service exception in countProgramAttendant")
    void testCountProgramAttendant_ServiceException() throws Exception {
        // Arrange
        when(adminService.countProgramAttendant())
                .thenThrow(new RuntimeException("Service error"));

        // Act & Assert
        mockMvc.perform(get("/api/admin/attendants/count")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());

        verify(adminService, times(1)).countProgramAttendant();
    }
}
