package org.example.eventsourcing.command.controller;

import org.example.eventsourcing.command.service.ExamService;
import org.example.eventsourcing.command.model.SubmitExamRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/commands/exams")
public class ExamCommandController {
    private final ExamService examService;

    public ExamCommandController(ExamService examService) {
        this.examService = examService;
    }

    @PostMapping("/submit")
    public ResponseEntity<Void> submitExam(@RequestBody SubmitExamRequest request) {
        examService.submitExam(request);
        return ResponseEntity.ok().build();
    }
}
