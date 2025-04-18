package com.example.controller;

import com.example.entity.Log;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/logs")
public class LogController {

    @PostMapping("/save")
    public String saveLog(@RequestBody Log log) {
        log.setTimestamp(LocalDateTime.now());
        return "Log saved at: " + log.getTimestamp() + " - Message: " + log.getMessage();
    }
}
