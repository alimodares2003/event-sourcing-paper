package org.example.eventsourcing.command.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.example.eventsourcing.command.entity.StoredEvent;
import org.example.eventsourcing.command.model.SubmitExamRequest;

import java.time.LocalDateTime;

public record ExamCreatedEvent(SubmitExamRequest state) {
    public StoredEvent toEntity() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return new StoredEvent(
                this.getClass().getSimpleName(),
                ow.writeValueAsString(this),
                LocalDateTime.now()
        );
    }
}