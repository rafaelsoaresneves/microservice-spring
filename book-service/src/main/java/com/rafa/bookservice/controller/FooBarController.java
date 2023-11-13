package com.rafa.bookservice.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Tag(name = "Foo Bar")
@RestController
@RequestMapping("book-service")
public class FooBarController {

    private Logger logger = LoggerFactory.getLogger(FooBarController.class);

    @Operation(summary = "foobar")
    @GetMapping("/foobar")
    //@Retry(name = "foobar" , fallbackMethod = "fallBackMethod")
    //@CircuitBreaker(name = "default" , fallbackMethod = "fallBackMethod")
    //@RateLimiter(name = "default")
    @Bulkhead(name = "default")
    public String fooBar() {
        logger.info("Request to foobar is received!");
        /*var response = new RestTemplate().getForEntity("http://localhost:8080/foobar", String.class);*/
        /*return response.getBody();*/
        return "foobar";
    }

    public String fallBackMethod(Exception ex){
        logger.info("Chamou fallBackMethod");
        return "fallBack Method foobar";
    }
}
