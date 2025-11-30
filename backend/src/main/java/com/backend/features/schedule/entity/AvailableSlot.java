/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.backend.features.schedule.entity;

/**
 *
 * @author khahu
 */
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "availableSlots")
@Data
@NoArgsConstructor
public class AvailableSlot {
    @Id
    private String slotId;
    private Date startTime;
    private Date endTime;
    private String location;
    private String status; // "Free" or "Booked"

    public String getId() {
        return slotId;
    }

    public Date getStart() {
        return startTime;
    }

    public Date getEnd() {
        return endTime;
    }

    public void updateLocation(String newLocation) {
        this.location = newLocation;
    }

    public void updateTime(Date start, Date end) {
        this.startTime = start;
        this.endTime = end;
    }

    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }
}