package com.rafa.greetingservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloWorldController {

/*  @GetMapping("/hello")
    public String helloWorld(){
        return "Hello World";
    }*/

    @Value("${greeting-service.default-value}")
    private String valorParam;

    @RequestMapping("/hello")
    public String helloWorld(){
        return "Hello World: "+ valorParam;
    }
}
