package org.example.eventsourcing.query.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Time;

@Entity
@Table(name = "exam-report")
public class ExamReport {
    @Id
    @GeneratedValue
    private Long id;
    private long examId;
    private String studentId;
    private String teacherId;

    private int score;
    private Time evaluationTime;

    public ExamReport(long examId, String studentId, String teacherId, int score, Time evaluationTime) {
        this.examId = examId;
        this.studentId = studentId;
        this.teacherId = teacherId;
        this.score = score;
        this.evaluationTime = evaluationTime;
    }

    public ExamReport() {

    }

    public Long getId() {
        return id;
    }

    public long getExamId() {
        return examId;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public int getScore() {
        return score;
    }

    public Time getEvaluationTime() {
        return evaluationTime;
    }
}
