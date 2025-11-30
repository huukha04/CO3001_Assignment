/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.backend.features.auth.dto.req;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author khahu
 */
@Data
@NoArgsConstructor
public class AuthRequest {
    private String username;
    private String password;
}
