package com.example.controller;

import com.example.entity.Log;
import com.example.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class LogController {

    @Autowired
    private LogRepository logRepository;

    @GetMapping
    public List<Log> getAllLogs() {
        return logRepository.findAll();
    }

    @PostMapping
    public Log createLog(@RequestBody Log log) {
        return logRepository.save(log);
    }

    @GetMapping("/between")
    public List<Log> getLogsBetween(
            @RequestParam("start") String start,
            @RequestParam("end") String end) {
        return logRepository.findByTimestampBetween(LocalDateTime.parse(start), LocalDateTime.parse(end));
    }

    @GetMapping("/below-threshold")
    public List<Log> getLogsBelowThresholdBetween(
            @RequestParam("start") String start,
            @RequestParam("end") String end) {
        return logRepository.findByStatusAndTimestampBetween("Below Threshold", LocalDateTime.parse(start), LocalDateTime.parse(end));
    }
}
