package com.backend.controller.auth;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.backend.common.exception.GlobalExceptionHandler;
import com.backend.features.auth.controller.AuthController;
import com.backend.features.auth.dto.req.AuthRequest;
import com.backend.features.auth.dto.res.AuthResponse;
import com.backend.features.auth.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;

@DisplayName("AuthController Tests")
class AuthControllerTest {

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("Should login successfully with valid credentials")
    void testLogin_Success() throws Exception {
        // Arrange
        AuthRequest request = new AuthRequest();
        request.setUsername("tutor");
        request.setPassword("password123");

        AuthResponse response = AuthResponse.builder()
                .username("tutor")
                .token("jwt-token-123")
                .message("Login successful")
                .build();

        when(authService.login(any(AuthRequest.class))).thenReturn(response);

        // Act & Assert
        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("tutor"))
                .andExpect(jsonPath("$.token").value("jwt-token-123"))
                .andExpect(jsonPath("$.message").value("Login successful"));

        verify(authService, times(1)).login(any(AuthRequest.class));
    }

    @Test
    @DisplayName("Should fail login with invalid credentials")
    void testLogin_InvalidCredentials() throws Exception {
        // Arrange
        AuthRequest request = new AuthRequest();
        request.setUsername("invalid");
        request.setPassword("wrong");

        AuthResponse response = AuthResponse.builder()
                .username("invalid")
                .token(null)
                .message("Invalid credentials")
                .build();

        when(authService.login(any(AuthRequest.class))).thenReturn(response);

        // Act & Assert
        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Invalid credentials"));

        verify(authService, times(1)).login(any(AuthRequest.class));
    }

    @Test
    @DisplayName("Should handle null username")
    void testLogin_NullUsername() throws Exception {
        // Arrange
        AuthRequest request = new AuthRequest();
        request.setUsername(null);
        request.setPassword("password123");

        // Act & Assert
        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        verify(authService, times(1)).login(any(AuthRequest.class));
    }

    @Test
    @DisplayName("Should handle empty request body")
    void testLogin_EmptyBody() throws Exception {
        // Act & Assert
        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should throw exception when service fails")
    void testLogin_ServiceException() throws Exception {
        // Arrange
        AuthRequest request = new AuthRequest();
        request.setUsername("user");
        request.setPassword("pass");

        when(authService.login(any(AuthRequest.class)))
                .thenThrow(new RuntimeException("Database connection failed"));

        // Act & Assert
        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().is5xxServerError());

        verify(authService, times(1)).login(any(AuthRequest.class));
    }
}
