package com.ibercode.pdfgen;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class PdfService {

    @Autowired
    private PdfRepository pdfRepository;

    public byte[] generatePdf() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        // Initialize PDF writer
        PdfWriter writer = new PdfWriter(byteArrayOutputStream);

        // Initialize PDF document
        com.itextpdf.kernel.pdf.PdfDocument pdf = new com.itextpdf.kernel.pdf.PdfDocument(writer);

        // Initialize document
        Document document = new Document(pdf);

        // Add paragraph to the document
        document.add(new Paragraph("Hello, World!"));

        // Add image to the document
/*        String imageUrl = "https://ibercode.com/assets/images/ibercode.png"; // replace with your image URL or file path
        ImageData data = ImageDataFactory.create(imageUrl);
        Image img = new Image(data);
        document.add(img);*/

        String imagePath = "assets/salut_logotip.png"; // replace with your local image file path
        ImageData data = ImageDataFactory.create(imagePath);
        Image img = new Image(data);
        document.add(img);

        // Close document
        document.close();

        byte[] pdfBytes = byteArrayOutputStream.toByteArray();

        // Store PDF in MongoDB
        PdfFile pdfFile = new PdfFile();
        pdfFile.setFilename("generated.pdf");
        pdfFile.setContent(pdfBytes);
        pdfRepository.save(pdfFile);

        return pdfBytes;
    }
}