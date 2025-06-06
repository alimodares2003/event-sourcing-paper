package org.example.eventsourcing.query.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "aggregated_stats")
public class AggregatedStatsEntity {

    @Id
    private UUID teacherId;

    private int totalExams;

    private double averageScore;

    private long totalEvaluationTimeMillis;

    // Constructors, Getters, Setters
    public AggregatedStatsEntity() {
    }

    public AggregatedStatsEntity(UUID teacherId, int totalExams, double averageScore, long totalEvaluationTimeMillis) {
        this.teacherId = teacherId;
        this.totalExams = totalExams;
        this.averageScore = averageScore;
        this.totalEvaluationTimeMillis = totalEvaluationTimeMillis;
    }

    public UUID getTeacherId() {
        return teacherId;
    }

    public int getTotalExams() {
        return totalExams;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public long getTotalEvaluationTimeMillis() {
        return totalEvaluationTimeMillis;
    }
}