package hr.fer.zavrsni.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import hr.fer.zavrsni.backend.service.PdfService;

import java.io.FileWriter;
import java.io.IOException;

@RestController
@Validated
public class PdfController {

    private final PdfService pdfService;

    @Autowired
    public PdfController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    @PostMapping("/extract-pdf")
    public ResponseEntity<String> extractPdfData(@RequestParam("file") MultipartFile file) {
        try {
            String filePath = "C:\\FER\\git_repo_FER\\FER\\Završni rad\\backend\\src\\main\\resources\\static\\";

            String extractedText = pdfService.extractTextFromPdf(file.getInputStream());
            filePath+= file.getName() + ".txt";
            // Process the extracted text and import into the database
            try (FileWriter writer = new FileWriter(filePath)) {
                writer.write(extractedText);
                System.out.println("File created successfully.");
            } catch (IOException e) {
                System.out.println("An error occurred while writing to the file.");
                e.printStackTrace();
            }
            
            return ResponseEntity.ok(extractedText);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to extract PDF data.");
        }
    }
}

