/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.backend.features.feedback.repository;

/**
 *
 * @author khahu
 */
import com.backend.features.feedback.entity.SessionReport;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface SessionReportRepository extends MongoRepository<SessionReport, String> {
    List<SessionReport> findBySectionId(String sectionId);
}