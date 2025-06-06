package org.example.eventsourcing.command.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.eventsourcing.command.event.ExamCreatedEvent;
import org.example.eventsourcing.command.model.SubmitExamRequest;
import org.example.eventsourcing.command.repository.EventStoreRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ExamService {
    private final KafkaTemplate<String, Object> kafka;
    private final EventStoreRepository store;

    public ExamService(KafkaTemplate<String, Object> kafka, EventStoreRepository store) {
        this.kafka = kafka;
        this.store = store;
    }

    public void submitExam(SubmitExamRequest request) {
        //Create Exam Event
        ExamCreatedEvent event = new ExamCreatedEvent(request);
        //Save Event to Event Store
        try {
            store.save(event.toEntity());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        //Publish Event to Kafka
        kafka.send("exam-events", event);
    }
}
