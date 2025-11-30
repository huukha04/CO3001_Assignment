/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.backend.features.course.entity;

/**
 *
 * @author khahu
 */
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection = "courseSections")
public class CourseSection {
    @Id
    private String sectionId;
    private String courseName;
    private String dayOfWeek;
    private String startTime;
    private String endTime;
    private int currentEnrollment;
    private int capacity;
    private boolean isCancelled;

    public void cancelSection() {
        this.isCancelled = true;
    }

    public void updateCapacity(int newCapacity) {
        this.capacity = newCapacity;
    }

    public void updateCourseName(String newCourseName) {
        this.courseName = newCourseName;
    }

    public Object getFeedbackList() {
        // TODO: Implement feedback retrieval logic
        return null;
    }

    public Object getReportList() {
        // TODO: Implement report retrieval logic
        return null;
    }

    public Object getRegisteredMenteesList() {
        // TODO: Implement registered mentees retrieval logic
        return null;
    }
}