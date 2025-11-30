/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.backend.features.schedule.repository;

/**
 *
 * @author khahu
 */
import com.backend.features.schedule.entity.SlotBooking;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SlotBookingRepository extends MongoRepository<SlotBooking, String> {
    // TODO
}
