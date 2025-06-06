package org.example.eventsourcing.query.service;

import org.example.eventsourcing.query.repository.ExamReportRepository;
import org.springframework.stereotype.Service;

@Service
public class ExamQueryService {

    private final ExamReportRepository examReportRepository;

    public ExamQueryService(ExamReportRepository examReportRepository) {
        this.examReportRepository = examReportRepository;
    }

    public long getExamsCount() {
        return examReportRepository.count();
    }
}
