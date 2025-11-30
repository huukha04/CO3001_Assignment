/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.backend.features.user.dto.res;

/**
 *
 * @author khahu
 */
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class AvailableSlotResponse {
    private String slotId;
    private Date start;
    private Date end;
    private String location;
    private String status; // "available" hoặc "booked"
}