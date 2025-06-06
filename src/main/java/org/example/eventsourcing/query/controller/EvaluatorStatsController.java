package org.example.eventsourcing.query.controller;

import org.example.eventsourcing.query.entity.AggregatedStatsEntity;
import org.example.eventsourcing.query.repository.AggregatedStatsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/reports/evaluators")
public class EvaluatorStatsController {

    private final AggregatedStatsRepository repository;

    public EvaluatorStatsController(AggregatedStatsRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{teacherId}")
    public ResponseEntity<AggregatedStatsEntity> getStats(@PathVariable UUID teacherId) {
        return repository.findById(teacherId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}