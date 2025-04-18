@Service
public class SpeedMonitorService {

    @Value("${speed.threshold.mbps}")
    private double threshold;

    private List<LocalDateTime> dropTimes = new ArrayList<>();
    private List<LocalDateTime> dailyDrops = new ArrayList<>();

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
        if (dropTimes.size() > 5) {
            String content = "Hourly Speed Drop Report:\nDrops: " + dropTimes.size() +
                             "\nTimes: " + dropTimes.toString();
            emailService.sendEmail("network@vnit.ac.in", "Speed Drop Alert", content);
            pdfService.saveAsPdf("hourly_report.pdf", content);
        }
        dropTimes.clear();
    }

    @Scheduled(cron = "0 0 23 * * *") // end of day
    public void sendDailyReport() {
        String content = "Daily Speed Drop Summary:\nDrops: " + dailyDrops.size() +
                         "\nTimes: " + dailyDrops.toString();
        emailService.sendEmail("network@vnit.ac.in", "Daily Speed Report", content);
        pdfService.saveAsPdf("daily_report.pdf", content);
        dailyDrops.clear();
    }
}
