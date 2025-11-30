/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.backend.features.user.controller;

/**
 *
 * @author khahu
 */
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.features.user.service.AdminService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/feedback/{sectionId}")
    public Object viewSectionFeedback(@PathVariable String sectionId) {
        return adminService.viewSectionFeedback(sectionId);
    }

    @GetMapping("/attendants")
    public Object viewProgramAttendant() {
        return adminService.viewProgramAttendant();
    }

    @GetMapping("/attendants/count")
    public int countProgramAttendant() {
        return adminService.countProgramAttendant();
    }
}