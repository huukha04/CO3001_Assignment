package com.backend.features.feedback.service;

import com.backend.features.feedback.dto.UpdateReportRequest;
import com.backend.features.feedback.dto.SessionReportResponse;
import com.backend.features.feedback.entity.SessionReport;
import com.backend.features.feedback.repository.SessionReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SessionReportService {

    private final SessionReportRepository reportRepository;

    public List<SessionReportResponse> getReportsBySection(String sectionId) {
        List<SessionReport> entities = reportRepository.findBySectionId(sectionId);
        return entities.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public SessionReportResponse updateReport(String reportId, UpdateReportRequest request) {
        SessionReport report = reportRepository.findById(reportId)
                .orElseThrow(() -> new RuntimeException("Report not found: " + reportId));

        report.setContent(request.getContent());

        SessionReport updatedReport = reportRepository.save(report);

        return mapToResponse(updatedReport);
    }

    public void deleteReport(String reportId) {
        if (!reportRepository.existsById(reportId)) {
            throw new RuntimeException("Cannot delete. Report not found: " + reportId);
        }
        reportRepository.deleteById(reportId);
    }

    private SessionReportResponse mapToResponse(SessionReport entity) {
        SessionReportResponse response = new SessionReportResponse();
        response.setReportId(entity.getReportId());
        response.setSectionId(entity.getSectionId());
        response.setContent(entity.getContent());
        response.setCreatedDate(entity.getCreatedDate());
        response.setTutorId(entity.getTutorId());
        return response;
    }
}