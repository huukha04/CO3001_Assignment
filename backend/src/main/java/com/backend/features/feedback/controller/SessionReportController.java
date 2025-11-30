package com.backend.features.feedback.controller;

import com.backend.features.feedback.dto.UpdateReportRequest;
import com.backend.features.feedback.dto.SessionReportResponse;
import com.backend.features.feedback.service.SessionReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/reports") 
@RequiredArgsConstructor
public class SessionReportController {

    private final SessionReportService reportService;

    @GetMapping("/section/{sectionId}")
    public ResponseEntity<List<SessionReportResponse>> getBySection(@PathVariable("sectionId") String sectionId) {
        return ResponseEntity.ok(reportService.getReportsBySection(sectionId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SessionReportResponse> updateReport(
            @PathVariable("id") String id,
            @RequestBody UpdateReportRequest request) { 
        return ResponseEntity.ok(reportService.updateReport(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReport(@PathVariable("id") String id) {
        reportService.deleteReport(id);
        return ResponseEntity.ok("Deleted report successfully");
    }
}