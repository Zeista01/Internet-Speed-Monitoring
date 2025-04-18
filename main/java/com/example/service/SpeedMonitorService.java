package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SpeedMonitorService {

    @Value("${speed.threshold.mbps}")
    private double threshold;

    private final List<LocalDateTime> dropTimes = new ArrayList<>();
    private final List<LocalDateTime> dailyDrops = new ArrayList<>();

    @Autowired private EmailService emailService;
    @Autowired private PdfService pdfService;

    @Scheduled(fixedRate = 300000) // every 5 mins
    public void checkSpeed() {
        try {
            Process process = new ProcessBuilder("speedtest", "--simple").start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            double downloadSpeed = 0.0;
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Download:")) {
                    downloadSpeed = Double.parseDouble(line.split(" ")[1]);
                }
            }

            if (downloadSpeed < threshold) {
                LocalDateTime now = LocalDateTime.now();
                dropTimes.add(now);
                dailyDrops.add(now);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Scheduled(cron = "0 0 * * * *") // every hour
    public void sendHourlyReport() {
        if (!dropTimes.isEmpty()) {
            String content = "Hourly Speed Drop Report:\nDrops: " + dropTimes.size() +
                    "\nTimes: " + dropTimes;
            emailService.sendEmail("network@vnit.ac.in", "Speed Drop Alert", content);
            pdfService.saveAsPdf("hourly_report.pdf", content);
        }
        dropTimes.clear();
    }

    @Scheduled(cron = "0 0 23 * * *") // end of day
    public void sendDailyReport() {
        String content = "Daily Speed Drop Summary:\nDrops: " + dailyDrops.size() +
                "\nTimes: " + dailyDrops;
        emailService.sendEmail("network@vnit.ac.in", "Daily Speed Report", content);
        pdfService.saveAsPdf("daily_report.pdf", content);
        dailyDrops.clear();
    }
}
