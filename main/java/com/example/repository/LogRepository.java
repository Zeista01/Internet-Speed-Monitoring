package com.example.repository;

import com.example.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface LogRepository extends JpaRepository<Log, Integer> {
    List<Log> findByTimestampBetween(LocalDateTime start, LocalDateTime end);
    List<Log> findByStatusAndTimestampBetween(String status, LocalDateTime start, LocalDateTime end);
}
