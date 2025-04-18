package com.example.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;

@Service
public class PdfService {

    public void saveAsPdf(String filename, String content) {
        try {
            File directory = new File("reports");
            if (!directory.exists()) {
                directory.mkdirs();
            }

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
