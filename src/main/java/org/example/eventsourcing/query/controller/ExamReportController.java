package org.example.eventsourcing.query.controller;

import org.example.eventsourcing.query.service.ExamQueryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reports/exams")
public class ExamReportController {
    private final ExamQueryService queryService;

    public ExamReportController(ExamQueryService queryService) {
        this.queryService = queryService;
    }

    @GetMapping
    public long getAllExamsCount() {
        return queryService.getExamsCount();
    }
}