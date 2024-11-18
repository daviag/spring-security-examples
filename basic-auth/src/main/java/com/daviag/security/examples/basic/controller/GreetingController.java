package com.daviag.security.examples.basic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/greeting")
public class GreetingController {

    @GetMapping("/public")
    public String getPublic() {
        return "Hello!";
    }

    @GetMapping("/restricted")
    public String getRestricted() {
        return "Hello Authenticated!";
    }
}
