/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.backend.features.feedback.repository;

/**
 *
 * @author khahu
 */
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.backend.features.feedback.entity.SessionFeedback;

public interface SessionFeedbackRepository extends MongoRepository<SessionFeedback, String> {
    List<SessionFeedback> findBySectionId(String sectionId);
}
