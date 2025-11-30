/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.backend.features.feedback.entity;

/**
 *
 * @author khahu
 */
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "sessionReports")
@Data
@NoArgsConstructor
public class SessionReport {
    @Id
    private String reportId;
    private String content;
    private Date createdDate;
    private String tutorId;
    private String sectionId;
}