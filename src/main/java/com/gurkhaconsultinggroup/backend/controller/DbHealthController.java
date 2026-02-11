package com.gurkhaconsultinggroup.backend.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Database Health API",description = "Displays health status of database")
public class DbHealthController {
    private final JdbcTemplate jdbcTemplate;

    public DbHealthController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/api/db-health")
    public String dbHealth(){
       Integer one = jdbcTemplate.queryForObject("SELECT 1", Integer.class);
       if(one != null && one == 1){
           return "Database is healthy!";
       } else {
           return "Database is not healthy!";
       }
    }
}
