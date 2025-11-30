/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.backend.features.schedule.entity;

/**
 *
 * @author khahu
 */
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Document(collection = "slotBookings")
@Data
@NoArgsConstructor
public class SlotBooking {
    @Id
    private String bookingId;
    private Date bookingDate;
    private String menteeId;
    private String tutorId;
    private String slotId;
}