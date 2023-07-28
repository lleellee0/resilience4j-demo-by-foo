package com.example.resilience4jdemo.d_circuitbreaker_event;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.core.registry.EntryAddedEvent;
import io.github.resilience4j.core.registry.EntryRemovedEvent;
import io.github.resilience4j.core.registry.EntryReplacedEvent;
import io.github.resilience4j.core.registry.RegistryEventConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class CircuitBreakerDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CircuitBreakerDemoApplication.class, args);
    }

    @Bean
    public RegistryEventConsumer<CircuitBreaker> myRegistryEventConsumer() {

        return new RegistryEventConsumer<CircuitBreaker>() {
            @Override
            public void onEntryAddedEvent(EntryAddedEvent<CircuitBreaker> entryAddedEvent) {
                log.info("RegistryEventConsumer.onEntryAddedEvent");

                CircuitBreaker.EventPublisher eventPublisher = entryAddedEvent.getAddedEntry().getEventPublisher();

                eventPublisher.onEvent(event -> log.info("onEvent {}", event));
                eventPublisher.onSuccess(event -> log.info("onSuccess {}", event));
                eventPublisher.onCallNotPermitted(event -> log.info("onCallNotPermitted {}", event));
                eventPublisher.onError(event -> log.info("onError {}", event));
                eventPublisher.onIgnoredError(event -> log.info("onIgnoredError {}", event));

                eventPublisher.onStateTransition(event -> log.info("onStateTransition {}", event));

                eventPublisher.onSlowCallRateExceeded(event -> log.info("onSlowCallRateExceeded {}", event));
                eventPublisher.onFailureRateExceeded(event -> log.info("onFailureRateExceeded {}", event));
            }

            @Override
            public void onEntryRemovedEvent(EntryRemovedEvent<CircuitBreaker> entryRemoveEvent) {
                log.info("RegistryEventConsumer.onEntryRemovedEvent");
            }

            @Override
            public void onEntryReplacedEvent(EntryReplacedEvent<CircuitBreaker> entryReplacedEvent) {
                log.info("RegistryEventConsumer.onEntryReplacedEvent");
            }
        };
    }

}
