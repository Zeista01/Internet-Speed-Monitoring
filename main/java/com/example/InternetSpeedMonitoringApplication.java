package com.example;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
@SpringBootApplication
@EnableScheduling  // Add this to enable scheduling
public class InternetSpeedMonitoringApplication {
    public static void main(String[] args) {
        SpringApplication.run(InternetSpeedMonitoringApplication.class, args);
    }
}
