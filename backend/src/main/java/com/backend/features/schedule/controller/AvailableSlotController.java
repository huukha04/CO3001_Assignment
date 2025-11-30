/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.backend.features.schedule.controller;

/**
 *
 * @author khahu
 */
import org.springframework.web.bind.annotation.*;
import com.backend.features.schedule.dto.req.*;
import com.backend.features.schedule.service.AvailableSlotService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/schedule/available-slots")
@RequiredArgsConstructor
public class AvailableSlotController {

    private final AvailableSlotService service; 

    @PutMapping("/location")
    public String updateLocation(@RequestBody UpdateLocationRequest request) {
        return service.updateLocation(request);
    }

    @PutMapping("/time")
    public String updateTime(@RequestBody UpdateTimeRequest request) {
        return service.updateTime(request);
    }

    @PutMapping("/status")
    public String updateStatus(@RequestBody UpdateStatusRequest request) {
        return service.updateStatus(request);
    }

    @DeleteMapping("/{slotId}")
    public String deleteSlot(@PathVariable String slotId) {
        return service.deleteSlot(slotId);
    }
}
