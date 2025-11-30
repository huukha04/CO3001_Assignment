/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.backend.features.auth.service.impl;

/**
 *
 * @author khahu
 */
import org.springframework.stereotype.Service;

import com.backend.common.util.JwtUtil;
import com.backend.features.auth.dto.req.AuthRequest;
import com.backend.features.auth.dto.res.AuthResponse;
import com.backend.features.auth.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtUtil jwtUtil;

    @Override
    public AuthResponse login(AuthRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();

        try {
            // Kiểm tra username đúng dạng xxx.xxxx
            if (!username.matches("^[A-Za-z]+\\.[A-Za-z0-9]+$")) {
                throw new RuntimeException("Username must follow format xxx.xxxx");
            }

            // Kiểm tra mật khẩu đúng 6 ký tự
            if (password == null || password.length() != 6) {
                throw new RuntimeException("Password must have exactly 6 characters");
            }

            // Nếu hợp lệ thì return true hoặc tạo response
            return AuthResponse.builder()
                    .username(username)
                    .token(jwtUtil.generateToken(username))
                    .message("Validation success")
                    .build();

        } catch (RuntimeException e) {
            throw new RuntimeException("Login process failed", e);
        }
    }

}