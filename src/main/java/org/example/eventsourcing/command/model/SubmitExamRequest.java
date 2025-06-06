package org.example.eventsourcing.command.model;

import java.sql.Time;
import java.util.UUID;

public record SubmitExamRequest(long examId,
                                UUID studentId,
                                UUID teacherId,
                                int score,
                                Time evaluationTime) {
}
