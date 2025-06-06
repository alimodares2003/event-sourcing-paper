package org.example.eventsourcing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EventSourcingUniApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventSourcingUniApplication.class, args);
    }

}
