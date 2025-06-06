package org.example.eventsourcing.query.handler;

import org.example.eventsourcing.command.event.ExamCreatedEvent;
import org.example.eventsourcing.command.model.SubmitExamRequest;
import org.example.eventsourcing.query.entity.AggregatedStatsEntity;
import org.example.eventsourcing.query.entity.ExamReport;
import org.example.eventsourcing.query.repository.AggregatedStatsRepository;
import org.example.eventsourcing.query.repository.ExamReportRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
@EnableAsync
public class ExamEventListener {
    private final ExamReportRepository reportRepo;
    private final AggregatedStatsRepository aggregatedStatsRepository;


    public ExamEventListener(ExamReportRepository reportRepo, AggregatedStatsRepository repository) {
        this.reportRepo = reportRepo;
        this.aggregatedStatsRepository = repository;
    }


    // Modality 1
    @KafkaListener(topics = "exam-events", groupId = "query-service")
    public void consumeMod1(ExamCreatedEvent event) {
        System.out.println("handleExamTaken");
        System.out.println(event);
        reportRepo.save(new ExamReport(event.state().examId(),
                event.state().studentId().toString(),
                event.state().teacherId().toString(),
                event.state().score(),
                event.state().evaluationTime()
        ));
    }


    // Modality 2
    private final ArrayList<ExamReport> buffer = new ArrayList<>();

    @KafkaListener(topics = "exam-events", groupId = "query-service")
    public void consumeMod2(ExamCreatedEvent event) {
        System.out.println("event buffered");
        buffer.add(new ExamReport(event.state().examId(),
                event.state().studentId().toString(),
                event.state().teacherId().toString(),
                event.state().score(),
                event.state().evaluationTime()
        ));
    }

    @Async
    @Scheduled(fixedRate = 30000)
    public void flushAndStore() {
        System.out.println("flushAndStore");
        reportRepo.saveAll(buffer);
        buffer.clear();
    }

    //Modality 3

    private final Map<UUID, List<SubmitExamRequest>> buffer3 = new ConcurrentHashMap<>();

    @KafkaListener(topics = "exam-events", groupId = "query-service")
    public void consumeMod3(ExamCreatedEvent event) {
        buffer3.computeIfAbsent(event.state().teacherId(), k -> new ArrayList<>()).add(event.state());
    }

    @Scheduled(fixedRate = 180_000) // Every 3 minutes
    public void aggregateAndStore() {
        System.out.println("aggregateAndStore");
        for (Map.Entry<UUID, List<SubmitExamRequest>> entry : buffer3.entrySet()) {
            UUID teacherId = entry.getKey();
            List<SubmitExamRequest> events = entry.getValue();

            int total = events.size();
            double avgScore = events.stream().mapToInt(SubmitExamRequest::score).average().orElse(0);
            long totalTime = events.stream()
                    .mapToLong(req -> req.evaluationTime().getTime())
                    .sum();

            AggregatedStatsEntity entity = new AggregatedStatsEntity(
                    teacherId, total, avgScore, totalTime
            );

            aggregatedStatsRepository.save(entity);
        }

        buffer3.clear();
    }

}