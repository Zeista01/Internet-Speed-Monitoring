@Service
public class PdfService {
    public void saveAsPdf(String filename, String content) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("reports/" + filename));
            document.open();
            document.add(new Paragraph(content));
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
