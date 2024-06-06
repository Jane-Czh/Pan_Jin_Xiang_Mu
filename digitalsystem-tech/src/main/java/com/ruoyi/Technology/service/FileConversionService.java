package com.ruoyi.Technology.service;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class FileConversionService {

    public void convertWordToPdf(String filePath, String pdfFilePath) throws IOException {
        try (InputStream is = new FileInputStream(new File(filePath));
             XWPFDocument doc = new XWPFDocument(is);
             OutputStream out = new FileOutputStream(new File(pdfFilePath));
             PDDocument pdf = new PDDocument()) {

            for (XWPFParagraph p : doc.getParagraphs()) {
                for (XWPFRun run : p.getRuns()) {
                    String text = run.getText(0);  // Extract text from word
                    if (text != null && !text.isEmpty()) {
                        PDPage page = new PDPage();
                        pdf.addPage(page);
                        try (PDPageContentStream contentStream = new PDPageContentStream(pdf, page)) {
                            contentStream.beginText();
                            contentStream.setFont(PDType1Font.HELVETICA, 12);
                            contentStream.newLineAtOffset(100, 700); // Set position for text
                            contentStream.showText(text);  // Add text to PDF content
                            contentStream.endText();
                        }
                    }
                }
            }

            pdf.save(out);
        }
    }

    public static void main(String[] args) {
        FileConversionService service = new FileConversionService();
        try {
            service.convertWordToPdf("input.docx", "output.pdf");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
