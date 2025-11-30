/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.backend.features.user.dto.req;

/**
 *
 * @author khahu
 */
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class CreateSectionRequest {
    private String courseName;
    private String dayOfWeek;
    private String startTime; 
    private String endTime;  
    private int capacity;
}