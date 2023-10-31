package com.rafa.greetingservice.controller;

import com.rafa.greetingservice.configuration.GreetingConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloWorldController {

    @Autowired
    GreetingConfiguration greetingConfiguration;

/*  @GetMapping("/hello")
    public String helloWorld(){
        return "Hello World";
    }*/

    //@Value("${greeting-service.default-value}")
    //private String valorParam;

    @RequestMapping("/hello")
    public String helloWorld(){
        return "Hello World: "+ greetingConfiguration.getDefaultValue();
    }
}
