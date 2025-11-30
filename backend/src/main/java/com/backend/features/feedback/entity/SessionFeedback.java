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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "sessionFeedbacks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionFeedback {
    @Id
    private String feedbackId;
    private String sectionId;   // Thêm trường này để liên kết với CourseSection
    private int rating;
    private String comment;
    private Date submitDate;
}