/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.backend.features.schedule.service;

/**
 *
 * @author khahu
 */
import com.backend.features.schedule.dto.req.UpdateLocationRequest;
import com.backend.features.schedule.dto.req.UpdateStatusRequest;
import com.backend.features.schedule.dto.req.UpdateTimeRequest;

public interface AvailableSlotService {

    String updateLocation(UpdateLocationRequest request);

    String updateTime(UpdateTimeRequest request);

    String updateStatus(UpdateStatusRequest request);

    String deleteSlot(String slotId);
}