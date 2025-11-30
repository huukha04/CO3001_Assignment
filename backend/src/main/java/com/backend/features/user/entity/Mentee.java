/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.backend.features.user.entity;

/**
 *
 * @author khahu
 */
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Document(collection = "mentees")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Mentee extends User {

    private String studentId;

    public Mentee(String userId, String fullName, String birthDate, String phoneNumber, String email, String studentId) {
        super(userId, fullName, birthDate, phoneNumber, email);
        this.setUserType("MENTEE");
        this.studentId = studentId;
    }
}


