package com.gurkhaconsultinggroup.backend.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Health API",description = "Displays health status of backend app")
public class HealthController {
    @Value("${spring.application.name}")
    private String appName;

    @GetMapping("/api/health")
    public String health(){
        return appName + " is running!";
    }
}
