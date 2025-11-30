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

import java.util.Date;

@Data
@NoArgsConstructor
@Document(collection = "sectionRegistrations")
public class SectionRegistration {
    @Id
    private String id;
    private Date registerDate;
    private boolean registerStatus;
    private boolean isApproved;
    private String menteeId;
    private String sectionId;

    public void approveRegistration() {
        this.isApproved = true;
        this.registerStatus = true;
    }

    public void denyRegistration() {
        this.isApproved = false;
        this.registerStatus = false;
    }
}
