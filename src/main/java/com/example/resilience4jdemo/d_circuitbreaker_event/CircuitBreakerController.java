package com.example.resilience4jdemo.d_circuitbreaker_event;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class CircuitBreakerController {

    private final CircuitBreakerService circuitBreakerService;

    public CircuitBreakerController(CircuitBreakerService circuitBreakerService) {
        this.circuitBreakerService = circuitBreakerService;
    }


    @GetMapping("/api-call")
    public String apiCall(@RequestParam String param) throws InterruptedException {
        return circuitBreakerService.process(param);
    }

};
