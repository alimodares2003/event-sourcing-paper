package org.example.eventsourcing.query.repository;

import org.example.eventsourcing.query.entity.AggregatedStatsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AggregatedStatsRepository extends JpaRepository<AggregatedStatsEntity, UUID> {}
