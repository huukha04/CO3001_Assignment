/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.backend.features.course.repository;

/**
 *
 * @author khahu
 */
import com.backend.features.course.entity.SectionRegistration;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface SectionRegistrationRepository extends MongoRepository<SectionRegistration, String> {
    Optional<SectionRegistration> findBySectionIdAndMenteeId(String sectionId, String menteeId);
}
