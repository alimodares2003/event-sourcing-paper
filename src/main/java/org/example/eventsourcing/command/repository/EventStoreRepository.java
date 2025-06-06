package org.example.eventsourcing.command.repository;

import org.example.eventsourcing.command.entity.StoredEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventStoreRepository extends JpaRepository<StoredEvent, Long> {}
