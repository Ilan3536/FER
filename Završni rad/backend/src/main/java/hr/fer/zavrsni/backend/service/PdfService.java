package hr.fer.zavrsni.backend.service;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class PdfService {

    public String extractTextFromPdf(InputStream inputStream) throws IOException {
        PDDocument document = null;
        try {
            document = PDDocument.load(inputStream);
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);
        } finally {
            if (document != null) {
                document.close();
            }
        }
    }
}
