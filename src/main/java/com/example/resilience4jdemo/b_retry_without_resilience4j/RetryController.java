package com.example.resilience4jdemo.b_retry_without_resilience4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class RetryController {

    private final RetryService retryService;

    public RetryController(RetryService retryService) {
        this.retryService = retryService;
    }


    @GetMapping("/api-call")
    public String apiCall(@RequestParam String param) throws InterruptedException {
        return retryService.process(param);
    }

};
