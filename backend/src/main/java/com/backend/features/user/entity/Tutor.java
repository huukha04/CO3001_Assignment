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


@Document(collection = "tutors")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Tutor extends User {

    // Constructor custom để set userType
    public Tutor(String userId, String fullName, String birthDate, String phoneNumber, String email) {
        super(userId, fullName, birthDate, phoneNumber, email);
        this.setUserType("TUTOR");
    }
}
