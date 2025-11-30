/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.backend.features.schedule.service.impl;

/**
 *
 * @author khahu
 */
import org.springframework.stereotype.Service;

import com.backend.features.schedule.dto.req.UpdateLocationRequest;
import com.backend.features.schedule.dto.req.UpdateStatusRequest;
import com.backend.features.schedule.dto.req.UpdateTimeRequest;
import com.backend.features.schedule.entity.AvailableSlot;
import com.backend.features.schedule.repository.AvailableSlotRepository;
import com.backend.features.schedule.service.AvailableSlotService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AvailableSlotServiceImpl implements AvailableSlotService {

    private final AvailableSlotRepository repository;

    @Override
    public String updateLocation(UpdateLocationRequest request) {
        if (request == null || request.getSlotId() == null) {
            throw new RuntimeException("Invalid request");
        }
        String slotId = request.getSlotId();
        @SuppressWarnings("null")
        AvailableSlot slot = repository.findById(slotId)
                .orElseThrow(() -> new RuntimeException("Slot not found"));

        slot.updateLocation(request.getLocation());
        repository.save(slot);

        return slotToString(slot);
    }

    @Override
    public String updateTime(UpdateTimeRequest request) {
        if (request == null || request.getSlotId() == null) {
            throw new RuntimeException("Invalid request");
        }
        String slotId = request.getSlotId();
        @SuppressWarnings("null")
        AvailableSlot slot = repository.findById(slotId)
                .orElseThrow(() -> new RuntimeException("Slot not found"));

        slot.updateTime(request.getStart(), request.getEnd());
        repository.save(slot);

        return slotToString(slot);
    }

    @Override
    public String updateStatus(UpdateStatusRequest request) {
        if (request == null || request.getSlotId() == null) {
            throw new RuntimeException("Invalid request");
        }
        String slotId = request.getSlotId();
        @SuppressWarnings("null")
        AvailableSlot slot = repository.findById(slotId)
                .orElseThrow(() -> new RuntimeException("Slot not found"));

        slot.updateStatus(request.getStatus());
        repository.save(slot);

        return slotToString(slot);
    }

    @Override
    public String deleteSlot(String slotId) {
        if (slotId != null) {
            repository.deleteById(slotId);
        }
        return "Deleted slot with id=" + slotId;
    }

    // Helper method: convert slot to String
    private String slotToString(AvailableSlot slot) {
        return "Slot[id=" + slot.getId() +
               ", location=" + slot.getLocation() +
               ", start=" + slot.getStart() +
               ", end=" + slot.getEnd() +
               ", status=" + slot.getStatus() + "]";
    }
}
