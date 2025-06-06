package org.example.eventsourcing.query.repository;

import org.example.eventsourcing.query.entity.ExamReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamReportRepository extends JpaRepository<ExamReport, Long> {}
