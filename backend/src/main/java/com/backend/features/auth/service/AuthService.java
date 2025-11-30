/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.backend.features.auth.service;

/**
 *
 * @author khahu
 */

import com.backend.features.auth.dto.req.AuthRequest;
import com.backend.features.auth.dto.res.AuthResponse;

public interface AuthService {
    AuthResponse login(AuthRequest request);
}
