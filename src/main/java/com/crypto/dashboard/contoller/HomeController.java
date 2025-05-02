package com.crypto.dashboard.contoller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String Home() {
        return "Welcome to Crypto Dashboard";
    }
    @GetMapping("/api")
    public String api() {
        return "Welcome to Crypto Dashboard API";
    }
}
