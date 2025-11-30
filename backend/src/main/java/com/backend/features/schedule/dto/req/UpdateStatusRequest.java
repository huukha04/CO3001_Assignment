/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.backend.features.schedule.dto.req;

/**
 *
 * @author khahu
 */
import lombok.Data;

@Data
public class UpdateStatusRequest {
    private String slotId;
    private String status;
}
