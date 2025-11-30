package com.backend.controller.schedule;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.backend.common.exception.GlobalExceptionHandler;
import com.backend.features.schedule.controller.AvailableSlotController;
import com.backend.features.schedule.dto.req.UpdateLocationRequest;
import com.backend.features.schedule.dto.req.UpdateStatusRequest;
import com.backend.features.schedule.dto.req.UpdateTimeRequest;
import com.backend.features.schedule.service.AvailableSlotService;
import com.fasterxml.jackson.databind.ObjectMapper;

@DisplayName("AvailableSlotController Tests")
class AvailableSlotControllerTest {

    @Mock
    private AvailableSlotService slotService;

    @InjectMocks
    private AvailableSlotController slotController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(slotController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("Should update location successfully")
    void testUpdateLocation_Success() throws Exception {
        // Arrange
        UpdateLocationRequest request = new UpdateLocationRequest();

        when(slotService.updateLocation(any(UpdateLocationRequest.class)))
                .thenReturn("Location updated successfully");

        // Act & Assert
        mockMvc.perform(put("/api/schedule/available-slots/location")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("Location updated successfully"));

        verify(slotService, times(1)).updateLocation(any(UpdateLocationRequest.class));
    }

    @Test
    @DisplayName("Should handle error when updating location")
    void testUpdateLocation_Error() throws Exception {
        // Arrange
        UpdateLocationRequest request = new UpdateLocationRequest();

        when(slotService.updateLocation(any(UpdateLocationRequest.class)))
                .thenThrow(new RuntimeException("Update failed"));

        // Act & Assert
        mockMvc.perform(put("/api/schedule/available-slots/location")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().is5xxServerError());

        verify(slotService, times(1)).updateLocation(any(UpdateLocationRequest.class));
    }

    @Test
    @DisplayName("Should update time successfully")
    void testUpdateTime_Success() throws Exception {
        // Arrange
        UpdateTimeRequest request = new UpdateTimeRequest();

        when(slotService.updateTime(any(UpdateTimeRequest.class)))
                .thenReturn("Time updated successfully");

        // Act & Assert
        mockMvc.perform(put("/api/schedule/available-slots/time")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("Time updated successfully"));

        verify(slotService, times(1)).updateTime(any(UpdateTimeRequest.class));
    }

    @Test
    @DisplayName("Should handle error when updating time")
    void testUpdateTime_Error() throws Exception {
        // Arrange
        UpdateTimeRequest request = new UpdateTimeRequest();

        when(slotService.updateTime(any(UpdateTimeRequest.class)))
                .thenThrow(new RuntimeException("Update failed"));

        // Act & Assert
        mockMvc.perform(put("/api/schedule/available-slots/time")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().is5xxServerError());

        verify(slotService, times(1)).updateTime(any(UpdateTimeRequest.class));
    }

    @Test
    @DisplayName("Should update status successfully")
    void testUpdateStatus_Success() throws Exception {
        // Arrange
        UpdateStatusRequest request = new UpdateStatusRequest();

        when(slotService.updateStatus(any(UpdateStatusRequest.class)))
                .thenReturn("Status updated successfully");

        // Act & Assert
        mockMvc.perform(put("/api/schedule/available-slots/status")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("Status updated successfully"));

        verify(slotService, times(1)).updateStatus(any(UpdateStatusRequest.class));
    }

    @Test
    @DisplayName("Should handle error when updating status")
    void testUpdateStatus_Error() throws Exception {
        // Arrange
        UpdateStatusRequest request = new UpdateStatusRequest();

        when(slotService.updateStatus(any(UpdateStatusRequest.class)))
                .thenThrow(new RuntimeException("Update failed"));

        // Act & Assert
        mockMvc.perform(put("/api/schedule/available-slots/status")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().is5xxServerError());

        verify(slotService, times(1)).updateStatus(any(UpdateStatusRequest.class));
    }

    @Test
    @DisplayName("Should delete slot successfully")
    void testDeleteSlot_Success() throws Exception {
        // Arrange
        String slotId = "slot-001";

        when(slotService.deleteSlot(slotId))
                .thenReturn("Slot deleted successfully");

        // Act & Assert
        mockMvc.perform(delete("/api/schedule/available-slots/{slotId}", slotId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Slot deleted successfully"));

        verify(slotService, times(1)).deleteSlot(slotId);
    }

    @Test
    @DisplayName("Should return error when deleting non-existent slot")
    void testDeleteSlot_NotFound() throws Exception {
        // Arrange
        String slotId = "slot-999";

        when(slotService.deleteSlot(slotId))
                .thenThrow(new RuntimeException("Slot not found"));

        // Act & Assert
        mockMvc.perform(delete("/api/schedule/available-slots/{slotId}", slotId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());

        verify(slotService, times(1)).deleteSlot(slotId);
    }

    @Test
    @DisplayName("Should handle null slot ID in delete")
    void testDeleteSlot_NullId() throws Exception {
        // Act & Assert
        mockMvc.perform(delete("/api/schedule/available-slots/null")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
