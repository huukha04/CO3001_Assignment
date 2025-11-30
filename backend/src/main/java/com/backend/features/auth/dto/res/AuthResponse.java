/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.backend.features.auth.dto.res;

/**
 *
 * @author khahu
 */
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class AuthResponse {
    private String username;
    private String token;
    private String message;

    public AuthResponse(String username, String token, String message) {
        this.username = username;
        this.token = token;
        this.message = message;
    }
}
